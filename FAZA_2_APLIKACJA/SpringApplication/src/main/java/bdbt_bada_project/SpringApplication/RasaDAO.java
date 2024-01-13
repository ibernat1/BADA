package bdbt_bada_project.SpringApplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class RasaDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public RasaDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public int GetNrRasyFromNazwa (String nazwa){
        int numer = 1;
        String sql= "SELECT * FROM Rasa";
        List<Rasa> listRasa=jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Rasa.class));
        for (Rasa rasa : listRasa) {
            if (rasa.getNazwa_rasy().equals(nazwa)) {
                numer = rasa.getNr_rasy();
                break; // Stop iterating once a match is found
            }
        }
        return numer;
    }

    /* Import java.util.List (zawiera info z bazy danych) */
    public List<Rasa> list(){
        String sql= "SELECT * FROM Rasa";
        List<Rasa> listRasa=jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Rasa.class));
        return listRasa;
    }
    public int getNrGatunkuWithNrRasy(int nr_rasy){
        Object [] args={nr_rasy};
        String sql= "SELECT * FROM Rasa WHERE nr_rasy= " + args[0];
        int nr_gatunku=jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Rasa.class)).getNr_gatunku();
        return nr_gatunku;
    }

    public List<Rasa> getRacesBySpecies(int nr_gatunku) {
        String sql = "SELECT * FROM Rasa WHERE nr_gatunku = ?";
        List<Rasa> listRasa = jdbcTemplate.query(sql, new Object[]{nr_gatunku}, BeanPropertyRowMapper.newInstance(Rasa.class));
        return listRasa;
    }
    public String getNazwaRasyWithNr(int nr){
        Object [] args={nr};
        String sql= "SELECT * FROM Rasa WHERE nr_rasy= " + args[0];
        String nazwa=jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Rasa.class)).getNazwa_rasy();
        return nazwa;
    }
    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Rasa rasa) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Rasa").usingColumns("nr_rasy", "nazwa_rasy", "nr_gatunku");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(rasa);
        insertActor.execute(param);

    }
    /* Read – odczytywanie danych z bazy */
    public Rasa get(int nr) {
        String sql= String.format("SELECT * FROM Rasa WHERE nr_rasy=%d", nr);
        Rasa rasa=jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Rasa.class));
        return rasa;
    }

    /* Update – aktualizacja danych */
    public void update(Rasa rasa) {
        String sql = "UPDATE Rasa SET nazwa_rasy=:nazwa_rasy, nr_gatunku=:nr_gatunku WHERE nr_rasy=:nr_rasy";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(rasa);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int nr) {
        String sql = "DELETE FROM Rasa WHERE nr_rasy = ?";
        jdbcTemplate.update(sql,nr);
    }
}

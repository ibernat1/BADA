package bdbt_bada_project.SpringApplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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

    /* Import java.util.List (zawiera info z bazy danych) */
    public List<Rasa> list(){
        String sql= "SELECT * FROM Rasa";
        List<Rasa> listRasa=jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Rasa.class));
        return listRasa;
    }
    public int getNrGatunkuWithNrRasy(int nr_rasy){
        String sql= String.format("SELECT * FROM Rasa WHERE nr_rasy=%d", nr_rasy);
        int nr_gatunku=jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Rasa.class)).get(0).getNr_gatunku();
        return nr_gatunku;
    }
    public String getNazwaRasyWithNr(int nr){
        String sql= String.format("SELECT * FROM Rasa WHERE nr_rasy=%d", nr);
        String nazwa=jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Rasa.class)).get(0).getNazwa_rasy();
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
        return null;
    }
    /* Update – aktualizacja danych */
    public void update(Rasa rasa) {
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int nr) {
    }
}

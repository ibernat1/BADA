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
public class GatunekDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public GatunekDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    /* Import java.util.List (zawiera info z bazy danych) */
    public List<Gatunek> list(){
        String sql= "SELECT * FROM Gatunki";
        List<Gatunek> listGatunek=jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Gatunek.class));
        return listGatunek;
    }

    public String getNazwaGatunkuWithNr(int nr){
        Object [] args={nr};
        String sql= "SELECT * FROM Gatunki WHERE nr_gatunku= " + args[0];
        String nazwa_gatunku=jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Gatunek.class)).getNazwa_gatunku();
        return nazwa_gatunku;
    }
    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Gatunek gatunek) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Gatunki").usingColumns("nr_gatunku", "nazwa_gatunku");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(gatunek);
        insertActor.execute(param);

    }
    /* Read – odczytywanie danych z bazy */
    public Gatunek get(int nr) {
        String sql= String.format("SELECT * FROM Gatunek WHERE nr_gatunku=%d", nr);
        Gatunek gatunek=jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Gatunek.class));
        return gatunek;
    }
    /* Update – aktualizacja danych */
    public void update(Gatunek gatunek) {
        String sql = "UPDATE Gatunki SET nazwa_gatunku=:nazwa_gatunku WHERE nr_gatunku=:nr_gatunku";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(gatunek);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int nr) {
        String sql = "DELETE FROM Gatunki WHERE nr_gatunku = ?";
        jdbcTemplate.update(sql,nr);
    }


}

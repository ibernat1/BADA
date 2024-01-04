package bdbt_bada_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.beans.BeanProperty;
import java.sql.Date;
import java.util.List;

@Repository
public class ZwierzetaDAO {
    /* Import org.springframework.jd....Template */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ZwierzetaDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    /* Import java.util.List  (zawiera info z bazy danych) */
    public List<Zwierze> list(){
        String sql = "SELECT * FROM ZWIERZĘTA";
        List<Zwierze> listZwierze = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Zwierze.class));
        return listZwierze;
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Zwierze zwierze) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("ZWIERZĘTA").usingColumns("imie", "data_przyjecia", "szczepienie_wscieklizna", "data_adopcji", "rok_urodzenia", "nr_schroniska", "nr_adoptujacego","nr_kojca","nr_rasy");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(zwierze);
        insertActor.execute(param);
    }

    /* Read – odczytywanie danych z bazy */
    public Zwierze get(int nr) {
        return null;
    }

    /* Update – aktualizacja danych */
    public void update(Zwierze zwierze) {
    }



    /* Delete – wybrany rekord z danym id */
    public void delete(int nr) {
    }

}

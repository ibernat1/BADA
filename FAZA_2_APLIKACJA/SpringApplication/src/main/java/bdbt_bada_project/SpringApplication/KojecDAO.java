package bdbt_bada_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KojecDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public KojecDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    /* Import java.util.List (zawiera info z bazy danych) */
    public List<Kojec> list(){
        String sql= "SELECT * FROM Kojce";
        List<Kojec> listKojec=jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Kojec.class));
        return listKojec;
    }
    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Kojec kojec) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Kojce").usingColumns("nr_kojca", "rozmiar", "liczba_zwierzat", "czy_ocieplany");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(kojec);
        insertActor.execute(param);

    }
    /* Read – odczytywanie danych z bazy */
    public Kojec kojec(int nr) {
        Object [] args={nr};
        String sql= "SELECT * FROM Kojce WHERE nr_kojca= " + args[0];
        Rasa kojec=jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Kojec.class));
        return kojec;
    }
    /* Update – aktualizacja danych */
    public void update(Kojec kojec) {
        String sql = "UPDATE Kojce SET rozmiar=:rozmiar, liczba_zwierzat=:liczba_zwierzat, czy ocieplany=:czy_ocieplany WHERE nr_kojca=:nr_kojca";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(kojec);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int nr) {
        String sql = "DELETE FROM Kojce WHERE nr_kojca = ?";
        jdbcTemplate.update(sql,nr);
    }
}

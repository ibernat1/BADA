package bdbt_bada_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

public class SchroniskoDAO {
    /* Import org.springframework.jd....Template */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SchroniskoDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    /* Import java.util.List  (zawiera info z bazy danych) */
    public List<Schronisko> list(){
        String sql = "SELECT * FROM Schroniska";
        List<Schronisko> listSchronisko = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Schronisko.class));
        return listSchronisko;
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    //też nie wiem czy ktoś bęzie dodawał nowe schroniska
    public void save(Schronisko schronisko) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Schroniska").usingColumns("nazwa", "nr_adresu", "data_zalozenia", "kierownik");



        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(schronisko);
        insertActor.execute(param);
    }

    /* Read – odczytywanie danych z bazy */
    public Schronisko get(int nr) {
        Object [] args={nr};
        String sql= "SELECT * FROM Schroniska WHERE nr_schroniska= " + args[0];
        Schronisko schronisko=jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Schronisko.class));
        return schronisko;
    }

    /* Update – aktualizacja danych */
    public void update(Schronisko schronisko) {
        String sql = "UPDATE Schroniska SET nazwa=:nazwa, nr_adresu=:nr_adresu, data_zalozenia=:data_zalozenia, kierownik=:kierownik WHERE nr_schroniska=:nr_schroniska";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(schronisko);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }

    /* Delete – wybrany rekord z danym id */
    //Moim zdaniem tą funkcję można usunąć w ogóle
    public void delete(int nr) {
        String sql = "DELETE FROM Schroniska WHERE nr_schroniska = ?";
        jdbcTemplate.update(sql,nr);
    }

}

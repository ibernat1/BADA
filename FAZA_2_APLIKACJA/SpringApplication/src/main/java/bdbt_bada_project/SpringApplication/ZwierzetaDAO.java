package bdbt_bada_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.beans.BeanProperty;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ZwierzetaDAO {
    /* Import org.springframework.jd....Template */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    RasaDAO rasaDao;

    public void commitTransaction() {
        try {
            jdbcTemplate.getDataSource().getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace(); // Lub inna obsługa błędów, np. logger
        }
    }

    public ZwierzetaDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.rasaDao = rasaDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    /* Import java.util.List  (zawiera info z bazy danych) */
    public List<Zwierze> list(){
        String sql = "SELECT z.*, r.nazwa_rasy, g.nazwa_gatunku FROM ZWIERZĘTA z JOIN rasa r ON z.nr_rasy = r.nr_rasy JOIN gatunki g ON r.nazwa_gatunku = g.nr_gatunku";
        List<Zwierze> listZwierze = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Zwierze.class));
        for (Zwierze zwierze : listZwierze) {
            System.out.println(zwierze.getImie() + zwierze.getSzczepienie_wscieklizna());
        }
        return listZwierze;
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Zwierze zwierze) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("ZWIERZĘTA").usingColumns("imie", "data_przyjecia", "szczepienie_wscieklizna", "data_adopcji", "rok_urodzenia", "nr_schroniska", "nr_adoptujacego","nr_kojca","nr_rasy");

        System.out.println(zwierze.getNazwa_rasy());
        int numer =1;

        if(zwierze.getNr_rasy() == null)
        {
            String nazwa = zwierze.getNazwa_rasy();

            numer = rasaDao.GetNrRasyFromNazwa(nazwa);
            zwierze.setNr_rasy(numer);
        }
        numer= Integer.parseInt(zwierze.getNazwa_rasy());

        zwierze.setNr_rasy(numer);
        System.out.println(zwierze.getNr_rasy());

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(zwierze);
        insertActor.execute(param);
    }

    /* Read – odczytywanie danych z bazy */
    public Zwierze get(int nr) {
        Object [] args={nr};
        String sql= "SELECT * FROM Zwierzęta WHERE nr_zwierzecia= " + args[0];
        Zwierze zwierze=jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Zwierze.class));
        return zwierze;
    }

    /* Update – aktualizacja danych */
    public void update(Zwierze zwierze) {
        String sql = "UPDATE Zwierzęta SET imie=:imie, data_przyjecia=:data_przyjecia, szczepienie_wscieklizna=:szczepienie_wscieklizna, data_adopcji=:data_adopcji, rok_urodzenia=:rok_urodzenia, nr_schroniska=:nr_schroniska, nr_adoptujacego=:nr_adoptujacego, nr_kojca=:nr_kojca, nr_rasy=:nr_rasy WHERE nr_zwierzecia=:nr_zwierzecia";
        try {
            BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(zwierze);
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
            System.out.println(zwierze.getImie());
            System.out.println(zwierze.getNr_zwierzecia());
            template.update(sql, param);
        } catch (DataAccessException e) {
            // Log the exception or print the stack trace
            e.printStackTrace();
        }

    }

    /* Delete – wybrany rekord z danym id */
    public void delete(int nr) {
        String sql = "DELETE FROM Zwierzęta WHERE nr_zwierzecia = ?";
        jdbcTemplate.update(sql,nr);
    }


    public List<Zwierze> findByGatunek(String gatunek) {
        Object [] args={gatunek};
        String sql = "SELECT z.*, r.nazwa_rasy, g.nazwa_gatunku FROM ZWIERZĘTA z JOIN rasa r ON z.nr_rasy = r.nr_rasy JOIN gatunki g ON r.nazwa_gatunku = g.nr_gatunku WHERE g.nazwa_gatunku= '" + args[0] +"'";
        List<Zwierze> listZwierze = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Zwierze.class));
        return listZwierze;
    }

    public String getNazwa_rasy(int nr_rasy){
        RasaDAO rasa = new RasaDAO(jdbcTemplate);
        return rasa.getNazwaRasyWithNr(nr_rasy);
    }
    public String getNazwa_gatunku(int nr_rasy){
        RasaDAO rasa = new RasaDAO(jdbcTemplate);
        GatunekDAO gatunek = new GatunekDAO(jdbcTemplate);
        int nr_gatunku=rasa.getNrGatunkuWithNrRasy(nr_rasy);
        return gatunek.getNazwaGatunkuWithNr(nr_gatunku);
    }

    public void adoptujZwierze(Zwierze zwierze, int user_id){
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        zwierze.setData_adopcji(date);
        zwierze.setNr_adoptujacego(user_id);
        this.update(zwierze);
    }

}

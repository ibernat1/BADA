package bdbt_bada_project.SpringApplication;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class RasaDAOTest {
    private RasaDAO dao;

    @BeforeEach
    void setUp() throws Exception{
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        datasource.setUsername("C##IGA2");
        datasource.setPassword("haslo");
        datasource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new RasaDAO(new JdbcTemplate(datasource));
    }
    @Test
    void testList(){
        List<Rasa> listRasa = dao.list();

        assertTrue(!listRasa.isEmpty());
    }


    @Test
    void testSave() throws ParseException {
        Rasa rasa = new Rasa(10,"buldog",1);
    }
    @Test
    void testGet(){
        int id=1;
        Rasa rasa = dao.get(id);
        assertNotNull(rasa);
    }
    @Test
    void testUpdate() throws ParseException {
    }
    @Test
    void TestDelete(){
        int id = 1;
        dao.delete(id);
    }

}


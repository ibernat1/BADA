package bdbt_bada_project.SpringApplication;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;


public class ZwierzetaDAOTest {
    private ZwierzetaDAO dao;

    @BeforeEach
    void setUp() throws Exception{
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        datasource.setUsername("C##IGA2");
        datasource.setPassword("haslo");
        datasource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new ZwierzetaDAO(new JdbcTemplate(datasource));
    }
    @Test
    void testList(){
        List<Zwierze> listZwierze = dao.list();

        assertTrue(!listZwierze.isEmpty());
    }

    /*
    @Test
    void testSave(){
        fail("Jeszcze nie zaimplementowano");
    }
    @Test
    void testGet(){
        fail("Jeszcze nie zaimplementowano");
    }
    @Test
    void testUpdate(){
        fail("Jeszcze nie zaimplementowano");
    }
    @Test
    void TestDelete(){
        fail("Jeszcze nie zaimplementowano");
    }

     */
}


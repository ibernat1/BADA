package bdbt_bada_project.SpringApplication;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
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


    @Test
    void testSave() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = dateFormat.parse("2023-10-04");
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Zwierze zwierze = new Zwierze(10,"Benio", sqlDate, 'N', null, "2020", 1, null,1,2  );
    }
      /*
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


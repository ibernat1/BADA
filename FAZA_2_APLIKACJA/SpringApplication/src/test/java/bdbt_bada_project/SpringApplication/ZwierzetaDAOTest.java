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
    @Test
    void testGet(){
        int id=1;
        Zwierze zwierze = dao.get(id);
        assertNotNull(zwierze);
    }
    @Test
    void testUpdate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = dateFormat.parse("2023-10-04");
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Zwierze zwierze = new Zwierze();
        zwierze.setNr_zwierzecia(1);
        zwierze.setData_przyjecia(sqlDate);
        zwierze.setImie("Reksio");
        zwierze.setSzczepienie_wscieklizna('T');
        zwierze.setData_adopcji(null);
        zwierze.setRok_urodzenia("2022");
        zwierze.setNr_schroniska(1);
        zwierze.setNr_adoptujacego(null);
        zwierze.setNr_kojca(1);
        zwierze.setNr_rasy(3);

        dao.update(zwierze);
    }
      /*
    @Test
    void TestDelete(){
        fail("Jeszcze nie zaimplementowano");
    }

     */
}


package bdbt_bada_project.SpringApplication;

import java.sql.Date;

public class Schronisko {
    private int nr_schroniska;
    private String nazwa;
    private int nr_adresu;
    private Date data_zalozenia;
    private String kierownik;

    public String getKierownik() {
        return kierownik;
    }

    public void setKierownik(String kierownik) {
        this.kierownik = kierownik;
    }




    public int getNr_schroniska() {
        return nr_schroniska;
    }

    public void setNr_schroniska(int nr_schroniska) {
        this.nr_schroniska = nr_schroniska;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getNr_adresu() {
        return nr_adresu;
    }

    public void setNr_adresu(int nr_adresu) {
        this.nr_adresu = nr_adresu;
    }

    public Date getData_zalozenia() {
        return data_zalozenia;
    }

    public void setData_zalozenia(Date data_zalozenia) {
        this.data_zalozenia = data_zalozenia;
    }

    public Schronisko(int nr_schroniska, String nazwa, int nr_adresu, Date data_zalozenia, String kierownik){
        super();
        this.nr_schroniska=nr_schroniska;
        this.nazwa=nazwa;
        this.nr_adresu=nr_adresu;
        this.data_zalozenia=data_zalozenia;
        this.kierownik=kierownik;
    }

}

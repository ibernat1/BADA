package bdbt_bada_project.SpringApplication;

import java.sql.Date;
import java.sql.Timestamp;

public class Zwierze {
    private int nr_zwierzecia;
    private String imie;
    private Date data_przyjecia;
    private char szczepienie_wscieklizna;
    private Date data_adopcji;
    private String rok_urodzenia;
    private Integer nr_schroniska;
    private Integer nr_adoptujacego;
    private Integer nr_kojca;
    private Integer nr_rasy;
    private String nazwa_rasy;
    private String nazwa_gatunku;

    public Zwierze() {
        this.data_adopcji=null;
        this.nr_schroniska=1;
        this.nr_rasy=3;
    }

    public Integer getNr_zwierzecia() {
        return nr_zwierzecia;
    }

    public void setNr_zwierzecia(int nr_zwierzecia) {
        this.nr_zwierzecia = nr_zwierzecia;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public Date getData_przyjecia() {
        return data_przyjecia;
    }

    public void setData_przyjecia(Date data_przyjecia) {
        this.data_przyjecia = data_przyjecia;
    }

    public char getSzczepienie_wscieklizna() {
        return szczepienie_wscieklizna;
    }

    public void setSzczepienie_wscieklizna(char szczepienie_wscieklizna) {
        this.szczepienie_wscieklizna = szczepienie_wscieklizna;
    }

    public Date getData_adopcji() {
        return data_adopcji;
    }

    public void setData_adopcji(Date data_adopcji) {
        if (data_adopcji != null && data_adopcji.toString().isEmpty()) {
            this.data_adopcji = null;
        } else {
            this.data_adopcji = data_adopcji;
        }
    }

    public String getRok_urodzenia() {
        return rok_urodzenia;
    }

    public void setRok_urodzenia(String rok_urodzenia) {
        this.rok_urodzenia = rok_urodzenia;
    }

    public Integer getNr_schroniska() {
        return nr_schroniska;
    }

    public void setNr_schroniska(Integer nr_schroniska) {
        this.nr_schroniska = nr_schroniska;
    }

    public Integer getNr_adoptujacego() {
        return nr_adoptujacego;
    }

    public void setNr_adoptujacego(Integer nr_adoptujacego) {
        this.nr_adoptujacego = nr_adoptujacego;
    }

    public Integer getNr_kojca() {
        return nr_kojca;
    }

    public void setNr_kojca(Integer nr_kojca) {
        this.nr_kojca = nr_kojca;
    }

    public Integer getNr_rasy() {
        return nr_rasy;
    }

    public void setNr_rasy(Integer nr_rasy) {
        this.nr_rasy = nr_rasy;
    }

    public String getNazwa_rasy() {
        return nazwa_rasy;
    }

    public void setNazwa_rasy(String nazwa_rasy) {
        this.nazwa_rasy = nazwa_rasy;
    }

    public String getNazwa_gatunku() {
        return nazwa_gatunku;
    }

    public void setNazwa_gatunku(String nazwa_gatunku) {
        this.nazwa_gatunku = nazwa_gatunku;
    }

    public Zwierze(Integer nr_zwierzecia, String imie, Date data_przyjecia, char szczepienie_wscieklizna, Date data_adopcji, String rok_urodzenia, Integer nr_schroniska, Integer nr_adoptujacego, Integer nr_kojca, Integer nr_rasy, String nazwa_rasy, String nazwa_gatunku){
        super();
        this.nr_zwierzecia=nr_zwierzecia;
        this.imie=imie;
        this.data_przyjecia=data_przyjecia;
        this.szczepienie_wscieklizna=szczepienie_wscieklizna;
        this.data_adopcji=data_adopcji;
        this.rok_urodzenia=rok_urodzenia;
        this.nr_schroniska=nr_schroniska;
        this.nr_adoptujacego=nr_adoptujacego;
        this.nr_kojca=nr_kojca;
        this.nr_rasy=nr_rasy;
        this.nazwa_rasy=nazwa_rasy;
        this.nazwa_gatunku=nazwa_gatunku;
    }


}

package bdbt_bada_project.SpringApplication;

import java.sql.Date;

public class Zwierze {
    private int nr_zwierzecia;
    private String imie;
    private Date data_przyjecia;
    private boolean szczepienie;
    private Date data_adopcji;
    private String rok_urodzenia;
    private Integer nr_schroniska;
    private Integer nr_adoptujacego;
    private Integer nr_kojca;
    private Integer nr_rasy;

    public Zwierze() {
    }

    public int getNr_zwierzecia() {
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

    public boolean isSzczepienie() {
        return szczepienie;
    }

    public void setSzczepienie(boolean szczepienie) {
        this.szczepienie = szczepienie;
    }

    public Date getData_adopcji() {
        return data_adopcji;
    }

    public void setData_adopcji(Date data_adopcji) {
        this.data_adopcji = data_adopcji;
    }

    public String getRok_urodzenia() {
        return rok_urodzenia;
    }

    public void setRok_urodzenia(String rok_urodzenia) {
        this.rok_urodzenia = rok_urodzenia;
    }

    public int getNr_schroniska() {
        return nr_schroniska;
    }

    public void setNr_schroniska(Integer nr_schroniska) {
        this.nr_schroniska = nr_schroniska;
    }

    public int getNr_adoptujacego() {
        return nr_adoptujacego;
    }

    public void setNr_adoptujacego(Integer nr_adoptujacego) {
        this.nr_adoptujacego = nr_adoptujacego;
    }

    public int getNr_kojca() {
        return nr_kojca;
    }

    public void setNr_kojca(Integer nr_kojca) {
        this.nr_kojca = nr_kojca;
    }

    public int getNr_rasy() {
        return nr_rasy;
    }

    public void setNr_rasy(Integer nr_rasy) {
        this.nr_rasy = nr_rasy;
    }

    public Zwierze(int nr_zwierzecia, String imie, Date data_przyjecia, boolean szczepienie, Date data_adopcji, String rok_urodzenia, Integer nr_schroniska, Integer nr_adoptujacego, Integer nr_kojca, Integer nr_rasy){
        super();
        this.nr_zwierzecia=nr_zwierzecia;
        this.imie=imie;
        this.data_przyjecia=data_przyjecia;
        this.szczepienie=szczepienie;
        this. data_adopcji=data_adopcji;
        this.rok_urodzenia=rok_urodzenia;
        this.nr_schroniska=nr_schroniska;
        this.nr_adoptujacego=nr_adoptujacego;
        this.nr_kojca=nr_kojca;
        this.nr_rasy=nr_rasy;
    }
}

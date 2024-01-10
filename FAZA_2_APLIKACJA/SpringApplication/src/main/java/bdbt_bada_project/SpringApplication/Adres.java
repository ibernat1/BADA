package bdbt_bada_project.SpringApplication;

public class Adres {
    private int nr_adresu;
    private String miejscowosc;
    private String ulica;
    private String nr_domu;
    private String nr_lokalu;
    private int nr_poczty;
    public Adres(){

    }

    public int getNr_adresu() {
        return nr_adresu;
    }

    public void setNr_adresu(int nr_adresu) {
        this.nr_adresu = nr_adresu;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNr_domu() {
        return nr_domu;
    }

    public void setNr_domu(String nr_domu) {
        this.nr_domu = nr_domu;
    }

    public String getNr_lokalu() {
        return nr_lokalu;
    }

    public void setNr_lokalu(String nr_lokalu) {
        this.nr_lokalu = nr_lokalu;
    }

    public int getNr_poczty() {
        return nr_poczty;
    }

    public void setNr_poczty(int nr_poczty) {
        this.nr_poczty = nr_poczty;
    }

    public Adres(int nr_adresu, String miejscowosc, String ulica, String nr_domu, String nr_lokalu, int nr_poczty) {
        this.nr_adresu = nr_adresu;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nr_domu = nr_domu;
        this.nr_lokalu = nr_lokalu;
        this.nr_poczty = nr_poczty;
    }
}

package bdbt_bada_project.SpringApplication;

public class Gatunek {


    private int nr_gatunku;
    private String nazwa_gatunku;


    public Gatunek(int nr_gatunku, String nazwa_gatunku){
        super();
        this.nr_gatunku=nr_gatunku;
        this.nazwa_gatunku=nazwa_gatunku;
    }

    public String getNazwa_gatunku() {
        return nazwa_gatunku;
    }

    public void setNazwa_gatunku(String nazwa_gatunku) {
        this.nazwa_gatunku = nazwa_gatunku;
    }

    public int getNr_gatunku() {
        return nr_gatunku;
    }

    public void setNr_gatunku(int nr_gatunku) {
        this.nr_gatunku = nr_gatunku;
    }
}

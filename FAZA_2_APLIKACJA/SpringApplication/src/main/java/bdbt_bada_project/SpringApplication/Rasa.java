package bdbt_bada_project.SpringApplication;

public class Rasa {
    private int nr_rasy;
    private String nazwa_rasy;
    private int nr_gatunku;

    public Rasa(int nr_rasy, String nazwa_rasy, int nr_gatunku){
        super();
        this.nr_rasy=nr_rasy;
        this.nazwa_rasy=nazwa_rasy;
        this.nr_gatunku=nr_gatunku;
    }

    public int getNr_rasy() {
        return nr_rasy;
    }

    public void setNr_rasy(int nr_rasy) {
        this.nr_rasy = nr_rasy;
    }

    public String getNazwa_rasy() {
        return nazwa_rasy;
    }

    public void setNazwa_rasy(String nazwa_rasy) {
        this.nazwa_rasy = nazwa_rasy;
    }

    public int getNr_gatunku() {
        return nr_gatunku;
    }

    public void setNr_gatunku(int nr_gatunku) {
        this.nr_gatunku = nr_gatunku;
    }


}

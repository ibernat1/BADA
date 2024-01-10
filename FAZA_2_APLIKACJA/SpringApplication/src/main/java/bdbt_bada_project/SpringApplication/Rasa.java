package bdbt_bada_project.SpringApplication;

public class Rasa {
    private Integer nr_rasy;
    private String nazwa_rasy;
    private Integer nr_gatunku;

    public Rasa(Integer nr_rasy, String nazwa_rasy, Integer nr_gatunku){
        super();
        this.nr_rasy=nr_rasy;
        this.nazwa_rasy=nazwa_rasy;
        this.nr_gatunku=nr_gatunku;
    }
    public Rasa() {
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

    public Integer getNr_gatunku() {
        return nr_gatunku;
    }

    public void setNr_gatunku(Integer nr_gatunku) {
        this.nr_gatunku = nr_gatunku;
    }


}

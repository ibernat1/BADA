package bdbt_bada_project.SpringApplication;

public class Kojec {
    private int nr_kojca;
    private int rozmiar;
    private int liczba_zwierzat;
    private Boolean czy_ocieplony;

    public int getNr_kojca() {
        return nr_kojca;
    }

    public void setNr_kojca(int nr_kojca) {
        this.nr_kojca = nr_kojca;
    }

    public int getRozmiar() {
        return rozmiar;
    }

    public void setRozmiar(int rozmiar) {
        this.rozmiar = rozmiar;
    }

    public int getLiczba_zwierzat() {
        return liczba_zwierzat;
    }

    public void setLiczba_zwierzat(int liczba_zwierzat) {
        this.liczba_zwierzat = liczba_zwierzat;
    }

    public Boolean getCzy_ocieplony() {
        return czy_ocieplony;
    }

    public void setCzy_ocieplony(Boolean czy_ocieplony) {
        this.czy_ocieplony = czy_ocieplony;
    }

    public Kojec(int nr_kojca, int rozmiar, int liczba_zwierzat, Boolean czy_ocieplony){
        super();
        this.nr_kojca=nr_kojca;
        this.rozmiar=rozmiar;
        this.liczba_zwierzat=liczba_zwierzat;
        this.czy_ocieplony=czy_ocieplony;
    }
}

package model;

public class Kolegij extends Tablica {
    public Long ID;
    public String naziv_kolegija;
    public Long studijID;

    public Kolegij(String naziv_kolegija, Long studijID) {
        this.naziv_kolegija = naziv_kolegija;
        this.studijID = studijID;
    }

    public Kolegij() {
        this.ID = null;
        this.naziv_kolegija = "";
        this.studijID = Long.valueOf(0);
    }

    public Long getID(){
        return ID;
    }
}

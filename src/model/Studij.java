package model;

public class Studij extends Tablica {
    public Long ID;
    public String naziv_studija;

    public Studij(String naziv_studija) {
        this.naziv_studija = naziv_studija;
    }

    public Studij(){
        this.ID = Long.valueOf(0);
        this.naziv_studija = "";
    }

    public Long getID(){
        return ID;
    }
}


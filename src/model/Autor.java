package model;

public class Autor extends Tablica {
    public Long ID;
    public String ime_autora;
    public String prezime_autora;

    public Autor(String ime_autora, String prezime_autora) {
        this.ime_autora = ime_autora;
        this.prezime_autora = prezime_autora;
    }

    public Autor() {
        this.ID = Long.valueOf(0);
        this.ime_autora = "";
        this.prezime_autora = "";
    }

    public Long getID(){
        return ID;
    }
}

package model;

public class Knjiga extends Tablica{
    public Long ID;
    public String naziv_knjige;
    public Long godina_izdanja;
    public String Kategorija;
    public String broj_stranica;
    public Long autorID;
    public Long kolegijID;

    public Knjiga(String naziv_knjige, Long godina_izdanja, String Kategorija, String broj_stranica, Long autorID, Long kolegijID) {
        this.naziv_knjige = naziv_knjige;
        this.godina_izdanja = godina_izdanja;
        this.Kategorija = Kategorija;
        this.broj_stranica = broj_stranica;
        this.autorID = autorID;
        this.kolegijID = kolegijID;
    }
    public Knjiga() {
        this.ID = Long.valueOf(0);
        this.naziv_knjige = "";
        this.godina_izdanja = Long.valueOf(0);
        this.Kategorija = "";
        this.broj_stranica = "";
        this.autorID = Long.valueOf(0);
        this.kolegijID = Long.valueOf(0);
    }

    public Long getID(){
        return ID;
    }
}

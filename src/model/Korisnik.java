package model;

public class Korisnik extends Tablica {
    public Long ID;
    public String korisnicko_ime;
    public String lozinka;
    public String vrsta_korisnika;
    public Long studentID;

    public Korisnik(String korisnicko_ime, String lozinka, String vrsta_korisnika, Long studentID) {
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
        this.vrsta_korisnika = vrsta_korisnika;
        this.studentID = studentID;
    }

    public Korisnik() {
        this.ID = Long.valueOf(0);
        this.korisnicko_ime = "";
        this.lozinka = "";
        this.vrsta_korisnika = "";
        this.studentID = Long.valueOf(0);
    }
    public Long getID(){
        return ID;
    }
}

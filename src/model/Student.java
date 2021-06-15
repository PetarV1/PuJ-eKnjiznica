package model;

public class Student extends Tablica {
    public Long ID;
    public String ime;
    public String prezime;
    public String broj_indeksa;
    public Float prosjek_ocjena;
    public Long studijID;

    public Student(String ime, String prezime, String broj_indeksa, Float prosjek_ocjena, Long studijID) {
        this.ime = ime;
        this.prezime = prezime;
        this.broj_indeksa = broj_indeksa;
        this.prosjek_ocjena = prosjek_ocjena;
        this.studijID = studijID;
    }
    public Student() {
        this.ID = Long.valueOf(0);
        this.ime = "";
        this.prezime = "";
        this.broj_indeksa = "";
        this.prosjek_ocjena = Float.valueOf(0);
        this.studijID = Long.valueOf(0);
    }
    public Long getID() { return ID; }
}



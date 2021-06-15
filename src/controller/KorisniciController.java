package controller;

import ispisivanjePoruka.Podaci;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.Korisnik;
import ispisivanjePoruka.Poruke;
import model.Tablica;
import java.net.URL;
import java.util.ResourceBundle;

public class KorisniciController implements Initializable, Poruke, Podaci {

    @FXML
    Label userDataLbl;
    @FXML
    private TableView <Object> KorisniciTbl;
    @FXML
    private TableColumn IDTblCol;
    @FXML
    private TableColumn KorisnickoImeTblCol;
    @FXML
    private TableColumn LozinkaTblCol;
    @FXML
    private TableColumn UlogaTblCol;
    @FXML
    private TableColumn StudentIDTblCol;

    @FXML
    private TextField KorisnickoImeTxt;
    @FXML
    private PasswordField LozinkaTxt;
    @FXML
    ChoiceBox<String> UlogaTxt;
    @FXML
    private TextField studentIDTxt;

    private Korisnik odabraniKorisnik;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> ulogeLista = FXCollections.observableArrayList();
        ulogeLista.addAll("","Admin","Student");
        this.UlogaTxt.setItems(ulogeLista);

        userDataLbl.setText(LoginController.logiraniKorisnik.vrsta_korisnika + " " + LoginController.logiraniKorisnik.korisnicko_ime.toUpperCase());
        try {
            ObservableList<Object> listaKorisnika = Tablica.dohvatiSve(Korisnik.class);
                    IDTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Korisnik, Long>, SimpleLongProperty>(){
                    @Override
                    public SimpleLongProperty call(TableColumn.CellDataFeatures<Korisnik,Long> korisnikLongCellDataFeatures) {
                    return new SimpleLongProperty(korisnikLongCellDataFeatures.getValue().getID());
                }}
                );
                    KorisnickoImeTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Korisnik, String>, SimpleStringProperty>(){
                    @Override
                    public SimpleStringProperty call(TableColumn.CellDataFeatures<Korisnik,String> korisnikLongCellDataFeatures) {
                    return new SimpleStringProperty(korisnikLongCellDataFeatures.getValue().korisnicko_ime);
                }}
                );
                    LozinkaTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Korisnik, String>, SimpleStringProperty>(){
                    @Override
                    public SimpleStringProperty call(TableColumn.CellDataFeatures<Korisnik,String> korisnikLongCellDataFeatures) {
                    return new SimpleStringProperty(korisnikLongCellDataFeatures.getValue().lozinka);
                }}
                );
                    UlogaTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Korisnik, String>, SimpleStringProperty>(){
                    @Override
                    public SimpleStringProperty call(TableColumn.CellDataFeatures<Korisnik,String> korisnikLongCellDataFeatures) {
                    return new SimpleStringProperty(korisnikLongCellDataFeatures.getValue().vrsta_korisnika);
                }}
                );
                    StudentIDTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Korisnik, Long>, SimpleLongProperty>(){
                    @Override
                    public SimpleLongProperty call(TableColumn.CellDataFeatures<Korisnik,Long> korisnikLongCellDataFeatures) {
                    return new SimpleLongProperty(korisnikLongCellDataFeatures.getValue().studentID);
                }}
            );
                KorisniciTbl.setItems(listaKorisnika);
            }
            catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void odaberiKorisnika(MouseEvent evt){
        this.odabraniKorisnik = (Korisnik) this.KorisniciTbl.getSelectionModel().getSelectedItem();
        if(this.odabraniKorisnik != null) {
            this.KorisnickoImeTxt.setText(this.odabraniKorisnik.korisnicko_ime);
            this.LozinkaTxt.setText(this.odabraniKorisnik.lozinka);
            this.UlogaTxt.setValue(this.odabraniKorisnik.vrsta_korisnika);
            this.studentIDTxt.setText(Long.toString(this.odabraniKorisnik.studentID));
        }
    }

    @FXML
    public void vratiKorisnikaNaNull(MouseEvent evt){
        this.odabraniKorisnik = null;
        KorisnickoImeTxt.setText("");
        LozinkaTxt.setText("");
        UlogaTxt.setValue("");
        studentIDTxt.setText("");

        ObservableList<Object> listaKorisnika = null;
        try {
            listaKorisnika = Tablica.dohvatiSve(Korisnik.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        KorisniciTbl.setItems(listaKorisnika);
    }

    @FXML
    public void spasiKorisnika(ActionEvent evt) {
        Korisnik k;

        if (this.odabraniKorisnik == null) {
            k = new Korisnik();
        } else {
            k = this.odabraniKorisnik;
        }

        k.korisnicko_ime = KorisnickoImeTxt.getText();
        k.lozinka = LozinkaTxt.getText();
        k.vrsta_korisnika = UlogaTxt.getValue();

        if (KorisnickoImeTxt.getText() == "" || LozinkaTxt.getText() == "" || UlogaTxt.getValue() == "") {
            nepopunjeniPodaci();
        }
        else
        {
            if (studentIDTxt.getText() == "") {
                k.studentID = Long.valueOf(0);
            } else {
                k.studentID = Long.valueOf(studentIDTxt.getText());
            }

            KorisnickoImeTxt.setText("");
            LozinkaTxt.setText("");
            UlogaTxt.setValue("");
            studentIDTxt.setText("");

            try {
                if (this.odabraniKorisnik == null)
                    k.spasi();
                else
                    k.uredi();

                ObservableList<Object> listaKorisnika = Tablica.dohvatiSve(Korisnik.class);
                KorisniciTbl.setItems(listaKorisnika);
            } catch (Exception e) {
                System.out.println("Nastala je greška: " + e.getMessage());
            }
            ispisiPoruku();
        }
    }
    @FXML
    public void pobrisiKorisnika(ActionEvent evt) {
        if(this.odabraniKorisnik != null){
            try {
                this.odabraniKorisnik.pobrisi();
                ObservableList<Object> listaKorisnika = Tablica.dohvatiSve(Korisnik.class);
                KorisniciTbl.setItems(listaKorisnika);
            } catch (Exception e) {
                System.out.println("Nastala je greška: "+e.getMessage());
            }
        }
    }

    @Override
    public void ispisiPoruku() {
        Dialog dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Potvrda");
        dialog.setHeaderText("Uspješno ste dodali korisnika!");
        dialog.showAndWait();
    }

    @Override
    public void nepopunjeniPodaci() {
        Dialog dialog = new Alert(Alert.AlertType.WARNING);
        dialog.setTitle("Upozorenje!");
        dialog.setHeaderText("Niste popunili sve podatke o korisniku.");
        dialog.showAndWait();
    }
}

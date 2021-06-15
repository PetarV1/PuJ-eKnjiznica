package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.Knjiga;
import model.Student;
import model.Studij;
import model.Tablica;
import ispisivanjePoruka.Poruke;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable, Poruke{
    @FXML
    Label ImePrezimeLbl;
    @FXML
    Label KorisnickoImeLbl;
    @FXML
    Label StudijLbl;
    @FXML
    Label ProsjekLbl;

    @FXML
    TableView StudentKnjigeTbl;
    @FXML
    TableColumn NazivKnjigeTblCol;
    @FXML
    TableColumn BrStrKnjigeTblCol;
    @FXML
    TableColumn KategorijaTblCol;

    private Knjiga odabranaKnjiga = new Knjiga();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        KorisnickoImeLbl.setText(LoginController.logiraniKorisnik.korisnicko_ime);
        try {
            Integer ID = Math.toIntExact(LoginController.logiraniKorisnik.studentID);
            Student student = (Student) Tablica.dohvati(Student.class, ID);
            Integer st = Math.toIntExact(student.studijID);
            Studij studij = (Studij) Tablica.dohvati(Studij.class, st);
            ImePrezimeLbl.setText(student.ime + " " + student.prezime);
            StudijLbl.setText(studij.naziv_studija);
            ProsjekLbl.setText(String.valueOf(student.prosjek_ocjena));

            ObservableList<Object> listaKnjiga = Tablica.dohvatiSve(Knjiga.class);

            NazivKnjigeTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Knjiga, String>, SimpleStringProperty>(){
                @Override
                public SimpleStringProperty call(TableColumn.CellDataFeatures<Knjiga,String> knjigaStringCellDataFeatures) {
                    return new SimpleStringProperty(knjigaStringCellDataFeatures.getValue().naziv_knjige);
                }}
            );
            BrStrKnjigeTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Knjiga, String>, SimpleStringProperty>(){
                @Override
                public SimpleStringProperty call(TableColumn.CellDataFeatures<Knjiga, String> knjigaStringCellDataFeatures) {
                    return new SimpleStringProperty(knjigaStringCellDataFeatures.getValue().broj_stranica);
                }}
            );
            KategorijaTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Knjiga, String>, SimpleStringProperty>(){
                @Override
                public SimpleStringProperty call(TableColumn.CellDataFeatures<Knjiga,String> knjigaStringCellDataFeatures) {
                    return new SimpleStringProperty(knjigaStringCellDataFeatures.getValue().Kategorija);
                }}
            );
            StudentKnjigeTbl.setItems(listaKnjiga);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void odaberiKnjigu(MouseEvent evt){
        this.odabranaKnjiga = (Knjiga) this.StudentKnjigeTbl.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void OtvoriPDF (MouseEvent evt) {
        try{
            Integer ID = Math.toIntExact(LoginController.logiraniKorisnik.studentID);
            Student student = (Student) Tablica.dohvati(Student.class, ID);
            Integer st = Math.toIntExact(student.studijID);
            Studij studij = (Studij) Tablica.dohvati(Studij.class, st);

            if(odabranaKnjiga.naziv_knjige == null)
            System.out.println("Niste odabrali knjigu");
            if(odabranaKnjiga.naziv_knjige != null) {
                if (odabranaKnjiga.kolegijID != 0)
                {
                    Desktop.getDesktop().open(new File("src/knjige/" + odabranaKnjiga.naziv_knjige + ".pdf"));
                }
                else if(odabranaKnjiga.kolegijID == 0 && student.prosjek_ocjena >= 4.5)
                {
                    Desktop.getDesktop().open(new File("src/knjige/" + odabranaKnjiga.naziv_knjige + ".pdf"));
                }
                else ispisiPoruku();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ispisiPoruku() {
        Dialog dialog = new Alert(AlertType.ERROR);
        dialog.setTitle("Greška!");
        dialog.setHeaderText("Vaš prosjek mora biti veći od 4.5 da biste čitali ovu knjigu.");
        dialog.showAndWait();
    }
}
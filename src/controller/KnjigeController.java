package controller;

import ispisivanjePoruka.PodaciPoruke2;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.Autor;
import model.Knjiga;
import model.Tablica;
import java.net.URL;
import java.util.ResourceBundle;

public class KnjigeController implements Initializable, PodaciPoruke2 {
    //gornja tablica(Autori)
    @FXML
    TableView autorTbl;
    @FXML
    TableColumn IDTblCol;
    @FXML
    TableColumn ImeTblCol;
    @FXML
    TableColumn PrezimeTblCol;
    @FXML
    TextField ImeAutoraTxt;
    @FXML
    TextField PrezimeAutoraTxt;

    private Autor odabraniAutor;
    //donja tablica(Knjige)
    @FXML
    TableView AdminKnjigeTbl;
    @FXML
    TableColumn NazivKnjigeTblCol;
    @FXML
    TableColumn BrStrKnjigeTblCol;
    @FXML
    TableColumn KategorijaTblCol;
    @FXML
    TableColumn IDAutorTblCol;
    @FXML
    TextField NazivKnjigeTxt;
    @FXML
    TextField KategorijaTxt;
    @FXML
    TextField BrojStranicaTxt;
    @FXML
    TextField AutorIDTxt;
    @FXML
    TableColumn IDKolegijTblCol;
    @FXML
    TextField KolegijIDTxt;

    private Knjiga odabranaKnjiga;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //gornja tablica(Autori)
        try {
            ObservableList<Object> listaAutora = Tablica.dohvatiSve(Autor.class);
            IDTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Autor, Long>, SimpleLongProperty>(){
                @Override
                public SimpleLongProperty call(TableColumn.CellDataFeatures<Autor,Long> autorLongCellDataFeatures) {
                    return new SimpleLongProperty(autorLongCellDataFeatures.getValue().getID());
                }}
            );
            ImeTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Autor, String>, SimpleStringProperty>(){
                @Override
                public SimpleStringProperty call(TableColumn.CellDataFeatures<Autor,String> autorStringCellDataFeatures) {
                    return new SimpleStringProperty(autorStringCellDataFeatures.getValue().ime_autora);
                }}
            );
            PrezimeTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Autor, String>, SimpleStringProperty>(){
                @Override
                public SimpleStringProperty call(TableColumn.CellDataFeatures<Autor,String> autorStringCellDataFeatures) {
                    return new SimpleStringProperty(autorStringCellDataFeatures.getValue().prezime_autora);
                }}
            );
            autorTbl.setItems(listaAutora);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //donja tablica(Knjige)
        try {
            ObservableList<Object> listaKnjiga = Tablica.dohvatiSve(Knjiga.class);

            NazivKnjigeTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Knjiga, String>, SimpleStringProperty>(){
                @Override
                public SimpleStringProperty call(TableColumn.CellDataFeatures<Knjiga,String> knjigaStringCellDataFeatures) {
                    return new SimpleStringProperty(knjigaStringCellDataFeatures.getValue().naziv_knjige);
                }}
            );
            BrStrKnjigeTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Knjiga, String>, SimpleStringProperty>(){
                @Override
                public SimpleStringProperty call(TableColumn.CellDataFeatures<Knjiga,String> knjigaStringCellDataFeatures) {
                    return new SimpleStringProperty(knjigaStringCellDataFeatures.getValue().broj_stranica);
                }}
            );
            KategorijaTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Knjiga, String>, SimpleStringProperty>(){
                @Override
                public SimpleStringProperty call(TableColumn.CellDataFeatures<Knjiga,String> knjigaStringCellDataFeatures) {
                    return new SimpleStringProperty(knjigaStringCellDataFeatures.getValue().Kategorija);
                }}
            );
            IDAutorTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Knjiga, Long>, SimpleLongProperty>(){
                @Override
                public SimpleLongProperty call(TableColumn.CellDataFeatures<Knjiga,Long> knjigaLongCellDataFeatures) {
                    return new SimpleLongProperty(knjigaLongCellDataFeatures.getValue().autorID);
                }}
            );
            IDKolegijTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Knjiga, Long>, SimpleLongProperty>(){
                @Override
                public SimpleLongProperty call(TableColumn.CellDataFeatures<Knjiga,Long> knjigaLongCellDataFeatures) {
                    return new SimpleLongProperty(knjigaLongCellDataFeatures.getValue().kolegijID);
                }}
            );
            AdminKnjigeTbl.setItems(listaKnjiga);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////FUNKCIJE ZA AUTORA//////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    @FXML
    public void odaberiAutora(MouseEvent evt){
        this.odabraniAutor = (Autor) this.autorTbl.getSelectionModel().getSelectedItem();
        if(this.odabraniAutor != null) {
            this.ImeAutoraTxt.setText(this.odabraniAutor.ime_autora);
            this.PrezimeAutoraTxt.setText(this.odabraniAutor.prezime_autora);
        }
    }

    @FXML
    public void vratiAutoraNaNull(MouseEvent evt){
        this.odabraniAutor = null;
        ImeAutoraTxt.setText("");
        PrezimeAutoraTxt.setText("");

        ObservableList<Object> listaAutora = null;
        try {
            listaAutora = Tablica.dohvatiSve(Autor.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        autorTbl.setItems(listaAutora);
    }

    @FXML
    public void spasiAutora(ActionEvent evt) {
        Autor a;
        if (this.odabraniAutor == null) {
            a = new Autor();
        } else {
            a = this.odabraniAutor;
        }

        a.ime_autora = ImeAutoraTxt.getText();
        a.prezime_autora = PrezimeAutoraTxt.getText();

        if(ImeAutoraTxt.getText() == "" || PrezimeAutoraTxt.getText() == ""){
            nepopunjeniPodaciAutor();
        }
        else {
            ImeAutoraTxt.setText("");
            PrezimeAutoraTxt.setText("");

            try {
                if (this.odabraniAutor == null)
                    a.spasi();
                else
                    a.uredi();
                ObservableList<Object> listaAutora = Tablica.dohvatiSve(Autor.class);
                autorTbl.setItems(listaAutora);
            } catch (Exception e) {
                System.out.println("Nastala je greška: " + e.getMessage());
            }
            ispisiPorukuAutor();
        }
    }

    @FXML
    public void pobrisiAutora(ActionEvent evt) {
        if(this.odabraniAutor != null){
            try {
                this.odabraniAutor.pobrisi();

                ImeAutoraTxt.setText("");
                PrezimeAutoraTxt.setText("");

                ObservableList<Object> listaAutora = Tablica.dohvatiSve(Autor.class);
                autorTbl.setItems(listaAutora);
            } catch (Exception e) {
                System.out.println("Nastala je greška: "+e.getMessage());
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////FUNKCIJE ZA KNJIGU//////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    @FXML
    public void odaberiKnjigu(MouseEvent evt){
        this.odabranaKnjiga = (Knjiga) this.AdminKnjigeTbl.getSelectionModel().getSelectedItem();
        if(this.odabranaKnjiga != null) {
            this.NazivKnjigeTxt.setText(this.odabranaKnjiga.naziv_knjige);
            this.BrojStranicaTxt.setText(this.odabranaKnjiga.broj_stranica);
            this.KategorijaTxt.setText(this.odabranaKnjiga.Kategorija);
            this.AutorIDTxt.setText(Long.toString(this.odabranaKnjiga.autorID));
            this.KolegijIDTxt.setText(Long.toString(this.odabranaKnjiga.kolegijID));
        }
    }

    @FXML
    public void vratiKnjiguNaNull(MouseEvent evt){
        this.odabranaKnjiga = null;
        NazivKnjigeTxt.setText("");
        BrojStranicaTxt.setText("");
        KategorijaTxt.setText("");
        AutorIDTxt.setText(Long.toString(0));
        KolegijIDTxt.setText(Long.toString(0));

        ObservableList<Object> listaKnjiga = null;
        try {
            listaKnjiga = Tablica.dohvatiSve(Knjiga.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdminKnjigeTbl.setItems(listaKnjiga);
    }

    @FXML
    public void spasiKnjigu(ActionEvent evt) {
        Knjiga k;

        if (this.odabranaKnjiga == null) {
            k = new Knjiga();
        } else {
            k = this.odabranaKnjiga;
        }

        k.naziv_knjige = NazivKnjigeTxt.getText();
        k.broj_stranica = BrojStranicaTxt.getText();
        k.Kategorija = KategorijaTxt.getText();
        if(AutorIDTxt.getText() != "") {
            k.autorID = Long.valueOf(AutorIDTxt.getText());
        }
        if(KolegijIDTxt.getText() != "") {
            k.kolegijID = Long.valueOf(KolegijIDTxt.getText());
        }

        if(NazivKnjigeTxt.getText() == "" || KategorijaTxt.getText() == ""
                || AutorIDTxt.getText() == "" || KolegijIDTxt.getText() == "") {
            nepopunjeniPodaciKnjiga();
        }
        else {
            NazivKnjigeTxt.setText("");
            BrojStranicaTxt.setText("");
            KategorijaTxt.setText("");
            AutorIDTxt.setText(String.valueOf(0));
            KolegijIDTxt.setText(String.valueOf(0));

            try {
                if (this.odabranaKnjiga == null)
                    k.spasi();
                else
                    k.uredi();
                ObservableList<Object> listaKnjiga = Tablica.dohvatiSve(Knjiga.class);
                AdminKnjigeTbl.setItems(listaKnjiga);
            } catch (Exception e) {
                System.out.println("Nastala je greška: " + e.getMessage());
            }
            ispisiPorukuKnjiga();
        }
    }

    @FXML
    public void pobrisiKnjigu(ActionEvent evt) {
        if(this.odabranaKnjiga != null){
            try {
                this.odabranaKnjiga.pobrisi();
                ObservableList<Object> listaKnjiga = Tablica.dohvatiSve(Knjiga.class);
                AdminKnjigeTbl.setItems(listaKnjiga);
            } catch (Exception e) {
                System.out.println("Nastala je greška: "+e.getMessage());
            }
        }
    }

    @Override
    public void ispisiPorukuAutor() {
        Dialog dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Potvrda");
        dialog.setHeaderText("Uspješno ste dodali autora!");
        dialog.showAndWait();
    }

    @Override
    public void ispisiPorukuKnjiga() {
        Dialog dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Potvrda");
        dialog.setHeaderText("Uspješno ste dodali knjigu!");
        dialog.showAndWait();
    }

    @Override
    public void nepopunjeniPodaciAutor() {
        Dialog dialog = new Alert(Alert.AlertType.WARNING);
        dialog.setTitle("Upozorenje!");
        dialog.setHeaderText("Niste popunili sve podatke o autoru.");
        dialog.showAndWait();
    }

    @Override
    public void nepopunjeniPodaciKnjiga() {
        Dialog dialog = new Alert(Alert.AlertType.WARNING);
        dialog.setTitle("Upozorenje!");
        dialog.setHeaderText("Niste popunili sve podatke o knjizi.");
        dialog.showAndWait();
    }
}

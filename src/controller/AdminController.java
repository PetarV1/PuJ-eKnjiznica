package controller;

import ispisivanjePoruka.Podaci;
import ispisivanjePoruka.Poruke;
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
import model.Kolegij;
import model.Tablica;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable, Podaci, Poruke {
    @FXML
    TableView <Object> kolegijiTbl;
    @FXML
    TableColumn IDTblCol;
    @FXML
    TableColumn NazivKolegijaTblCol;
    @FXML
    TableColumn StudijIDTblCol;
    @FXML
    TextField NazivKolegijaTxt;
    @FXML
    ChoiceBox<Long> StudijiTxt;

    private Kolegij odabraniKolegij;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Long> studijiLista = FXCollections.observableArrayList();
        studijiLista.addAll( Long.valueOf(1), Long.valueOf(2), Long.valueOf(3));
        this.StudijiTxt.setItems(studijiLista);
        try {
            ObservableList<Object> listaKolegija = Tablica.dohvatiSve(Kolegij.class);

            IDTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Kolegij, Long>, SimpleLongProperty>(){
                @Override
                public SimpleLongProperty call(TableColumn.CellDataFeatures<Kolegij,Long> kolegijLongCellDataFeatures) {
                    return new SimpleLongProperty(kolegijLongCellDataFeatures.getValue().getID());
                }}
            );

            NazivKolegijaTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Kolegij, String>, SimpleStringProperty>(){
                @Override
                public SimpleStringProperty call(TableColumn.CellDataFeatures<Kolegij,String> kolegijStringCellDataFeatures) {
                    return new SimpleStringProperty(kolegijStringCellDataFeatures.getValue().naziv_kolegija);
                }}
            );

            StudijIDTblCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Kolegij, Long>, SimpleLongProperty>(){
                @Override
                public SimpleLongProperty call(TableColumn.CellDataFeatures<Kolegij,Long> kolegijLongCellDataFeatures) {
                    return new SimpleLongProperty(kolegijLongCellDataFeatures.getValue().studijID);
                }}
            );
            kolegijiTbl.setItems(listaKolegija);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void odaberiKolegij(MouseEvent evt){
        this.odabraniKolegij = (Kolegij) this.kolegijiTbl.getSelectionModel().getSelectedItem();
        if(this.odabraniKolegij != null) {
            this.NazivKolegijaTxt.setText(this.odabraniKolegij.naziv_kolegija);
            this.StudijiTxt.setValue(this.odabraniKolegij.studijID);
        }
    }

    @FXML
    public void vratiKolegijNaNull(MouseEvent evt){
        this.odabraniKolegij = null;
        NazivKolegijaTxt.setText(null);
        StudijiTxt.setValue(null);

        ObservableList<Object> listaKolegija = null;
        try {
            listaKolegija = Tablica.dohvatiSve(Kolegij.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        kolegijiTbl.setItems(listaKolegija);
    }

    @FXML
    public void spasiKolegij(ActionEvent evt) {
        Kolegij k;
        if (this.odabraniKolegij == null) {
            k = new Kolegij();
        } else {
            k = this.odabraniKolegij;
        }

        k.naziv_kolegija = NazivKolegijaTxt.getText();
        k.studijID = StudijiTxt.getValue();

        if (NazivKolegijaTxt.getText() == "" || StudijiTxt.getValue() == null){
            nepopunjeniPodaci();
        }
        else {
            NazivKolegijaTxt.setText(null);
            StudijiTxt.setValue(null);

            try {
                if (this.odabraniKolegij == null)
                    k.spasi();
                else
                    k.uredi();
                ObservableList<Object> listaKolegija = Tablica.dohvatiSve(Kolegij.class);
                kolegijiTbl.setItems(listaKolegija);
            } catch (Exception e) {
                System.out.println("Nastala je greška: " + e.getMessage());
            }
            ispisiPoruku();
        }
    }
    @FXML
    public void pobrisiKolegij(ActionEvent evt) {
        if(this.odabraniKolegij != null){
            try {
                this.odabraniKolegij.pobrisi();
                ObservableList<Object> listaKolegija = Tablica.dohvatiSve(Kolegij.class);
                kolegijiTbl.setItems(listaKolegija);
            } catch (Exception e) {
                System.out.println("Nastala je greška: "+e.getMessage());
            }
        }
    }

    @Override
    public void nepopunjeniPodaci() {
        Dialog dialog = new Alert(Alert.AlertType.WARNING);
        dialog.setTitle("Upozorenje!");
        dialog.setHeaderText("Niste popunili sve podatke o kolegiju.");
        dialog.showAndWait();
    }

    @Override
    public void ispisiPoruku() {
        Dialog dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Potvrda");
        dialog.setHeaderText("Uspješno ste dodali kolegij!");
        dialog.showAndWait();
    }
}

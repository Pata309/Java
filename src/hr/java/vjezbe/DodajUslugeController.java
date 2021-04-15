package hr.java.vjezbe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Prikazuje prozor s pretragom automobila
 * 
 * @author Patricija Kuže
 *
 */

public class DodajUslugeController {

	final int ID_TIPA = 1;
	

	private static ArrayList<Usluga> listaUsluga;

	@FXML
	private TextField naslovUslugeTextField;
	@FXML
	private TextField opisUslugeTextField;
	@FXML
	private TextField cijenaUslugeTextField;
	@FXML
	private ComboBox<Stanje> stanjeUslugaTextField;
	

	/**
	 * Inicijalizira scenu
	 */
	@FXML
	private void initialize() {
		stanjeUslugaTextField.getItems().addAll(Stanje.values());
		
	}

	/**
	 * Pretrazuje automobile
	 */
	public void dodaj() {
	
		if(provjeri().equals("")) {
			dodajNovuUslugu();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Pogreška pri spremanju");
			alert.setContentText(provjeri());
			alert.showAndWait();
		}
	}
	
	/**
	 * Dodaje novu uslugu
	 */
	private void dodajNovuUslugu() {
		String naslov = naslovUslugeTextField.getText();
		String opis = opisUslugeTextField.getText();
		BigDecimal cijena = new BigDecimal(cijenaUslugeTextField.getText());
		int idStanja = stanjeUslugaTextField.getSelectionModel().getSelectedIndex();
		Long id = pronadiId();
		
		Stanje stanje = null;
		if (idStanja == 0) {
			stanje = Stanje.NOVO;
		} else if (idStanja == 1) {
			stanje = Stanje.IZVRSNO;
		} else if (idStanja == 2) {
			stanje = Stanje.RABLJENO;
		} else if (idStanja == 3) {
			stanje = Stanje.NEISPRAVNO;
		}
		
		Usluga usluga = new Usluga(naslov, opis, cijena, stanje, id);
		
		try {
			BazaPodataka.spremiUslugu(usluga);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	/**
	 * Provjerava jesu li sva polja popunjena
	 * @return
	 */
	private String provjeri() {
		
		String greske = "";
		if(naslovUslugeTextField.getText().isEmpty()) {
			greske += "Naslov usluge ne smije biti prazan!\n";
		}
		if(opisUslugeTextField.getText().isEmpty()) {
			greske += "Opis usluge ne smije biti prazan!\n";
		}
		if(cijenaUslugeTextField.getText().isEmpty()) {
			greske += "Cijena usluge ne smije biti prazna!\n";
		}
		if(stanjeUslugaTextField.getSelectionModel().isEmpty()) {
			greske += "Stanje usluge ne smije biti prazno!\n";
		}
		return greske;
		
	}
	
	/**
	 * Provjerava je li objekt instanca klase Usluga
	 * @return
	 */
	private long pronadiId() {
		try {
			listaUsluga = (ArrayList<Usluga>) BazaPodataka.dohvatiUsluge();
		} catch (BazaPodatakaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaUsluga.isEmpty() ? 1 : (listaUsluga.get(listaUsluga.size()-1).getId())+1;
	}
}

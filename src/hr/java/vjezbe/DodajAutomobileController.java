package hr.java.vjezbe;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Prikazuje prozor s pretragom automobila
 * 
 * @author Patricija Kuže
 *
 */

public class DodajAutomobileController {

	final int ID_TIPA = 2;
	
	static ObservableList<Automobil> obsListaAutomobila;

	private static ArrayList<Automobil> listaAutomobila;

	@FXML
	private TextField naslovAutomobilaTextField;
	@FXML
	private TextField opisAutomobilaTextField;
	@FXML
	private TextField snagaAutomobilaTextField;
	@FXML
	private TextField cijenaAutomobilaTextField;
	@FXML
	private ComboBox<Stanje> stanjeAutomobilaTextField;
	

	/**
	 * Inicijalizira scenu
	 */
	@FXML
	private void initialize() {
		stanjeAutomobilaTextField.getItems().addAll(Stanje.values());
		
	}

	/**
	 * Pretrazuje automobile
	 */
	public void dodaj() {
	
		if(provjeri().equals("")) {
			dodajNoviAutomobil();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Pogreška pri spremanju");
			alert.setContentText(provjeri());
			alert.showAndWait();
		}
	}
	
	/**
	 * Dodaje novi automobil
	 */
	private void dodajNoviAutomobil() {
		String naslov = naslovAutomobilaTextField.getText();
		String opis = opisAutomobilaTextField.getText();
		BigDecimal cijena = new BigDecimal(cijenaAutomobilaTextField.getText());
		BigDecimal snaga = new BigDecimal(snagaAutomobilaTextField.getText());
		int idStanja = stanjeAutomobilaTextField.getSelectionModel().getSelectedIndex();
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
		
		Automobil automobil = new Automobil(naslov, opis, snaga, stanje, snaga, id);
		
		try {
			BazaPodataka.spremiAutomobil(automobil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Provjerava je li unesena trazena vrijednost
	 * @return
	 */
	private String provjeri() {
		
		String greske = "";
		if(naslovAutomobilaTextField.getText().isEmpty()) {
			greske += "Naslov automobila ne smije biti prazan!\n";
		}
		if(opisAutomobilaTextField.getText().isEmpty()) {
			greske += "Opis automobila ne smije biti prazan!\n";
		}
		if(snagaAutomobilaTextField.getText().isEmpty()) {
			greske += "Snaga automobila ne smije biti prazna!\n";
		}
		if(cijenaAutomobilaTextField.getText().isEmpty()) {
			greske += "Cijena automobila ne smije biti prazna!\n";
		}
		if(stanjeAutomobilaTextField.getSelectionModel().isEmpty()) {
			greske += "Stanje automobila ne smije biti prazno!\n";
		}
		return greske;
		
	}
	
	/**
	 * Trazi instancu automobila
	 * @return
	 */
	private long pronadiId() {
		try {
			listaAutomobila = (ArrayList<Automobil>) BazaPodataka.dohvatiAutomobile();
		} catch (BazaPodatakaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (listaAutomobila.get(listaAutomobila.size()-1).getId())+1;
	}
}

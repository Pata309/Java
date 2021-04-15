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
import hr.java.vjezbe.entitet.PrivatniKorisnik;
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

public class DodajPrivatneKorisnikeController {

	final int ID_TIPA = 1;
	
	private static ArrayList<PrivatniKorisnik> listaPrivatnihKorisnika;

	@FXML
	private TextField imeKorisnikaTextField;
	@FXML
	private TextField prezimeKorisnikaTextField;
	@FXML
	private TextField emailKorisnikaTextField;
	@FXML
	private TextField telefonKorisnikaTextField;

	/**
	 * Inicijalizira scenu
	 */
	@FXML
	private void initialize() {
		
	}

	/**
	 * Pretrazuje automobile
	 */
	public void dodaj() {
	
		if(provjeri().equals("")) {
			dodajNovogPrivatnogKorisnika();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Pogreška pri spremanju");
			alert.setContentText(provjeri());
			alert.showAndWait();
		}
	}
	
	/**
	 * Dodaje novog privatnog korisnika
	 */
	private void dodajNovogPrivatnogKorisnika() {
		String ime = imeKorisnikaTextField.getText();
		String prezime = prezimeKorisnikaTextField.getText();
		String email = emailKorisnikaTextField.getText();
		String telefon = telefonKorisnikaTextField.getText();
		Long id = pronadiId();
		
		PrivatniKorisnik privatniKorisnik = new PrivatniKorisnik(ime, prezime, email, telefon, id);
		
		try {
			BazaPodataka.spremiPrivatnogKorisnika(privatniKorisnik);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Provjerava jesu li unesena trazena polja
	 * @return
	 */
	private String provjeri() {
		
		String greske = "";
		if(imeKorisnikaTextField.getText().isEmpty()) {
			greske += "Ime korisnika ne smije biti prazno!\n";
		}
		if(prezimeKorisnikaTextField.getText().isEmpty()) {
			greske += "Prezime korisnika ne smije biti prazno!\n";
		}
		if(emailKorisnikaTextField.getText().isEmpty()) {
			greske += "Email korisnika ne smije biti prazan!\n";
		}
		if(telefonKorisnikaTextField.getText().isEmpty()) {
			greske += "Telefon korisnika ne smije biti prazan!\n";
		}
		
		return greske;
		
	}
	
	/**
	 * Provjerava je li objekt instanca PrivatnogKorisnika
	 * @return
	 */
	private long pronadiId() {
		try {
			listaPrivatnihKorisnika = (ArrayList<PrivatniKorisnik>) BazaPodataka.dohvatiPrivatneKorisnike();
		} catch (BazaPodatakaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (listaPrivatnihKorisnika.get(listaPrivatnihKorisnika.size()-1).getId())+1;
	}
}

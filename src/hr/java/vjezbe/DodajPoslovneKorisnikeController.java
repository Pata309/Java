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
import hr.java.vjezbe.entitet.PoslovniKorisnik;
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

public class DodajPoslovneKorisnikeController {

	final int ID_TIPA = 2;
	
	private static ArrayList<PoslovniKorisnik> listaPoslovnihKorisnika;

	@FXML
	private TextField nazivKorisnikaTextField;
	@FXML
	private TextField webKorisnikaTextField;
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
			dodajNovogPoslovnogKorisnika();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Pogreška pri spremanju");
			alert.setContentText(provjeri());
			alert.showAndWait();
		}
	}
	/**
	 * Dodaje novog poslovnog korisnika
	 */
	private void dodajNovogPoslovnogKorisnika() {
		String naziv = nazivKorisnikaTextField.getText();
		String web = webKorisnikaTextField.getText();
		String email = emailKorisnikaTextField.getText();
		String telefon = telefonKorisnikaTextField.getText();
		Long id = pronadiId();
		
		PoslovniKorisnik poslovniKorisnik = new PoslovniKorisnik(email, telefon, naziv, web, id);
		try {
			BazaPodataka.spremiPoslovnogKorisnika(poslovniKorisnik);
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
		if(nazivKorisnikaTextField.getText().isEmpty()) {
			greske += "Naziv korisnika ne smije biti prazno!\n";
		}
		if(webKorisnikaTextField.getText().isEmpty()) {
			greske += "Web korisnika ne smije biti prazno!\n";
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
	 * Trazi instancu poslovnog korisnika
	 * @return
	 */
	private long pronadiId() {
		try {
			listaPoslovnihKorisnika = (ArrayList<PoslovniKorisnik>) BazaPodataka.dohvatiPoslovneKorisnike();
		} catch (BazaPodatakaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaPoslovnihKorisnika.isEmpty() ? 1 :(listaPoslovnihKorisnika.get(listaPoslovnihKorisnika.size()-1).getId())+1;
	}
}

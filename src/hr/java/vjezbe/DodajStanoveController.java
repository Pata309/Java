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
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
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

public class DodajStanoveController {

	final int ID_TIPA = 3;
	

	private static ArrayList<Stan> listaStanova;

	@FXML
	private TextField naslovStanaTextField;
	@FXML
	private TextField opisStanaTextField;
	@FXML
	private TextField kvadraturaStanaTextField;
	@FXML
	private TextField cijenaStanaTextField;
	@FXML
	private ComboBox<Stanje> stanjeStanaTextField;
	

	/**
	 * Inicijalizira scenu
	 */
	@FXML
	private void initialize() {
		stanjeStanaTextField.getItems().addAll(Stanje.values());
		
	}

	/**
	 * Pretrazuje automobile
	 */
	public void dodaj() {
	
		if(provjeri().equals("")) {
			dodajNoviStan();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Pogreška pri spremanju");
			alert.setContentText(provjeri());
			alert.showAndWait();
		}
	}
	
	/**
	 * Dodaje novi stan
	 */
	private void dodajNoviStan() {
		String naslov = naslovStanaTextField.getText();
		String opis = opisStanaTextField.getText();
		BigDecimal cijena = new BigDecimal(cijenaStanaTextField.getText());
		BigDecimal kvadratura = new BigDecimal(kvadraturaStanaTextField.getText());
		int idStanja = stanjeStanaTextField.getSelectionModel().getSelectedIndex();
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
		
		Stan stan = new Stan(naslov, opis, cijena, stanje, kvadratura, id);
		
		try {
			BazaPodataka.spremiStan(stan);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	/**
	 * Provjerava jesu li sva trazena polja unesena
	 * @return
	 */
	private String provjeri() {
		
		String greske = "";
		if(naslovStanaTextField.getText().isEmpty()) {
			greske += "Naslov stana ne smije biti prazan!\n";
		}
		if(opisStanaTextField.getText().isEmpty()) {
			greske += "Opis stana ne smije biti prazan!\n";
		}
		if(kvadraturaStanaTextField.getText().isEmpty()) {
			greske += "Kvadratura stana ne smije biti prazna!\n";
		}
		if(cijenaStanaTextField.getText().isEmpty()) {
			greske += "Cijena stana ne smije biti prazna!\n";
		}
		if(stanjeStanaTextField.getSelectionModel().isEmpty()) {
			greske += "Stanje stana ne smije biti prazno!\n";
		}
		return greske;
		
	}
	
	/**
	 * Provjerava je li objekt instanca klase Stan
	 * @return
	 */
	private long pronadiId() {
		try {
			listaStanova = (ArrayList<Stan>) BazaPodataka.dohvatiStanove();
		} catch (BazaPodatakaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (listaStanova.get(listaStanova.size()-1).getId())+1;
	}
}

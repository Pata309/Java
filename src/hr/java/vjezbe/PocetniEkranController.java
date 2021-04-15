package hr.java.vjezbe;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Prikazuje pocetni ekran
 * 
 * @author Patricija Kuže
 *
 */

public class PocetniEkranController {

	/*
	 * Prikazuje pretragu automobila
	 */
	@FXML
	public void prikaziPretraguAutomobila() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("Automobili.fxml"));
			Main.setCenterPane(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prikazuje pretragu stanova
	 */
	@FXML
	public void prikaziPretraguStanova() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("Stanovi.fxml"));
			Main.setCenterPane(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prikazuje pretragu usluga
	 */
	@FXML
	public void prikaziPretraguUsluga() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("Usluge.fxml"));
			Main.setCenterPane(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prikazuje pretragu privatnih korisnika
	 */
	@FXML
	public void prikaziPretraguPrivatnihKorisnika() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("PrivatniKorisnici.fxml"));
			Main.setCenterPane(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prikazuje pretragu poslovnih korisnika
	 */
	@FXML
	public void prikaziPretraguPoslovnihKorisnika() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("PoslovniKorisnici.fxml"));
			Main.setCenterPane(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Prikazuje pretragu automobila
	 */
	@FXML
	public void dodavanjeAutomobila() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("DodajAutomobile.fxml"));
			Main.setCenterPane(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prikazuje pretragu stanova
	 */
	@FXML
	public void dodavanjeStanova() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("DodajStanove.fxml"));
			Main.setCenterPane(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prikazuje pretragu usluga
	 */
	@FXML
	public void dodavanjeUsluga() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("DodajUsluge.fxml"));
			Main.setCenterPane(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prikazuje pretragu privatnih korisnika
	 */
	@FXML
	public void dodavanjePrivatnihKorisnika() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("DodajPrivatneKorisnike.fxml"));
			Main.setCenterPane(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prikazuje pretragu poslovnih korisnika
	 */
	@FXML
	public void dodavanjePoslovnihKorisnika() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("DodajPoslovneKorisnike.fxml"));
			Main.setCenterPane(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

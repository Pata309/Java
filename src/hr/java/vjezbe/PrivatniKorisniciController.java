package hr.java.vjezbe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * Prikazuje privatne korisnike
 * 
 * @author Patricija Kuže
 *
 */
public class PrivatniKorisniciController {

	static ObservableList<PrivatniKorisnik> obsListaPrivatnihKorisnika;
	private static ArrayList<PrivatniKorisnik> listaPrivatnihKorisnika;

	@FXML
	private TextField imeKorisnikaTextField;
	@FXML
	private TextField prezimeKorisnikaTextField;
	@FXML
	private TextField emailKorisnikaTextField;
	@FXML
	private TextField telefonKorisnikaTextField;

	@FXML
	private TableView<PrivatniKorisnik> privatniKorisniciTableView;
	@FXML
	private TableColumn<PrivatniKorisnik, String> imeColumn;
	@FXML
	private TableColumn<PrivatniKorisnik, String> prezimeColumn;
	@FXML
	private TableColumn<PrivatniKorisnik, String> emailColumn;
	@FXML
	private TableColumn<PrivatniKorisnik, String> telefonColumn;
	
	
	
	/**
	 * Inicijalizira scenu
	 */
	@FXML
	private void initialize() {
		try {
			listaPrivatnihKorisnika = (ArrayList<PrivatniKorisnik>) BazaPodataka.dohvatiPrivatneKorisnike();
		} catch (BazaPodatakaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		imeColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<PrivatniKorisnik, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<PrivatniKorisnik, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getIme().toString());
					}
				});
		
		prezimeColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<PrivatniKorisnik, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<PrivatniKorisnik, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getPrezime().toString());
					}
				});
		emailColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<PrivatniKorisnik, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<PrivatniKorisnik, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getEmail());
					}
				});
		telefonColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<PrivatniKorisnik, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<PrivatniKorisnik, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getTelefon());
					}
				});

		pretrazi();
	}

	/**
	 * Pretrazuje privatne korisnike
	 */
	public void pretrazi() {
		
		List<PrivatniKorisnik> filter = listaPrivatnihKorisnika;
		if (imeKorisnikaTextField.getText().isEmpty() == false) {
			filter = listaPrivatnihKorisnika.stream().filter(
					m -> m.getIme().toLowerCase().contains(imeKorisnikaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		
		if (prezimeKorisnikaTextField.getText().isEmpty() == false) {
			filter = listaPrivatnihKorisnika.stream().filter(
					m -> m.getPrezime().toLowerCase().contains(prezimeKorisnikaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		if (emailKorisnikaTextField.getText().isEmpty() == false) {
			filter = listaPrivatnihKorisnika.stream().filter(
					m -> m.getIme().toLowerCase().contains(emailKorisnikaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		
		if (telefonKorisnikaTextField.getText().isEmpty() == false) {
			filter = listaPrivatnihKorisnika.stream().filter(
					m -> m.getPrezime().toLowerCase().contains(telefonKorisnikaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		
		
		ObservableList<PrivatniKorisnik> filtriraniPrivatniKorisnici = FXCollections.observableArrayList(filter);
		privatniKorisniciTableView.setItems(filtriraniPrivatniKorisnici);
	}

	public static void dodajNovogPrivatnogKorisnika(PrivatniKorisnik privatniKorisnik) {
		obsListaPrivatnihKorisnika.add(privatniKorisnik);
		listaPrivatnihKorisnika.add(privatniKorisnik);
		
	}
}

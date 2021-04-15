package hr.java.vjezbe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
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
 * Prikazuje poslovne korisnike
 * 
 * @author Patricija Kuže
 *
 */
public class PoslovniKorisniciController {

	static ObservableList<PoslovniKorisnik> obsListaPoslovnihKorisnika;
	private static ArrayList<PoslovniKorisnik> listaPoslovnihKorisnika;

	@FXML
	private TextField nazivKorisnikaTextField;
	@FXML
	private TextField webKorisnikaTextField;
	@FXML
	private TextField emailKorisnikaTextField;
	@FXML
	private TextField telefonKorisnikaTextField;

	@FXML
	private TableView<PoslovniKorisnik> poslovniKorisniciTableView;
	@FXML
	private TableColumn<PoslovniKorisnik, String> nazivColumn;
	@FXML
	private TableColumn<PoslovniKorisnik, String> webColumn;
	@FXML
	private TableColumn<PoslovniKorisnik, String> emailColumn;
	@FXML
	private TableColumn<PoslovniKorisnik, String> telefonColumn;
	
	
	
	/**
	 * Inicijalizira scenu
	 */
	@FXML
	private void initialize() {
		try {
			listaPoslovnihKorisnika = (ArrayList<PoslovniKorisnik>) BazaPodataka.dohvatiPoslovneKorisnike();
		} catch (BazaPodatakaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nazivColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<PoslovniKorisnik, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<PoslovniKorisnik, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getNaziv().toString());
					}
				});
		
		webColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<PoslovniKorisnik, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<PoslovniKorisnik, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getWeb().toString());
					}
				});
		emailColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<PoslovniKorisnik, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<PoslovniKorisnik, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getEmail());
					}
				});
		telefonColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<PoslovniKorisnik, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<PoslovniKorisnik, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getTelefon());
					}
				});

		pretrazi();
	}

	/**
	 * Pretrazuje poslovne korisnike
	 */
	public void pretrazi() {
		
		List<PoslovniKorisnik> filter = listaPoslovnihKorisnika;
		if (nazivKorisnikaTextField.getText().isEmpty() == false) {
			filter = listaPoslovnihKorisnika.stream().filter(
					m -> m.getNaziv().toLowerCase().contains(nazivKorisnikaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		
		if (webKorisnikaTextField.getText().isEmpty() == false) {
			filter = listaPoslovnihKorisnika.stream().filter(
					m -> m.getWeb().toLowerCase().contains(webKorisnikaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		if (emailKorisnikaTextField.getText().isEmpty() == false) {
			filter = listaPoslovnihKorisnika.stream().filter(
					m -> m.getEmail().toLowerCase().contains(emailKorisnikaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		
		if (telefonKorisnikaTextField.getText().isEmpty() == false) {
			filter = listaPoslovnihKorisnika.stream().filter(
					m -> m.getTelefon().toLowerCase().contains(telefonKorisnikaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		
		
		ObservableList<PoslovniKorisnik> filtriraniPoslovniKorisnici = FXCollections.observableArrayList(filter);
		poslovniKorisniciTableView.setItems(filtriraniPoslovniKorisnici);
	}

	public static void dodajNovogPoslovnogKorisnika(PoslovniKorisnik poslovniKorisnik) {
		obsListaPoslovnihKorisnika.add(poslovniKorisnik);
		listaPoslovnihKorisnika.add(poslovniKorisnik);
		
	}
}

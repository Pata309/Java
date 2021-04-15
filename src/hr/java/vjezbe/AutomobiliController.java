package hr.java.vjezbe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Automobil;
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
 * Prikazuje prozor s pretragom automobila
 * 
 * @author Patricija Kuže
 *
 */

public class AutomobiliController {
	
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
	private TableView<Automobil> automobiliTableView;
	@FXML
	private TableColumn<Automobil, String> naslovColumn;
	@FXML
	private TableColumn<Automobil, String> opisColumn;
	@FXML
	private TableColumn<Automobil, BigDecimal> snagaColumn;
	@FXML
	private TableColumn<Automobil, BigDecimal> cijenaColumn;
	@FXML
	private TableColumn<Automobil, String> stanjeColumn;

	/**
	 * Inicijalizira scenu
	 */
	@FXML
	private void initialize() {
		try {
			listaAutomobila = (ArrayList<Automobil>) BazaPodataka.dohvatiAutomobile();
		} catch (BazaPodatakaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		naslovColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Automobil, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Automobil, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getNaslov().toString());
					}
				});
		
		opisColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Automobil, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Automobil, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getOpis().toString());
					}
				});
		snagaColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Automobil, BigDecimal>, ObservableValue<BigDecimal>>() {
					@Override
					public ObservableValue<BigDecimal> call(CellDataFeatures<Automobil, BigDecimal> param) {
						return new ReadOnlyObjectWrapper<BigDecimal>(param.getValue().getCijena());
					}
				});
		cijenaColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Automobil, BigDecimal>, ObservableValue<BigDecimal>>() {
					@Override
					public ObservableValue<BigDecimal> call(CellDataFeatures<Automobil, BigDecimal> param) {
						return new ReadOnlyObjectWrapper<BigDecimal>(param.getValue().getSnagaKs());
					}
				});
		stanjeColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Automobil, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Automobil, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getStanje().toString());
					}
				});

		pretrazi();
	}

	/**
	 * Pretrazuje automobile
	 */
	public void pretrazi() {
		
		List<Automobil> filter = listaAutomobila;
		if (naslovAutomobilaTextField.getText().isEmpty() == false) {
			filter = listaAutomobila.stream().filter(
					m -> m.getNaslov().toLowerCase().contains(naslovAutomobilaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		
		if (opisAutomobilaTextField.getText().isEmpty() == false) {
			filter = listaAutomobila.stream().filter(
					m -> m.getOpis().toLowerCase().contains(opisAutomobilaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		
		if (snagaAutomobilaTextField.getText().isEmpty() == false) {
			filter = listaAutomobila.stream().filter(
					m -> m.getCijena().toString().contains(snagaAutomobilaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		if (cijenaAutomobilaTextField.getText().isEmpty() == false) {
			filter = listaAutomobila.stream().filter(
					m -> m.getSnagaKs().toString().contains(cijenaAutomobilaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		
		ObservableList<Automobil> filtriraniAutomobili = FXCollections.observableArrayList(filter);
		automobiliTableView.setItems(filtriraniAutomobili);
	}
	public static void dodajNoviAutomobil(Automobil automobil) {
		obsListaAutomobila.add(automobil);
		listaAutomobila.add(automobil);
		
	}
}

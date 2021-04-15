package hr.java.vjezbe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Usluga;
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
 * Pretrazuje usluge
 * 
 * @author Patricija Kuže
 *
 */
public class UslugeController {

	
	static ObservableList<Usluga> obsListaUsluga;
	private static ArrayList<Usluga> listaUsluga;

	@FXML
	private TextField naslovUslugeTextField;
	@FXML
	private TextField opisUslugeTextField;
	@FXML
	private TextField cijenaUslugeTextField;

	@FXML
	private TableView<Usluga> uslugeTableView;
	@FXML
	private TableColumn<Usluga, String> naslovColumn;
	@FXML
	private TableColumn<Usluga, String> opisColumn;
	@FXML
	private TableColumn<Usluga, BigDecimal> cijenaColumn;
	@FXML
	private TableColumn<Usluga, String> stanjeColumn;
	
	/**
	 * Inicijalizira scenu
	 */
	@FXML
	private void initialize() {
		try {
			listaUsluga = (ArrayList<Usluga>) BazaPodataka.dohvatiUsluge();
		} catch (BazaPodatakaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		naslovColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Usluga, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Usluga, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getNaslov().toString());
					}
				});
		
		opisColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Usluga, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Usluga, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getOpis().toString());
					}
				});
		
		cijenaColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Usluga, BigDecimal>, ObservableValue<BigDecimal>>() {
					@Override
					public ObservableValue<BigDecimal> call(CellDataFeatures<Usluga, BigDecimal> param) {
						return new ReadOnlyObjectWrapper<BigDecimal>(param.getValue().getCijena());
					}
				});
		stanjeColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Usluga, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Usluga, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getStanje().toString());
					}
				});

		pretrazi();
	}
	
	/**
	 * Pretrazuje usluge
	 */
	public void pretrazi() {
		
		List<Usluga> filter = listaUsluga;
		if (naslovUslugeTextField.getText().isEmpty() == false) {
			filter = listaUsluga.stream().filter(
					m -> m.getNaslov().toLowerCase().contains(naslovUslugeTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		
		if (opisUslugeTextField.getText().isEmpty() == false) {
			filter = listaUsluga.stream().filter(
					m -> m.getOpis().toLowerCase().contains(opisUslugeTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		
		if (cijenaUslugeTextField.getText().isEmpty() == false) {
			filter = listaUsluga.stream().filter(
					m -> m.getCijena().toString().contains(cijenaUslugeTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		
		ObservableList<Usluga> filtriraneUsluge = FXCollections.observableArrayList(filter);
		uslugeTableView.setItems(filtriraneUsluge);
	}

	public static void dodajNovuUslugu(Usluga usluga) {
		obsListaUsluga.add(usluga);
		listaUsluga.add(usluga);
		
	}
}

package hr.java.vjezbe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
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
 * Prikazuje stanove
 * 
 * @author Patricija Kuže
 *
 */
public class StanoviController {

	static ObservableList<Stan> obsListaStanova;
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
	private TableView<Stan> stanoviTableView;
	@FXML
	private TableColumn<Stan, String> naslovColumn;
	@FXML
	private TableColumn<Stan, String> opisColumn;
	@FXML
	private TableColumn<Stan, BigDecimal> kvadraturaColumn;
	@FXML
	private TableColumn<Stan, BigDecimal> cijenaColumn;
	@FXML
	private TableColumn<Stan, String> stanjeColumn;

	/**
	 * Inicijalizira scenu
	 */
	@FXML
	private void initialize() {
		try {
			listaStanova = (ArrayList<Stan>) BazaPodataka.dohvatiStanove();
		} catch (BazaPodatakaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		naslovColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Stan, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Stan, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getNaslov().toString());
					}
				});
		
		opisColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Stan, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Stan, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getOpis().toString());
					}
				});
		kvadraturaColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Stan, BigDecimal>, ObservableValue<BigDecimal>>() {
					@Override
					public ObservableValue<BigDecimal> call(CellDataFeatures<Stan, BigDecimal> param) {
						return new ReadOnlyObjectWrapper<BigDecimal>(param.getValue().getKvadratura());
					}
				});
		cijenaColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Stan, BigDecimal>, ObservableValue<BigDecimal>>() {
					@Override
					public ObservableValue<BigDecimal> call(CellDataFeatures<Stan, BigDecimal> param) {
						return new ReadOnlyObjectWrapper<BigDecimal>(param.getValue().getCijena());
					}
				});
		stanjeColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Stan, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Stan, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getStanje().toString());
					}
				});

		pretrazi();
	}

	/**
	 * Pretrazuje stanove
	 */
	public void pretrazi() {
		
		List<Stan> filter = listaStanova;
		if (naslovStanaTextField.getText().isEmpty() == false) {
			filter = listaStanova.stream().filter(
					m -> m.getNaslov().toLowerCase().contains(naslovStanaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		
		if (opisStanaTextField.getText().isEmpty() == false) {
			filter = listaStanova.stream().filter(
					m -> m.getOpis().toLowerCase().contains(opisStanaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		
		if (kvadraturaStanaTextField.getText().isEmpty() == false) {
			filter = listaStanova.stream().filter(
					m -> m.getKvadratura().toString().contains(kvadraturaStanaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		if (cijenaStanaTextField.getText().isEmpty() == false) {
			filter = listaStanova.stream().filter(
					m -> m.getCijena().toString().contains(cijenaStanaTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		
		ObservableList<Stan> filtriraniStanovi = FXCollections.observableArrayList(filter);
		stanoviTableView.setItems(filtriraniStanovi);
	}
	public static void dodajNoviStan(Stan stan) {
		obsListaStanova.add(stan);
		listaStanova.add(stan);
		
	}
	
}

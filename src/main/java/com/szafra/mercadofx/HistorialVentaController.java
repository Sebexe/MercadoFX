package com.szafra.mercadofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.Producto;
import negocio.ProductoVenta;
import negocio.Venta;

public class HistorialVentaController {

    ObservableList<Venta> ventasObservable;
    @FXML
    TableView<Venta> tablita;
    @FXML
    protected void onMenuButtonClick() {
        Programa.obtenerInstancia().cambiarEscenaMenu();
    }

    public void actualizarVistaVentas() {
        ventasObservable.clear();
        ventasObservable.addAll(Programa.obtenerInstancia().listarVentas());
    }
    @FXML
    public void initialize(){
        Programa.obtenerInstancia().setStockController(this);
        ventasObservable = FXCollections.observableArrayList(Programa.obtenerInstancia().listarVentas());
        TableColumn<Venta, Integer> contadorColumn = new TableColumn<>("N");
        TableColumn<Venta, Integer> cuilColumn = new TableColumn<>("CUIL");
        TableColumn<Venta, Double> finalColumn = new TableColumn<>("Precio Final");
        TableColumn<Venta, Integer> totalColumn = new TableColumn<>("Total");
        TableColumn<Venta, String> medioColumn = new TableColumn<>("Medio de Pago");
        contadorColumn.setCellValueFactory(new PropertyValueFactory<>("Numero_venta"));contadorColumn.setPrefWidth(50);
        cuilColumn.setCellValueFactory(new PropertyValueFactory<>("Cuil_cliente"));cuilColumn.setPrefWidth(150);
        finalColumn.setCellValueFactory(new PropertyValueFactory<>("Precio_final"));finalColumn.setPrefWidth(100);
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("Total"));totalColumn.setPrefWidth(100);
        medioColumn.setCellValueFactory(new PropertyValueFactory<>("medio"));medioColumn.setPrefWidth(200);

        tablita.getColumns().addAll(contadorColumn,cuilColumn,totalColumn,finalColumn,medioColumn);
        tablita.setItems(ventasObservable);
    }
}

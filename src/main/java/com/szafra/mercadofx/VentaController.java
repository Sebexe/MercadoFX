package com.szafra.mercadofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.Producto;
import negocio.ProductoVenta;

public class VentaController {
    ObservableList<ProductoVenta> carritoObservable;

    @FXML
    TextField codProducto;

    @FXML
    TextField cantidadProducto;

    @FXML
    TableView<ProductoVenta> tablaProductos;


    @FXML
    protected void onMenuButtonClick() {
        Programa.obtenerInstancia().cambiarEscenaMenu();
    }

    @FXML
    protected void onAgregarCarritoClick() {
        int codProductoValor = Integer.parseInt(codProducto.getText());
        int cantidadProductoValor = Integer.parseInt(cantidadProducto.getText());
        Producto producto_agregar = Programa.obtenerInstancia().usarAlmacen().buscarProducto(codProductoValor);
        ProductoVenta nuevoProducto = new ProductoVenta(producto_agregar,cantidadProductoValor);
        carritoObservable.add(nuevoProducto);
    }


    @FXML
    public void initialize(){
        carritoObservable = FXCollections.observableArrayList();
        TableColumn<ProductoVenta, Integer> codigoColumn = new TableColumn<>("ID");
        TableColumn<ProductoVenta, String> descripcionColumn = new TableColumn<>("Descripción");
        TableColumn<ProductoVenta, Integer> cantidadColumn = new TableColumn<>("Cantidad");
        TableColumn<ProductoVenta, Integer> precioColumn = new TableColumn<>("Precio Unitario");
        TableColumn<ProductoVenta, Integer> subtotalColumn = new TableColumn<>("Subtotal");



        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio_unitario"));
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        subtotalColumn.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        tablaProductos.getColumns().addAll(codigoColumn, descripcionColumn, precioColumn,cantidadColumn,subtotalColumn);
        tablaProductos.setItems(carritoObservable);


    }

}

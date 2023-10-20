package com.szafra.mercadofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.Producto;

public class StockController {
    ObservableList<Producto> productosObservableList;
    @FXML
    TableView tabla;
    @FXML
    TextField nuevoID;
    @FXML
    TextField nuevaDescripcion;
    @FXML
    TextField nuevoMinimo;
    @FXML
    TextField nuevoActual;

    @FXML
    TextField nuevoPrecio;

    @FXML
    TextField modificarID;

    @FXML
    TextField nuevoStock;


    @FXML
    protected void onMenuButtonClick() {
        Programa.obtenerInstancia().cambiarEscenaMenu();
    }

    @FXML
    protected void onEliminarProductoClick() {
        //hola
        if (modificarID.getText().isEmpty())
            Programa.obtenerInstancia().crearAlerta("Alguno de los campos no esta completo");
        else {
            int idModificar = Integer.parseInt(modificarID.getText());
            if (!Programa.obtenerInstancia().usarAlmacen().estaProducto(idModificar)){
                Programa.obtenerInstancia().crearAlerta("No existe en la base");
            }
            else {
                Programa.obtenerInstancia().usarAlmacen().sacarProducto(idModificar);
                Programa.obtenerInstancia().guardarAlmacen();
                ObservableList<Producto> productosObservableList = FXCollections.observableArrayList(Programa.obtenerInstancia().listarProductos());
                tabla.setItems(productosObservableList);
                Programa.obtenerInstancia().crearAlerta("El producto se ha eliminado correctamente.");
                modificarID.clear();
            }

        }
    }


    @FXML
    protected void onAgregarProductoClick() {
        if (nuevoID.getText().isEmpty() || nuevaDescripcion.getText().isEmpty() || nuevoPrecio.getText().isEmpty() || nuevoActual.getText().isEmpty() || nuevoMinimo.getText().isEmpty())
            Programa.obtenerInstancia().crearAlerta("Alguno de los campos no esta completo");
        else {
            int codigoNuevo = Integer.parseInt(nuevoID.getText());
            String descripcionNueva = nuevaDescripcion.getText();
            int precioNuevo = Integer.parseInt(nuevoPrecio.getText());
            int actualNuevo = Integer.parseInt(nuevoActual.getText());
            int minimoNuevo = Integer.parseInt(nuevoMinimo.getText());
            if (Programa.obtenerInstancia().usarAlmacen().estaProducto(codigoNuevo)) {
                Programa.obtenerInstancia().crearAlerta("El producto yá esta en el almacen");
            }
            else {
                Programa.obtenerInstancia().usarAlmacen().agregarProducto(codigoNuevo, descripcionNueva, precioNuevo, actualNuevo, minimoNuevo);
                Programa.obtenerInstancia().guardarAlmacen();
                ObservableList<Producto> productosObservableList = FXCollections.observableArrayList(Programa.obtenerInstancia().listarProductos());
                tabla.setItems(productosObservableList);
                Programa.obtenerInstancia().crearAlerta("El producto se ha cargado correctamente.");
                nuevoID.clear();nuevaDescripcion.clear();nuevoPrecio.clear();nuevoActual.clear();nuevoMinimo.clear();
            }
        }
    }

    @FXML
    protected void onAgregarStockClick() {
        if (modificarID.getText().isEmpty() || nuevoStock.getText().isEmpty())
            Programa.obtenerInstancia().crearAlerta("Alguno de los campos no esta completo");
        else {
            int idModificar = Integer.parseInt(modificarID.getText());
            int aAgregar = Integer.parseInt(nuevoStock.getText());
            Programa.obtenerInstancia().usarAlmacen().agregarExistencias(idModificar,aAgregar);
            productosObservableList.clear();
            productosObservableList.addAll(Programa.obtenerInstancia().listarProductos());
            Programa.obtenerInstancia().crearAlerta("Las existencias se han agregado correctamente.");
            Programa.obtenerInstancia().guardarAlmacen();
            modificarID.clear();nuevoStock.clear();

        }

    }

    @FXML
    protected void onEliminarStockClick() {
        if (modificarID.getText().isEmpty() || nuevoStock.getText().isEmpty())
            Programa.obtenerInstancia().crearAlerta("Alguno de los campos no esta completo");
        else {
            int idModificar = Integer.parseInt(modificarID.getText());
            int aAgregar = Integer.parseInt(nuevoStock.getText());
            Programa.obtenerInstancia().usarAlmacen().sacarExistencias(idModificar,aAgregar);
            Programa.obtenerInstancia().guardarAlmacen();
            productosObservableList.clear();
            productosObservableList.addAll(Programa.obtenerInstancia().listarProductos());
            Programa.obtenerInstancia().crearAlerta("Las existencias se han agregado correctamente.");
            modificarID.clear();nuevoStock.clear();
        }

    }




    @FXML
    public void initialize(){
        productosObservableList = FXCollections.observableArrayList(Programa.obtenerInstancia().listarProductos());
        TableColumn<Producto, Integer> codigoColumn = new TableColumn<>("Código");
        TableColumn<Producto, String> descripcionColumn = new TableColumn<>("Descripción");
        TableColumn<Producto, Integer> precioColumn = new TableColumn<>("Precio Unitario");
        TableColumn<Producto, Integer> stockActualColumn = new TableColumn<>("Stock Actual");
        TableColumn<Producto, Integer> stockMinimoColumn = new TableColumn<>("Stock Mínimo");
        TableColumn<Producto, String> estadoColumn = new TableColumn<>("Estado");
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));codigoColumn.setPrefWidth(150);
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));descripcionColumn.setPrefWidth(200);
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio_unitario"));precioColumn.setPrefWidth(200);
        stockActualColumn.setCellValueFactory(new PropertyValueFactory<>("stock_actual"));stockActualColumn.setPrefWidth(200);
        stockMinimoColumn.setCellValueFactory(new PropertyValueFactory<>("stock_minimo"));stockMinimoColumn.setPrefWidth(200);
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tabla.getColumns().addAll(codigoColumn, descripcionColumn, precioColumn, stockActualColumn, stockMinimoColumn,estadoColumn);
        tabla.setItems(productosObservableList);


    }

}
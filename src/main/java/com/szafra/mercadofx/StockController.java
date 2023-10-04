package com.szafra.mercadofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.Producto;

public class StockController {

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
    protected void onMenuButtonClick() {
        HelloApplication.obtenerInstancia().cambiarEscenaMenu();
    }

    @FXML
    protected void onEliminarProductoClick() {
        if (modificarID.getText().isEmpty())
            HelloApplication.obtenerInstancia().crearAlerta("Alguno de los campos no esta completo");
        else {
            int idModificar = Integer.parseInt(modificarID.getText());
            if (!HelloApplication.obtenerInstancia().usarAlmacen().estaProducto(idModificar)){
                HelloApplication.obtenerInstancia().crearAlerta("No existe en la base");
            }
            else {
                HelloApplication.obtenerInstancia().usarAlmacen().sacarProducto(idModificar);
                HelloApplication.obtenerInstancia().guardarAlmacen();
                ObservableList<Producto> productosObservableList = FXCollections.observableArrayList(HelloApplication.obtenerInstancia().listarProductos());
                tabla.setItems(productosObservableList);
            }

        }
    }


    @FXML
    protected void onAgregarProductoClick() {
        if (nuevoID.getText().isEmpty() || nuevaDescripcion.getText().isEmpty() || nuevoPrecio.getText().isEmpty() || nuevoActual.getText().isEmpty() || nuevoMinimo.getText().isEmpty())
            HelloApplication.obtenerInstancia().crearAlerta("Alguno de los campos no esta completo");
        else {
            int codigoNuevo = Integer.parseInt(nuevoID.getText());
            String descripcionNueva = nuevaDescripcion.getText();
            int precioNuevo = Integer.parseInt(nuevoPrecio.getText());
            int actualNuevo = Integer.parseInt(nuevoActual.getText());
            int minimoNuevo = Integer.parseInt(nuevoMinimo.getText());
            if (HelloApplication.obtenerInstancia().usarAlmacen().estaProducto(codigoNuevo)) {
                HelloApplication.obtenerInstancia().crearAlerta("El producto yá esta en el almacen");
            }
            else {
                HelloApplication.obtenerInstancia().usarAlmacen().agregarProducto(codigoNuevo, descripcionNueva, precioNuevo, actualNuevo, minimoNuevo);
                HelloApplication.obtenerInstancia().guardarAlmacen();
                ObservableList<Producto> productosObservableList = FXCollections.observableArrayList(HelloApplication.obtenerInstancia().listarProductos());
                tabla.setItems(productosObservableList);
            }
        }
    }

    @FXML
    public void initialize(){
        ObservableList<Producto> productosObservableList = FXCollections.observableArrayList(HelloApplication.obtenerInstancia().listarProductos());
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

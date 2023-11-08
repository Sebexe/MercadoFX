package com.szafra.mercadofx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import negocio.*;

import java.util.ArrayList;
import java.util.Objects;

public class VentaController {
    ObservableList<ProductoVenta> carritoObservable;
    ArrayList<ProductoVenta> carritoActual = new ArrayList<>();
    Venta ventaActual = new Venta();
    @FXML
    TextField codProducto;
    @FXML
    ChoiceBox<String> cajaDecisiones;

    @FXML
    Text precioTotal;
    @FXML
    Text precioF;

    @FXML
    TextField cantidadProducto;
    @FXML
    TextField submitVenta;

    @FXML
    TableView<ProductoVenta> tablaProductos;


    @FXML
    protected void onMenuButtonClick() {
        Programa.obtenerInstancia().cambiarEscenaMenu();
    }
    @FXML
    protected void onSubmitVentaClick() {
        if (submitVenta.getText().isEmpty() || carritoObservable.isEmpty())
            Programa.obtenerInstancia().crearAlerta("El CUIL ni el carrito pueden estar vacios.");
        else if (cajaDecisiones.getValue() == null || Objects.equals(cajaDecisiones.getValue(), "") ) {
            Programa.obtenerInstancia().crearAlerta("El medio de pago no puede estar vacio.");
        }
        else{
            int cuil_cliente = Integer.parseInt(submitVenta.getText());
            ventaActual.setCuil_cliente(cuil_cliente);
            ventaActual.setNumero_venta(Programa.obtenerInstancia().usarAlmacenVentas().getContador());
            for (ProductoVenta productoVenta : carritoActual) {
                Programa.obtenerInstancia().usarAlmacen().sacarExistencias(productoVenta.getID(), productoVenta.getCantidad());
                Programa.obtenerInstancia().usarAlmacen().calcularExistencias(productoVenta.getID());
            }
            Programa.obtenerInstancia().crearAlertaPositiva("La venta se ha completado con exito!");
            Programa.obtenerInstancia().usarAlmacenVentas().guardarVenta(ventaActual);
            Programa.obtenerInstancia().guardarAlmacen();
            Programa.obtenerInstancia().guardarAlmacenVentas();

            ventaActual = new Venta();
            carritoObservable.clear();
            carritoActual = new ArrayList<>();
            cajaDecisiones.setValue("");
            Programa.obtenerInstancia().Refrescar();
            Programa.obtenerInstancia().RefrescarVentas();
            precioTotal.setText("");
            precioF.setText("");
            submitVenta.clear();
        }
    }



    @FXML
    protected void onAgregarCarritoClick() {
        ProductoVenta productoEncontrado = null;
        if (codProducto.getText().isEmpty() || cantidadProducto.getText().isEmpty())
            Programa.obtenerInstancia().crearAlerta("Los campos no pueden estar vacios.");
        else{
            int codProductoValor = Integer.parseInt(codProducto.getText());
            int cantidadProductoValor = Integer.parseInt(cantidadProducto.getText());
            if (cantidadProductoValor <= 0)
                Programa.obtenerInstancia().crearAlerta("No se puede ingresar una cantidad negativa ni 0");
            else {
                if (Programa.obtenerInstancia().usarAlmacen().estaProducto(codProductoValor)) {
                    Producto producto_agregar = Programa.obtenerInstancia().usarAlmacen().buscarProducto(codProductoValor);
                    for (ProductoVenta productoVenta : carritoActual) {
                        if (productoVenta.getID() == codProductoValor)
                            productoEncontrado = productoVenta;
                    }
                    if (productoEncontrado == null) {
                        if (Programa.obtenerInstancia().usarAlmacen().haySuficiente(codProductoValor, -cantidadProductoValor)) {
                            ProductoVenta nuevoProducto = new ProductoVenta(producto_agregar, cantidadProductoValor);
                            carritoActual.add(nuevoProducto);
                        } else {
                            Programa.obtenerInstancia().crearAlerta("No hay suficiente stock de ese producto.");
                        }
                    } else {
                        if (Programa.obtenerInstancia().usarAlmacen().haySuficiente(codProductoValor, -productoEncontrado.getCantidad() - cantidadProductoValor)) {
                            productoEncontrado.setCantidad(productoEncontrado.getCantidad() + cantidadProductoValor);
                            productoEncontrado.CalcularSubtotal();
                        } else {
                            Programa.obtenerInstancia().crearAlerta("No hay suficiente stock de ese producto.");
                        }
                    }
                    ventaActual.setCarrito(carritoActual);
                    carritoObservable.clear();
                    carritoObservable.addAll(carritoActual);
                    codProducto.clear();
                    cantidadProducto.clear();
                    precioTotal.setText("El precio total es: $" + ventaActual.getTotal());
                    if (!Objects.equals(cajaDecisiones.getValue(), "") && cajaDecisiones.getValue() != null) {
                        precioF.setText("El precio final es: $" + ventaActual.getPrecio_final());
                    }
                } else {
                    Programa.obtenerInstancia().crearAlerta("Ese producto no existe");
                    codProducto.clear();
                    cantidadProducto.clear();
                }
            }
        }
    }


    @FXML
    public void initialize(){
        carritoObservable = FXCollections.observableArrayList();
        TableColumn<ProductoVenta, Integer> codigoColumn = new TableColumn<>("ID");
        TableColumn<ProductoVenta, String> descripcionColumn = new TableColumn<>("Descripción");
        TableColumn<ProductoVenta, Integer> cantidadColumn = new TableColumn<>("Cantidad");
        TableColumn<ProductoVenta, Integer> precioColumn = new TableColumn<>("Precio Unitario");
        TableColumn<ProductoVenta, Integer> subtotalColumn = new TableColumn<>("Subtotal");



        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));codigoColumn.setPrefWidth(50);
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));descripcionColumn.setPrefWidth(200);
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio_unitario"));precioColumn.setPrefWidth(100);
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));cantidadColumn.setPrefWidth(60);
        subtotalColumn.setCellValueFactory(new PropertyValueFactory<>("subtotal"));cantidadColumn.setPrefWidth(100);
        tablaProductos.getColumns().addAll(codigoColumn, descripcionColumn, precioColumn,cantidadColumn,subtotalColumn);
        tablaProductos.setItems(carritoObservable);

        cajaDecisiones.getItems().addAll("Efectivo","Debito","Credito 2 cuotas","Credito 3 cuotas","Credito 6 cuotas");
        cajaDecisiones.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                switch (t1) {
                    case "Efectivo" ->
                        ventaActual.setMedio_pago(new Efectivo(ventaActual.getTotal()));
                    case "Debito" ->
                            ventaActual.setMedio_pago(new Debito(ventaActual.getTotal()));
                    case "Credito 2 cuotas" ->
                            ventaActual.setMedio_pago(new Credito(ventaActual.getTotal(),2));
                    case "Credito 3 cuotas" ->
                            ventaActual.setMedio_pago(new Credito(ventaActual.getTotal(),3));
                    case "Credito 6 cuotas" ->
                            ventaActual.setMedio_pago(new Credito(ventaActual.getTotal(),6));
                    case "" ->
                            ventaActual.setMedio_pago(null);
                }
                if (!Objects.equals(cajaDecisiones.getValue(), "") && cajaDecisiones.getValue() != null) {
                    precioF.setText("El precio final es: $" + ventaActual.getPrecio_final());
                }
                }

            }
        );



    }

}

package com.szafra.mercadofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import negocio.Almacen;
import negocio.AlmacenVentas;
import negocio.Producto;
import negocio.Venta;

import java.io.*;
import java.util.ArrayList;

// cambio

public class Programa extends Application {

    private static Programa instancia; // Variable para almacenar la instancia única
    private static Scene menuP;
    private static Scene stockP;
    private static Scene ventaP;
    private static Scene historialP;
    private static Stage escenario;

    private static Almacen almacenPrincipal;
    private static AlmacenVentas almacenVentasPrincipal;

    private StockController stockController;
    private HistorialVentaController pasadasController;

    public void setStockController(StockController controller) {
        stockController = controller;
    }
    public void setStockController(HistorialVentaController controller) {
        pasadasController = controller;
    }
    public void Refrescar() {
        stockController.actualizarVistaStock();
    }

    public void RefrescarVentas() {
        pasadasController.actualizarVistaVentas();
    }


    public static Programa obtenerInstancia() {
        return instancia;
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image(Programa.class.getResourceAsStream("icon.png")));

        stage.setMinHeight(720);
        stage.setMinWidth(1280);
        stage.setMaxHeight(800);
        stage.setMaxWidth(1350);
        escenario = stage;
        almacenPrincipal = cargarAlmacen();
        almacenVentasPrincipal = cargarAlmacenVentas();
        FXMLLoader cargadorMenu = new FXMLLoader(Programa.class.getResource("menuPrincipal.fxml"));
        FXMLLoader cargadorHistorialVenta = new FXMLLoader(Programa.class.getResource("historialVentas.fxml"));
        FXMLLoader cargadorStock = new FXMLLoader(Programa.class.getResource("Stock.fxml"));
        FXMLLoader cargadorVenta = new FXMLLoader(Programa.class.getResource("nuevaVenta.fxml"));
        instancia = this;
        Scene escenaMenu = new Scene(cargadorMenu.load(), 1280, 720);
        menuP = escenaMenu;
        ventaP = new Scene(cargadorVenta.load(),1280,720);
        historialP = new Scene(cargadorHistorialVenta.load(),1280,720);
        stockP = new Scene(cargadorStock.load(), 1280, 720);




        stage.setTitle("All Market");
        stage.setScene(escenaMenu);
        stage.show();
    }

    public void crearAlerta(String texto){
        Alert nuevaAlerta = new Alert(Alert.AlertType.WARNING);
        nuevaAlerta.setContentText(texto);
        nuevaAlerta.show();
    }


    public void crearAlertaPositiva(String texto){
        Alert nuevaAlerta = new Alert(Alert.AlertType.CONFIRMATION);
        nuevaAlerta.setContentText(texto);
        nuevaAlerta.show();
    }

    public void cambiarEscenaMenu(){
        escenario.setScene(menuP);
    }
    public void cambiarEscenaStock(){
        escenario.setScene(stockP);
    }
    public void cambiarEscenaVenta(){
        escenario.setScene(ventaP);
    }
    public void cambiarEscenaHistorial(){
        escenario.setScene(historialP);

    }

    private Almacen cargarAlmacen() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("datos.dat"))) {
            return (Almacen) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new Almacen(); // Si ocurre un error, devuelve una lista vacía
        }
    }


    public ArrayList<Producto> listarProductos() {
        return almacenPrincipal.listarProductos();
    }

    public ArrayList<Venta> listarVentas() {
        return almacenVentasPrincipal.recuperarVentas();
    }


    public Almacen usarAlmacen() {
        return almacenPrincipal;
    }

    public AlmacenVentas usarAlmacenVentas() {
        return almacenVentasPrincipal;
    }

    public AlmacenVentas cargarAlmacenVentas() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("ventas.dat"))) {
            return (AlmacenVentas) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new AlmacenVentas(); // Si ocurre un error, devuelve una lista vacía
        }
    }

    public void guardarAlmacenVentas() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("ventas.dat"))) {
            outputStream.writeObject(almacenVentasPrincipal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarAlmacen() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("datos.dat"))) {
            outputStream.writeObject(almacenPrincipal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
package com.szafra.mercadofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import negocio.Almacen;
import negocio.Producto;

import java.io.*;
import java.util.ArrayList;

// cambio

public class HelloApplication extends Application {

    private static HelloApplication instancia; // Variable para almacenar la instancia única
    private static Scene menuP;
    private static Scene stockP;
    private static Scene ventaP;
    private static Scene historialP;
    private static Stage escenario;

    private static Almacen almacenPrincipal;


    public static HelloApplication obtenerInstancia() {
        return instancia;
    }

    @Override
    public void start(Stage stage) throws IOException {
        almacenPrincipal = cargarAlmacen();
        almacenPrincipal.agregarProducto(1,"Leche",200,100,20);
        instancia = this;
        escenario = stage;
        stage.setMinHeight(720);
        stage.setMinWidth(1280);
        FXMLLoader cargadorMenu = new FXMLLoader(HelloApplication.class.getResource("menuPrincipal.fxml"));
        Scene escenaMenu = new Scene(cargadorMenu.load(), 1280, 720);
        menuP = escenaMenu;
        FXMLLoader cargadorStock = new FXMLLoader(HelloApplication.class.getResource("Stock.fxml"));
        FXMLLoader cargadorVenta = new FXMLLoader(HelloApplication.class.getResource("nuevaVenta.fxml"));
        Scene escenaVenta = new Scene(cargadorVenta.load(),1280,720);
        ventaP = escenaVenta;

        FXMLLoader cargadorHistorialVenta = new FXMLLoader(HelloApplication.class.getResource("historialVentas.fxml"));
        Scene escenaHistorialVenta = new Scene(cargadorHistorialVenta.load(),1280,720);
        historialP = escenaHistorialVenta;

        stockP = new Scene(cargadorStock.load(), 1280, 720);


        stage.setTitle("Hello!");
        stage.setScene(escenaMenu);
        stage.show();
    }

    public void crearAlerta(String texto){
        Alert nuevaAlerta = new Alert(Alert.AlertType.WARNING);
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
        if (historialP != null)
            escenario.setScene(historialP);
        else crearAlerta("Todavia no esta habilitado ir al historial");

    }

    private Almacen cargarAlmacen() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("datos.dat"))) {
            return (Almacen) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new Almacen(); // Si ocurre un error, devuelve una lista vacía
        }
    }

    public void guardarAlmacen() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("datos.dat"))) {
            outputStream.writeObject(almacenPrincipal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Producto> listarProductos() {
        return almacenPrincipal.listarProductos();
    }

    public Almacen usarAlmacen() {
        return almacenPrincipal;
    }


    public static void main(String[] args) {
        launch();
    }
}
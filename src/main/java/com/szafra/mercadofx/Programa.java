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

public class Programa extends Application {

    private static Programa instancia; // Variable para almacenar la instancia única
    private static Scene menuP;
    private static Scene stockP;
    private static Scene ventaP;
    private static Scene historialP;
    private static Stage escenario;

    private static Almacen almacenPrincipal;






    public static Programa obtenerInstancia() {
        return instancia;
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setMinHeight(720);
        stage.setMinWidth(1280);
        escenario = stage;
        almacenPrincipal = cargarAlmacen();
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




        stage.setTitle("Hello!");
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
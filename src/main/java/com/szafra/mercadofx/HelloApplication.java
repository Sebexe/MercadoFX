package com.szafra.mercadofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static HelloApplication instancia; // Variable para almacenar la instancia única
    private static Scene menuP;
    private static Scene stockP;
    private static Scene ventaP;
    private static Scene historialP;
    private static Stage escenario;

    public static HelloApplication obtenerInstancia() {
        return instancia;
    }

    @Override
    public void start(Stage stage) throws IOException {
        instancia = this;
        escenario = stage;
        stage.setMinHeight(720);
        stage.setMaxHeight(1280);
        FXMLLoader cargadorMenu = new FXMLLoader(HelloApplication.class.getResource("menuPrincipal.fxml"));
        Scene escenaMenu = new Scene(cargadorMenu.load(), 1280, 720);
        menuP = escenaMenu;
        FXMLLoader cargadorStock = new FXMLLoader(HelloApplication.class.getResource("Stock.fxml"));
        FXMLLoader cargadorVenta = new FXMLLoader(HelloApplication.class.getResource("nuevaVenta.fxml"));
        Scene escenaVenta = new Scene(cargadorVenta.load(),1280,720);
        ventaP = escenaVenta;
        stockP = new Scene(cargadorStock.load(), 1280, 720);


        stage.setTitle("Hello!");
        stage.setScene(escenaMenu);
        stage.show();
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



    public static void main(String[] args) {
        launch();
    }
}
package com.szafra.mercadofx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHistorialButtonClick() {
        Programa.obtenerInstancia().cambiarEscenaHistorial();
    }
    @FXML
    protected void onStockButtonClick() {
        Programa.obtenerInstancia().cambiarEscenaStock();
    }
    @FXML
    protected void onVentaButtonClick() {
        Programa.obtenerInstancia().cambiarEscenaVenta();
    }
}
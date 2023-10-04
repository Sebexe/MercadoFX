package com.szafra.mercadofx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHistorialButtonClick() {
        HelloApplication.obtenerInstancia().cambiarEscenaHistorial();
    }
    @FXML
    protected void onStockButtonClick() {
        HelloApplication.obtenerInstancia().cambiarEscenaStock();
    }
    @FXML
    protected void onVentaButtonClick() {
        HelloApplication.obtenerInstancia().cambiarEscenaVenta();
    }
}
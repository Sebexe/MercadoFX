package com.szafra.mercadofx;

import javafx.fxml.FXML;

public class HistorialVentaController {
    @FXML
    protected void onMenuButtonClick() {
        HelloApplication.obtenerInstancia().cambiarEscenaMenu();
    }
}

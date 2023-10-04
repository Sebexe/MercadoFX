package com.szafra.mercadofx;

import javafx.fxml.FXML;

public class StockController {
    @FXML
    protected void onMenuButtonClick() {
        HelloApplication.obtenerInstancia().cambiarEscenaMenu();
    }
}

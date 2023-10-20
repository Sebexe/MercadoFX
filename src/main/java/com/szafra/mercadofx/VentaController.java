package com.szafra.mercadofx;

import javafx.fxml.FXML;

public class VentaController {
    @FXML
    protected void onMenuButtonClick() {
        Programa.obtenerInstancia().cambiarEscenaMenu();
    }
}

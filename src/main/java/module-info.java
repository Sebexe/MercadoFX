module com.szafra.mercadofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.szafra.mercadofx to javafx.fxml;
    opens negocio to javafx.base;
    exports com.szafra.mercadofx;
}
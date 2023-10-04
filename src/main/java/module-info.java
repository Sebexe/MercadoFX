module com.szafra.mercadofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.szafra.mercadofx to javafx.fxml;
    exports com.szafra.mercadofx;
}
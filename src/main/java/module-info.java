module com.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires ucanaccess;
    requires java.desktop;

    opens com.library to javafx.fxml;
    opens com.library.util to javafx.base;
    exports com.library;
    exports com.library.model;
    opens com.library.model to javafx.fxml;
}
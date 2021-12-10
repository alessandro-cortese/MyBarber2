module it.barbergroup.view2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires it.barbergroup.applicationcontroller;

    opens it.barbergroup.view2 to javafx.fxml;
    exports it.barbergroup.view2;
}
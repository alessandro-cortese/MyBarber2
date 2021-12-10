module it.barbergroup.view1 {
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

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.controller;
    opens com.example.demo.controller to javafx.fxml;

    exports TimePicker;
    opens TimePicker to javafx.fxml;

    exports com.example.demo.OtherBarberScheduler;
    opens com.example.demo.OtherBarberScheduler to javafx.fxml;
}
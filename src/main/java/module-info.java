module it.barbergroup {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;


    exports view_1;
    exports view_1.pickers;
    exports view_1.otherbarberscheduler;
    exports view_1.general;
    exports view_1.barber;
    exports view_1.client;
    opens view_1 to javafx.fxml;
    opens view_1.client to javafx.fxml;
    opens view_1.barber to javafx.fxml;
    opens view_1.general to javafx.fxml;
    opens view_1.otherbarberscheduler to javafx.fxml;
    opens view_1.pickers to javafx.fxml;



    opens view_2 to javafx.fxml;
    exports view_2;
    opens view_2.general to javafx.fxml;
    opens view_2.barber to javafx.fxml;
    opens view_2.client to javafx.fxml;


}
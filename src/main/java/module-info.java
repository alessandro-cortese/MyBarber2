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


    exports firstview;
    exports firstview.pickers;
    exports firstview.otherbarberscheduler;
    exports firstview.general;
    exports firstview.barber;
    exports firstview.client;
    opens firstview to javafx.fxml;
    opens firstview.client to javafx.fxml;
    opens firstview.barber to javafx.fxml;
    opens firstview.general to javafx.fxml;
    opens firstview.otherbarberscheduler to javafx.fxml;
    opens firstview.pickers to javafx.fxml;



    opens secondview to javafx.fxml;
    exports secondview;
    opens secondview.general to javafx.fxml;
    opens secondview.barber to javafx.fxml;
    opens secondview.client to javafx.fxml;


}
package it.barbergroup.view2.barber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import it.barbergroup.view2.general.ScreenChanger;
import javafx.scene.control.TextField;

import java.io.IOException;


public class BarberHomeController {


    @FXML
    private TextField barberHomeCommandLine;


    @FXML
    public void onCommand(ActionEvent event) throws IOException {

        String commandLine = barberHomeCommandLine.getText();
        barberHomeCommandLine.setText("");
        barberHomeCommandLine.setStyle(null);

        if (commandLine.compareTo("back") == 0 ) {
            ScreenChanger.getInstance().onBack(event) ;
            return ;
        }
        else if (commandLine.compareTo("logout") == 0) {
            System.exit(0);
        }
        else if (commandLine.compareTo("userarea") == 0) {
            ScreenChanger.getInstance().changeScreen(event, ScreenChanger.BARBER_USERAREA_SCREEN);
            return ;
        }

        barberHomeCommandLine.setStyle("-fx-border-color: red");



    }
}
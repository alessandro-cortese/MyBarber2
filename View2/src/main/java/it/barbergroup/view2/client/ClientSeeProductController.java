package it.barbergroup.view2.client;

import it.barbergroup.view2.general.ScreenChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class ClientSeeProductController {

    @FXML TextField commandLine ;

    @FXML
    public void onCommand(ActionEvent event) {
        String command = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");
        String addString = "add" ;

        if (command.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return;
        }
        else if (command.startsWith(addString)) {
            String addQuantity = command.replace(addString + " " , "") ;
            if (addQuantity.matches("[0-9]+")) {
                return ;
            }
        }

        commandLine.setStyle("-fx-border-color: red");
    }
}

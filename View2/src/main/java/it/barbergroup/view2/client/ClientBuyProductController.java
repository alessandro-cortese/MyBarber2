package it.barbergroup.view2.client;

import it.barbergroup.view2.general.ScreenChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ClientBuyProductController {


    @FXML
    TextField commandLine ;


    @FXML
    public void onCommand(ActionEvent event) throws IOException {
        String command = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");
        String searchString = "search" ;
        String gotoString = "goto" ;
        if (command.startsWith(searchString)) {
            String commandInput = command.replace(searchString + " " , "") ;
            if (commandInput.compareTo(searchString) != 0) {
                System.out.println("Cercato " + commandInput);
                return ;
            }
        }
        else if (command.startsWith(gotoString)) {
            String commandInput = command.replace(gotoString + " " , "") ;
            if (commandInput.matches("[0-9]+")) {
                ScreenChanger.getInstance().changeScreen(event, ScreenChanger.CLIENT_SEE_PRODUCT_SCREEN);
                return ;
            }
        }
        else if (command.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return ;
        }

        commandLine.setStyle("-fx-border-color: red");

    }
}

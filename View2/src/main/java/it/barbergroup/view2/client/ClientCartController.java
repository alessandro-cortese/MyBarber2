package it.barbergroup.view2.client;

import it.barbergroup.view2.general.ScreenChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.io.IOException;

public class ClientCartController {

    @FXML
    TextField commandLine ;

    public void onCommand(ActionEvent event) throws IOException {
        String command = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");

        String gotoString = "goto" ;
        String removeString = "remove" ;

        if (command.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return;
        }
        else if (command.startsWith(gotoString)) {
            String inputIndex = command.replace(gotoString + " ", "") ;
            if (inputIndex.matches("[0-9]+")) {
                ScreenChanger.getInstance().changeScreen(event, ScreenChanger.CLIENT_SEE_PRODUCT_SCREEN);
                return ;
            }
        }
        else if (command.startsWith(removeString)) {
            String inputString = command.replace(removeString + " ", "") ;
            if (inputString.matches("[0-9]+ [0-9]+")) {
                String[] inputNumbers = inputString.split(" ") ;
                int productIndex = Integer.parseInt(inputNumbers[0]) ;
                int productQuantity = Integer.parseInt(inputNumbers[1]) ;
                System.out.println("Rimosso " + productIndex + "; Quantità " + productQuantity);
                return ;
            }
        }

        commandLine.setStyle("-fx-border-color: red");
    }
}

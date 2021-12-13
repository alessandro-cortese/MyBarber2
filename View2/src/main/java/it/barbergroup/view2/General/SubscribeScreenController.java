package it.barbergroup.view2.General;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SubscribeScreenController {

    private static final String[] subscribeCommands = {
            "set name",
            "set surname",
            "set email",
            "set address",
            "set password",
            "set type",
            "register",
            "google register",
            "facebook register",
            "back"} ;


    @FXML TextField subscribeCommandLine;
    @FXML TextField nameField ;
    @FXML TextField surnameField ;
    @FXML TextField addressField ;
    @FXML TextField subscribeEmailField ;
    @FXML PasswordField subscribePasswordField ;
    @FXML TextField userTypeField ;



    @FXML
    public void onCommand() {
        String commandText = subscribeCommandLine.getText();
        subscribeCommandLine.setStyle(null);
        subscribeCommandLine.setText("");

        if (commandText.startsWith(subscribeCommands[0])) {
            nameField.setText(commandText.replace(subscribeCommands[0] + " ", "" ));
        }
        else if (commandText.startsWith(subscribeCommands[1])) {
            surnameField.setText(commandText.replace(subscribeCommands[1] + " ", "" ));
        }
        else if (commandText.startsWith(subscribeCommands[2])) {
            subscribeEmailField.setText(commandText.replace(subscribeCommands[2] + " ", "" ));
        }
        else if (commandText.startsWith(subscribeCommands[3])){
            addressField.setText(commandText.replace(subscribeCommands[3] + " ", "" ));
        }
        else if (commandText.startsWith(subscribeCommands[4])) {
            subscribePasswordField.setText(commandText.replace(subscribeCommands[4] + " ", "" ));
        }
        else if (commandText.startsWith(subscribeCommands[5])) {
            String userType = commandText.replace(subscribeCommands[5] + " ", "") ;
            if (userType.compareTo("B") == 0) {
                userTypeField.setText("Barber");
            }
            else if (userType.compareTo("C") == 0) {
                userTypeField.setText("Client");
            }
            else {
                subscribeCommandLine.setStyle("-fx-border-color: red");
            }
        }

        else {
            subscribeCommandLine.setStyle("-fx-border-color: red");
        }

    }
}

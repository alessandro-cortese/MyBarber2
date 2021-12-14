package it.barbergroup.view2.general;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Map;

public class SubscribeScreenController {


    @FXML TextField subscribeCommandLine;
    @FXML TextField nameField ;
    @FXML TextField surnameField ;
    @FXML TextField addressField ;
    @FXML TextField subscribeEmailField ;
    @FXML PasswordField subscribePasswordField ;
    @FXML TextField userTypeField ;


    private Map<String, TextField> textFieldMap ;

    private void initMap() {
        textFieldMap = Map.of(
                "set name", nameField,
                "set surname", surnameField,
                "set email", subscribeEmailField,
                "set address", addressField,
                "set password", subscribePasswordField,
                "set type", userTypeField) ;
    }

    private final String registerString = "register" ;


    @FXML
    public void onCommand() {
        String commandText = subscribeCommandLine.getText();
        subscribeCommandLine.setStyle(null);
        subscribeCommandLine.setText("");
        initMap();


        if (commandText.startsWith("set")) {
            for (Map.Entry<String, TextField> entry : textFieldMap.entrySet()) {
                String command = entry.getKey();
                if (commandText.startsWith(command)) {
                    String input = commandText.replace(command + " ", "") ;
                    if(input.compareTo(command) != 0) {
                        entry.getValue().setText(input);
                        return ;
                    }
                }
            }

        }
        else if (commandText.startsWith("type")) {
            String input = commandText.replace("type ", "") ;
            if (input.compareTo("B") == 0 || input.compareTo("C") == 0) {
                userTypeField.setText(input);
                return ;
            }

        }
        else if (commandText.startsWith(registerString)) {
            String output = "";
            if (commandText.compareTo(registerString) == 0) {
                output = "normalRegister" ;
            }
            else if (commandText.compareTo(registerString + " google") == 0) {
                output = "googleRegister" ;
            }
            else if (commandText.compareTo(registerString + " facebook") == 0) {
                output = "facebookRegister" ;
            }
            else {
                subscribeCommandLine.setStyle("-fx-border-color: red");
                return ;
            }
            System.out.println(output);
            return;
        }

        subscribeCommandLine.setStyle("-fx-border-color: red");


    }


}

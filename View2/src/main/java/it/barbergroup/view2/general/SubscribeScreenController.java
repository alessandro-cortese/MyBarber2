package it.barbergroup.view2.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Map;
import java.util.Map.Entry;

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
                "set password", subscribePasswordField) ;
    }



    @FXML
    public void onCommand(ActionEvent event) {
        String commandText = subscribeCommandLine.getText();

        subscribeCommandLine.setStyle(null);
        subscribeCommandLine.setText("");

        if (commandText.startsWith("set") && setCommand(commandText)) {
            return;
        }
        else if (commandText.startsWith("type") && typeCommand(commandText)) {
            return ;
        }
        else if (commandText.startsWith("register") && registerCommand(commandText)) {
            return ;
        }
        else if (commandText.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return ;
        }

        subscribeCommandLine.setStyle("-fx-border-color: red");
    }

    private boolean setCommand(String insertedCommand) {
        initMap();
        for (Entry<String, TextField> entry : textFieldMap.entrySet()) {
            String command = entry.getKey();
            if (insertedCommand.startsWith(command)) {
                String input = insertedCommand.replace(command + " ", "");
                if (input.compareTo(command) != 0) {
                    entry.getValue().setText(input);
                    return true;
                }
            }
        }
        return false ;
    }

    private boolean typeCommand(String command) {
        String input = command.replace("type ", "") ;
        if (input.compareTo("B") == 0 || input.compareTo("C") == 0) {
            userTypeField.setText(input);
            return true;
        }
        return false ;
    }

    private boolean registerCommand(String command) {
        String registerString = "register" ;
        boolean handled = false ;
        if (command.compareTo(registerString) == 0) {
            handled = true ;
        }
        else if (command.compareTo(registerString + " google") == 0) {
            handled = true ;
        }
        else if (command.compareTo(registerString + " facebook") == 0) {
            handled = true ;
        }
        return handled;
    }


}

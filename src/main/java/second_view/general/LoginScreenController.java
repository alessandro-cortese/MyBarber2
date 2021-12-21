package second_view.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginScreenController {


    @FXML TextField commandLine ;
    @FXML TextField emailField ;
    @FXML PasswordField passwordField ;

    @FXML Button enterButton ;

    private static final String[] commands = {"set email", "set password", "not login", "subscribe", "submit"} ;

    @FXML
    public void onCommand(ActionEvent event) throws IOException {
        String commandText = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");
        if (commandText.startsWith("set email")) {
            String username = commandText.replace(commands[0] + " ", "") ;
            if (username.compareTo(commands[0]) != 0) {
                emailField.setText(username);
            }
        }
        else if (commandText.startsWith("set password")) {
            String password = commandText.replace(commands[1] + " ", "") ;
            if (password.compareTo(commands[0]) != 0) {
                passwordField.setText(password);
            }
        }
        else if (commandText.compareTo("not login") == 0) {
            ScreenChanger.getInstance().changeScreen(event, ScreenChanger.CLIENT_HOME_SCREEN);
        }
        else if (commandText.compareTo("subscribe") == 0) {
            ScreenChanger.getInstance().changeScreen(event, ScreenChanger.SUBSCRIBE_SCREEN);
        }
        else if (commandText.compareTo("submit") == 0) {
            ScreenChanger.getInstance().changeScreen(event, ScreenChanger.CLIENT_HOME_SCREEN);
        }

        else {
            commandLine.setStyle("-fx-border-color: red") ;
        }


    }

}
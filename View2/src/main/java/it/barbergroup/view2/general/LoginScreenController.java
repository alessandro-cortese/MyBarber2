package it.barbergroup.view2.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        if (commandText.startsWith(commands[0])) {
            String username = commandText.replace(commands[0] + " ", "") ;
            emailField.setText(username);
        }
        else if (commandText.startsWith(commands[1])){
            String password = commandText.replace(commands[1] + " ", "") ;
            passwordField.setText(password);
        }
        else if (commandText.startsWith(commands[2])) {

        }
        else if (commandText.startsWith(commands[3])) {
            Stage myStage = (Stage)((Node) event.getSource()).getScene().getWindow() ;
            FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("it/barbergroup/view2/General/subscribe_screen.fxml")) ;
            Scene myScene = new Scene(root.load()) ;
            myStage.setScene(myScene);
        }

        else {
            commandLine.setStyle("-fx-border-color: red") ;
        }


    }

}
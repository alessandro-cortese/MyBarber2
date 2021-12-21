package second_view.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;

import java.io.IOException;

public class ClientSearchSaloon {

    @FXML TextField commandLine ;
    @FXML Label searchTypeLabel ;
    @FXML TextField searchText ;


    @FXML
    public void onCommand(ActionEvent event) throws IOException {
        String command = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");

        if (command.matches("search name (.*)")) {
            searchText.setText(command.replace("search name ", ""));
            searchTypeLabel.setText("Name");
        }
        else if (command.matches("search city (.*)")) {
            searchText.setText(command.replace("search city ", ""));
            searchTypeLabel.setText("City");
        }
        else if (command.matches("select ([0-9]+)")) {
            //Controllo che ci sia il salone con indice dato
            ScreenChanger.getInstance().changeScreen(event, ScreenChanger.CLIENT_BOOK_APPOINTMENT_SCREEN);
        }

        else if (command.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return ;
        }

        commandLine.setStyle("-fx-border-color: red");
    }

}

package second_view.client;

import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import second_view.general.ScreenChanger;

import java.io.IOException;

public class ClientBookAppointmentController {


    @FXML private TextField commandLine ;
    @FXML private ListView serviceListView ;
    @FXML private ListView ServiceSelectedListView;



    @FXML
    public void onCommand(ActionEvent event) throws IOException {
        String commandText = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");



        if (commandText.matches("service add [0-9]+")) {

            return;
        }
        else if (commandText.matches("service remove [0-9]+")) {
            //Rimuove indice da lista dei servizi aggiunti
            System.out.println(commandText.replace("service remove ", ""));
            return ;
        }
        else if (commandText.compareTo("go to payment") == 0) {
            ScreenChanger.getInstance().changeScreen(event, ScreenChanger.CLIENT_BOOK_TOTALPRICE);
            return ;
        }
        else if (commandText.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return;
        }



        commandLine.setStyle("-fx-border-color: red");

    }
}

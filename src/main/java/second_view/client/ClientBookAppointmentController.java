package second_view.client;

import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;

public class ClientBookAppointmentController {


    @FXML TextField commandLine ;
    @FXML TextField dateField ;
    @FXML TextField slotField ;
    @FXML ListView serviceList ;


    @FXML
    public void onCommand(ActionEvent event) {
        String commandText = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");

        if (commandText.matches("set date \\d{4}-\\d{2}-\\d{2}")) {
            String date = commandText.replace("set date ", "") ;
            dateField.setText(date);
            return ;
        }
        else if (commandText.matches("set slot [0-9]+")) {
            String slot = commandText.replace("set slot ", "") ;
            slotField.setText(slot);
            return ;
        }
        else if (commandText.matches("view date \\d{4}-\\d{2}-\\d{2}")) {
            //Cambia visualizzazione degli slot del barbiere
            return ;
        }
        else if (commandText.matches("service add [0-9]+")) {
            //Cambia lista dei servizi dell'appuntamento
            return;
        }
        else if (commandText.matches("service remove [0-9]+")) {
            //Rimuove indice da lista dei servizi aggiunti
            System.out.println(commandText.replace("service remove ", ""));
            return ;
        }
        else if (commandText.compareTo("book") == 0) {
            //ScreenChanger.getInstance().
            //Implementare in ScreenChanger logica di ritorno alla home
            return ;
        }

        commandLine.setStyle("-fx-border-color: red");

    }
}

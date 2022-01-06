package second_view.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;

import java.io.IOException;

public class ClientBookingDateHourGraphicContr{

    @FXML
    private TextField commandLine;

    @FXML
    private Button enterButton;

    @FXML
    private Label exceptionLabel;

    @FXML
    private Label exceptionLabel2;

    @FXML
    private ListView<?> hourListView;

    @FXML
    private TextField slotTimeField;

    @FXML
    private TextField dateField;

    @FXML
    void onCommand(ActionEvent event) throws IOException {
        String command = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");

        if (command.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return;
        }
        else if (command.matches("set date \\d{4}-\\d{2}-\\d{2}")){
            String date = command.replace("set date ", "") ;
            dateField.setText(date);
            return;
        }
        else if (command.matches("set slot time [0-9]+")) {
            String slot = command.replace("set slot ", "") ;
            slotTimeField.setText(slot);
            return ;
        }
        else if (command.compareTo("confirm") == 0) { //manda una notifica al controllore per ottenere le informazioni necessarie

            return ;
        }
        else if (command.compareTo("continue booking") == 0) {
            ScreenChanger.getInstance().changeScreen(event, ScreenChanger.CLIENT_BOOK_APPOINTMENT_SCREEN);
            return ;
        }
        else if (command.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return ;
        }
    }

}


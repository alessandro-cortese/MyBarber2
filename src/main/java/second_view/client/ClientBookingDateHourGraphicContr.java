package second_view.client;

import first_view.ObservableListNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import second_view.general.ScreenChanger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientBookingDateHourGraphicContr implements Initializable {

    public static final String CLIENT_SLOTTIME_ITEM = "first_view/list_item/client_slotTime_item.fxml";

    @FXML
    private TextField commandLine;

    @FXML
    private Button enterButton;

    @FXML
    private Label exceptionLabel;

    @FXML
    private Label exceptionLabel2;

    @FXML
    private ListView<Node> hourListView;

    @FXML
    private TextField slotTimeField;

    @FXML
    private TextField dateField;
    @FXML
    private  TextArea descriptionTextArea;

    @FXML
    private TextField saloonName;

    @FXML
    private TextField image;

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodesList = new Node[10];
        for (int i = 0; i < 10; i++) {

            try {
                nodesList[i] = (new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_SLOTTIME_ITEM))).load();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        ObservableListNode clientAppointmentsList = new ObservableListNode(nodesList);
        hourListView.setItems(clientAppointmentsList);
    }

}


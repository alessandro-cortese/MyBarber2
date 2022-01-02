package applicationController.graphic;

import first_view.ObservableListNode;
import first_view.pickers.TimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScheduleBookingGraphicController implements Initializable {
    private static final String CLIENT_BOOKED_SCREEN_NAME = "first_view/client/client_booked.fxml";
    private static final String SERVICE_ITEM ="first_view/listitem/barber_service_list_item.fxml";

    @FXML
    private Button bookedButton;

    @FXML
    private SplitMenuButton catalogueBooking;

    @FXML
    private DatePicker dateBooking;

    @FXML
    private ImageView saloonImage;

    @FXML
    private Label saloonName;

    @FXML
    private TextField textTime;

    @FXML
    private Button confirmDateHourButton;

    @FXML
    private ListView serviceListVIew;

    @FXML
    private Label seatNumberLabel;

    @FXML
    void onButtonSaloonClicked(ActionEvent event) throws IOException {
        Button sourceButton = (Button) event.getSource();
        Parent newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_BOOKED_SCREEN_NAME ))).load();
        Scene myScene = sourceButton.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        borderPane.setCenter(newCenterNode);
    }

    @FXML
    void onChangeTime(MouseEvent event) throws IOException {
        ((TextField) event.getSource()).setText((new TimePicker(0, 24)).getTime());
    }

    @FXML
    void selectService(ActionEvent event) throws IOException {

    }
    @FXML
    void ConfirmDateHour(ActionEvent event) throws IOException{ //qui chiamo la BookingDAO

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Node[] nodesList = new Node[10] ;
        for (int i = 0 ; i < 10 ; i++) {
            try {
                nodesList[i] = (new FXMLLoader(getClass().getClassLoader().getResource(SERVICE_ITEM))).load() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ObservableListNode clientAppointmentsList = new ObservableListNode(nodesList);
        serviceListVIew.setItems(clientAppointmentsList);

    }

}


package applicationController.graphic;

import first_view.pickers.TimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ScheduleBookingGraphicController {
    private static final String CLIENT_BOOKED_SCREEN_NAME = "first_view/client/client_booked.fxml";

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

}


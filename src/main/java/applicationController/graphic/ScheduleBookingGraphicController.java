package applicationController.graphic;

import applicationController.BookingController;
import engineering.bean.BookingBean;
import first_view.ObservableListNode;
import first_view.pickers.TimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
    private ListView serviceListView;

    @FXML
    private Label seatNumberLabel;

    @FXML
    private ListView<String> serviceSelectedListView;

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
    void ConfirmDateHour(ActionEvent event) throws IOException, ParseException { //qui chiamo la BookingDAO
       String bookingTime = textTime.getText()+":00";
        LocalDate date = dateBooking.getValue();
        Time time = Time.valueOf(bookingTime);
        //BookingBean bookingBean = new BookingBean(time, date);
        //BookingController bookingController = new BookingController().VerifyBooking(bookingBean);

        System.out.println(date);
        System.out.println(time);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodeService = new Node[10];
        Node[] nodesList = new Node[10] ;
        for (int i = 0 ; i < 10 ; i++) {

            try {
                nodesList[i] = (new FXMLLoader(getClass().getClassLoader().getResource(SERVICE_ITEM))).load() ;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        ObservableListNode clientAppointmentsList = new ObservableListNode(nodesList);
        serviceListView.setItems(clientAppointmentsList);

        ObservableList<String> list = FXCollections.observableArrayList();
        for (int j=0; j< nodeService.length;j++){
            list.add("e");
        }
        serviceSelectedListView.setItems(list);




    }

}


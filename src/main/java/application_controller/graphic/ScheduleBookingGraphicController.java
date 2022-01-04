package application_controller.graphic;

import first_view.ObservableListNode;
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
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScheduleBookingGraphicController implements Initializable {
    private static final String CLIENT_BOOKED_SCREEN_NAME = "first_view/client/client_booked.fxml";
    private static final String SERVICE_ITEM = "first_view/list_item/barber_service_list_item.fxml";
    private String saloonAddress;
    private String city;

    @FXML
    private Button bookedButton;

    @FXML
    private SplitMenuButton catalogueBooking;

    @FXML
    private ImageView saloonImage;

    @FXML
    private Label saloonName;

    @FXML
    private TextField textTime;

    @FXML
    private ListView serviceListView;



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
    void selectService(ActionEvent event) throws IOException {

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


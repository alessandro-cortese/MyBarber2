package application_controller.graphic;

import engineering.bean.SaloonBean;
import first_view.ObservableListNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;


public class ClientListViewController implements Initializable {
    @FXML
    private ListView<Node> saloonListView;

    @FXML
    private Label placeSaloonItem;

    @FXML
    Label labelSaloonItem;

    @FXML
    private Button saloonButton;

    private static final String APPOINTMENT_SALOON_ITEM = "first_view/list_item/take_saloon_item.fxml";
    private static final String CLIENT_DATEHOUR= "first_view/client/client_appointment_Hour&Date.fxml";

    private String saloonName;
    private String saloonAddress;
    private String saloonCity;
    private String saloonPhone;
    private int seatNumber;
    private Time slotTime;

    @FXML
    public void onButtonSaloonClicked(ActionEvent actionEvent) throws IOException {
        Button sourceButton = (Button) actionEvent.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_DATEHOUR));
        Parent newCenterNode = fxmlLoader.load();
        //BookingDateHourGraphicController bookingDateHourGraphicController = fxmlLoader.getController();
        // bookingDateHourGraphicController.display(saloonBean);
        Scene myScene = sourceButton.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        borderPane.setCenter(newCenterNode);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Node[] nodesList = new Node[10];
        for (int i = 0; i < 10; i++) {
            try {
                nodesList[i] = (new FXMLLoader(getClass().getClassLoader().getResource(APPOINTMENT_SALOON_ITEM))).load();
            } catch (IOException e) {
                e.getCause();
            }
        }
        ObservableListNode clientAppointmentsList = new ObservableListNode(nodesList);
        saloonListView.setItems(clientAppointmentsList);
    }


    public void display(SaloonBean saloonBean) {
        this.saloonName = saloonBean.getName();
        this.saloonAddress =saloonBean.getAddress();
        this.saloonCity = saloonBean.getCity();
        this.saloonPhone =saloonBean.getPhone();
        this.seatNumber = saloonBean.getSeatNumber();
        this.slotTime = saloonBean.getSlotTime();
    }

}

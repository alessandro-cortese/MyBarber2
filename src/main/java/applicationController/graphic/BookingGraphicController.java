package applicationController.graphic;

import applicationController.BookingController;
import engineering.bean.SaloonBean;
import first_view.ObservableListNode;
import first_view.general.InternalBackController;
import first_view.listCellFactories.SaloonListCellFactory;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookingGraphicController{


    private static final String CLIENT_TAKE_SALOON_SCREEN_NAME = "first_view/client/client_take_saloon.fxml";

    private static final String CLIENT_BOOKED_SCREEN_NAME = "first_view/client/client_booked.fxml";
    private static final String CLIENT_DATEHOUR= "first_view/client/client_appointment_Hour&Date.fxml";


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
    private ListView<Node> saloonListView= new ListView<>();
    @FXML
    private Button saloonButton;

    @FXML
    private Button saloonNextButton;

    @FXML
    private Tab saloonTab;

    @FXML
    private Button placeNextButton;

    @FXML
    private Tab placeTab;

    @FXML
    private TextField searchSaloonName;

    @FXML
    private TextField searchCity;

    @FXML
    Label showErr;
    @FXML
    Label showErr2;
    @FXML
    private Label NameSaloonItem;
    @FXML
    private Label placeSaloonItem;

    private SaloonBean saloonBean;
    private BookingController bookingController;
    private List<SaloonBean> saloonBeanList= new ArrayList<>();
    private SaloonBean saloonBeanName;
    @FXML
    public void onBookedButton(ActionEvent event) throws IOException {
        Button sourceButton = (Button) event.getSource();
        InternalBackController.getInternalBackControllerInstance().backToHome(sourceButton);
    }


    @FXML
    public void onButtonSaloonClicked(ActionEvent actionEvent) throws Exception {
        Parent newCenterNode=null;
       // String saloon = searchSaloonName.getText();
        //String saloonCity = searchCity.getText();
       // saloonBean = new SaloonBean(saloon, saloonCity);
        //bookingController = new BookingController();
        //saloonBeanList = bookingController.searchByCitySaloon(saloonBean); //ATT: POSSO COMUNICARE LL'ALTRO CONTROLLER GRAFICO IL SALOONBEANLIST? PER LA CITTÀ
        //saloonBeanName = bookingController.searchByNameSaloon(saloonBean);
        Button sourceButton = (Button) actionEvent.getSource();
        if(sourceButton == saloonNextButton) {
            newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_DATEHOUR))).load();
        }
        if(sourceButton == placeNextButton)
            newCenterNode =(new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_TAKE_SALOON_SCREEN_NAME))).load();
        if (sourceButton == bookedButton )
             newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_BOOKED_SCREEN_NAME ))).load();
        if(sourceButton == saloonButton)
            newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_DATEHOUR))).load();
        Scene myScene = sourceButton.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        borderPane.setCenter(newCenterNode);

    }

}
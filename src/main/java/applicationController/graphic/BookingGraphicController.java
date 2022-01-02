package applicationController.graphic;

import applicationController.BookingController;
import engineering.bean.SaloonBean;
import first_view.ObservableListNode;
import first_view.general.InternalBackController;
import first_view.listCellFactories.SaloonListCellFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookingGraphicController implements  Initializable{

    private static final String APPOINTMENT_SALOON_ITEM = "first_view/listitem/take_saloon_item.fxml";
    private static final String CLIENT_TAKE_SALOON_SCREEN_NAME = "first_view/client/client_take_saloon.fxml";
    private static final String CLIENT_SALOON_CENTER_SCREEN_NAME = "first_view/client/client_saloon_center.fxml";


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
    private Button bookedButton;

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
        /*if(saloon == "" ){
            showErr.setText("inserire un nome valido!");
            return;
        }
        if(saloonCity == "" ){
            showErr2.setText("Inserire una cittò valida!");
            return;
        }
        */

       // saloonBean = new SaloonBean(saloon, saloonCity);
        bookingController = new BookingController();
        //saloonBeanList = bookingController.searchByNameSaloon(saloonBean); //recupero i saloni
        Button sourceButton = (Button) actionEvent.getSource();
        if(sourceButton == saloonNextButton) {
            newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_SALOON_CENTER_SCREEN_NAME))).load();
        }
        if(sourceButton == placeNextButton)
            newCenterNode =(new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_TAKE_SALOON_SCREEN_NAME))).load();
        Scene myScene = sourceButton.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        borderPane.setCenter(newCenterNode);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodesList = new Node[saloonBeanList.size()] ;
        for (int i = 0 ; i < saloonBeanList.size() ; i++) {
            try {
                nodesList[i] = (new FXMLLoader(getClass().getClassLoader().getResource(APPOINTMENT_SALOON_ITEM ))).load() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ObservableListNode saloonListNode = new ObservableListNode(nodesList);
        saloonListView.setItems(saloonListNode);

    }
}
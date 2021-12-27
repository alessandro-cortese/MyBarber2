package applicationController.graphic;

import applicationController.logic.BookingController;
import engineering.bean.SaloonBean;
import first_view.ObservableListNode;
import first_view.general.InternalBackController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BookingGraphicController {

    private static final String APPOINTMENT_SALOON_ITEM = "first_view/listitem/take_saloon_item.fxml";
    private static final String CLIENT_TAKE_SALOON_SCREEN_NAME = "first_view/client/client_take_saloon.fxml";

    private static final String CLIENT_SALOON_CENTER_SCREEN_NAME = "first_view/client/client_saloon_center.fxml";

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
    public void onBookedButton(ActionEvent event) throws IOException{
        Button sourceButton = (Button) event.getSource();
        InternalBackController.getInternalBackControllerInstance().backToHome(sourceButton);
    }


    @FXML
    public void onButtonSaloonClicked(ActionEvent actionEvent) throws Exception {
        String saloon = searchSaloonName.getText();
        String saloonCity = searchCity.getText();
        if(saloon == "" ){
            showErr.setText("inserire un nome valido!");
            return;
        }
        if(saloonCity == ""){
            showErr2.setText("Inserire una cittò valida!");
            return;
        }

        SaloonBean saloonBean = new SaloonBean(saloon,saloonCity);

        BookingController bookingController = new BookingController();
        bookingController.searchByNameSaloon(saloonBean);


        Button sourceButton = (Button) actionEvent.getSource();
        Parent newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_TAKE_SALOON_SCREEN_NAME))).load();
        ListView listView = (ListView) newCenterNode.lookup("#saloonListView");
        onLoadListItems(listView, APPOINTMENT_SALOON_ITEM);
        Scene myScene = (Scene) sourceButton.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        borderPane.setCenter(newCenterNode);

    }

    @FXML
    void onButtonClicked(ActionEvent event) throws IOException {
        Node sourceButton = (Node) event.getSource();
        InternalBackController.getInternalBackControllerInstance().onNextScreen(sourceButton);
        FXMLLoader node = new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_SALOON_CENTER_SCREEN_NAME));
        Scene myScene =  sourceButton.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        borderPane.setCenter(node.load());
    }

    private void onLoadListItems(ListView listView, String itemResource) throws IOException {
        Node[] nodesList = new Node[10] ;
        for (int i = 0 ; i < 10 ; i++) {
            nodesList[i] = (new FXMLLoader(getClass().getClassLoader().getResource(itemResource))).load() ;
        }
        ObservableListNode saloonList = new ObservableListNode(nodesList);
        listView.setItems(saloonList);
    }


}

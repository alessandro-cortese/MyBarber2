package first_view.barber;
import first_view.ObservableListNode;
import first_view.general.InternalBackController;
import first_view.pickers.PricePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BarberListServiceController implements Initializable {

    @FXML private ListView<Node> barberServiceListView;

    @FXML private TextField nameServiceTextField;
    @FXML private TextField descriptionServiceTextField;
    @FXML private TextField priceServiceTextField;
    @FXML private TextField nameOfProductTextField;

    @FXML private Button modifyServiceButton;
    @FXML private Button addServiceButton;
    @FXML private Button deleteServiceButton;

    private static final String BARBER_SERVICE_LIST_ITEM = "first_view/listitem/barber_service_list_item.fxml";
    private static final String BARBER_MODIFY_SERVICE_SCREEN_NAME = "first_view/barber/barber_modify_service.fxml";
    private static final String BARBER_ADD_SERVICE_SCREEN_NAME = "first_view/barber/barber_add_service.fxml";

    @FXML
    public void onPricePicked(MouseEvent event) throws IOException {
        TextField sourceTextField = (TextField) event.getSource();
        if(sourceTextField == priceServiceTextField){
            PricePicker pricePicker = new PricePicker(0, 0.0);
            priceServiceTextField.setText(pricePicker.getPrice());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Node[] nodes = new Node[5];
        for (int i = 0 ; i < nodes.length ; i++) {
            try {
                nodes[i] = (new FXMLLoader(getClass().getClassLoader().getResource(BARBER_SERVICE_LIST_ITEM))).load() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ObservableListNode barberCenterObservableListNode = new ObservableListNode(nodes);
        barberServiceListView.setItems(barberCenterObservableListNode);

    }

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Node serviceNode = (Node) event.getSource();

        if(serviceNode == modifyServiceButton) {
            InternalBackController.getInternalBackControllerInstance().onNextScreen(serviceNode);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(BARBER_MODIFY_SERVICE_SCREEN_NAME));
            BorderPane myBorderPane = (BorderPane) serviceNode.getScene().getRoot();
            myBorderPane.setCenter(fxmlLoader.load());
        }
        else if(serviceNode == addServiceButton) {
            InternalBackController.getInternalBackControllerInstance().onNextScreen(serviceNode);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(BARBER_ADD_SERVICE_SCREEN_NAME));
            BorderPane myBorderPane = (BorderPane) serviceNode.getScene().getRoot();
            myBorderPane.setCenter(fxmlLoader.load());
        }
        else if(serviceNode == deleteServiceButton) {
            System.out.println("Delete");
        }

    }

}


package first_view.barber;

import applicationController.ManageServiceController;
import engineering.bean.ServiceBean;
import engineering.exception.NegativePriceException;
import first_view.general.InternalBackController;
import first_view.listCellFactories.ServiceListCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BarberListServiceController implements Initializable {

    @FXML private ListView<ServiceBean> barberServiceListView;
    @FXML private TextField nameServiceTextField;
    @FXML private TextField descriptionServiceTextField;
    @FXML private TextField priceServiceTextField;
    @FXML private TextField nameOfProductTextField;
    @FXML private Button modifyServiceButton;
    @FXML private Button addServiceButton;


    private ArrayList<ServiceBean> arrayList ;

    private static final String BARBER_MODIFY_SERVICE_SCREEN_NAME = "first_view/barber/barber_modify_service.fxml";
    private static final String BARBER_ADD_SERVICE_SCREEN_NAME = "first_view/barber/barber_add_service.fxml";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ManageServiceController manageServiceController = new ManageServiceController();

        try {
            arrayList = manageServiceController.getAllService();
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }

        barberServiceListView.setCellFactory(param -> new ServiceListCellFactory());

        barberServiceListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            modifyServiceButton.setDisable(newValue == null);
            addServiceButton.setDisable(newValue == null);
            assert newValue != null;
            nameServiceTextField.setText(newValue.getName());
            descriptionServiceTextField.setText(newValue.getDescription());
            nameOfProductTextField.setText(newValue.getNameOfUsedProduct());
            priceServiceTextField.setText(Double.toString(newValue.getPrice()));
        });

        modifyServiceButton.setDisable(true);
        addServiceButton.setDisable(true);

        barberServiceListView.getItems().clear();
        barberServiceListView.setItems(FXCollections.observableList(arrayList));

    }

    @FXML
    public void onButtonClicked(ActionEvent event) throws Exception {
        Node serviceNode = (Node) event.getSource();

        if(serviceNode == modifyServiceButton) {
            InternalBackController.getInternalBackControllerInstance().onNextScreen(serviceNode);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(BARBER_MODIFY_SERVICE_SCREEN_NAME));
            BorderPane myBorderPane = (BorderPane) serviceNode.getScene().getRoot();
            myBorderPane.setCenter(fxmlLoader.load());

            String localNameOfUsedProduct = (nameOfProductTextField.getText() == null ? "" : nameOfProductTextField.getText());

            ServiceBean localServiceBean = new ServiceBean(nameServiceTextField.getText(), descriptionServiceTextField.getText(), localNameOfUsedProduct, Double.parseDouble(priceServiceTextField.getText()));
            BarberModifyServiceController barberModifyServiceController = fxmlLoader.getController();
            barberModifyServiceController.displayServiceToModify(localServiceBean);

        }
        else if(serviceNode == addServiceButton) {
            InternalBackController.getInternalBackControllerInstance().onNextScreen(serviceNode);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(BARBER_ADD_SERVICE_SCREEN_NAME));
            BorderPane myBorderPane = (BorderPane) serviceNode.getScene().getRoot();
            myBorderPane.setCenter(fxmlLoader.load());
        }

    }

}


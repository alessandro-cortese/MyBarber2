package first_view.barber;
import first_view.general.InternalBackController;
import first_view.pickers.PricePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BarberListServiceController {

    @FXML private TextField nameServiceTextField;
    @FXML private TextField descriptionServiceTextField;
    @FXML private TextField priceServiceTextField;
    @FXML private TextField nameOfProductTextField;

    @FXML private Button modifyServiceButton;
    @FXML private Button addServiceButton;
    @FXML private Button deleteServiceButton;

    @FXML
    public void onPricePicked(MouseEvent event) throws IOException {
        TextField sourceTextField = (TextField) event.getSource();
        if(sourceTextField == priceServiceTextField){
            PricePicker pricePicker = new PricePicker(0, 0.0);
            priceServiceTextField.setText(pricePicker.getPrice());
        }

    }

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Node serviceNode = (Node) event.getSource();

        if(serviceNode == modifyServiceButton) {
            nameOfProductTextField.setEditable(true);
            nameServiceTextField.setEditable(true);
            priceServiceTextField.setEditable(true);
            descriptionServiceTextField.setEditable(true);
        }
        else if(serviceNode == addServiceButton) {
            InternalBackController.getInternalBackControllerInstance().onNextScreen(serviceNode);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("first_view/barber/barber_add_service.fxml"));
            BorderPane myBorderPane = (BorderPane) serviceNode.getScene().getRoot();
            myBorderPane.setCenter(fxmlLoader.load());
        }
        else if(serviceNode == deleteServiceButton) {
            System.out.println("Delete");
        }

    }

}


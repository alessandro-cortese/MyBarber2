package first_view.barber;

import engineering.bean.ServiceBean;
import engineering.exception.NegativePriceException;
import first_view.general.InternalBackController;
import first_view.pickers.PricePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BarberAddServiceController {

    private static final String BARBER_CONFIRM_ADD_SERVICE_SCREEN_NAME = "first_view/barber/barber_confirm_add_service.fxml" ;

    @FXML private Button continueButton ;
    @FXML private TextField priceTextField ;
    @FXML private TextField nameAddServiceTextField ;
    @FXML private TextField descriptionTextFiledAddService ;
    @FXML private TextField nameOfUsedProductTextField ;
    @FXML private Label exceptionAddServiceLabel;

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Node sourceNode = (Node) event.getSource();
        if(sourceNode == continueButton){
            InternalBackController.getInternalBackControllerInstance().onNextScreen(sourceNode);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(BARBER_CONFIRM_ADD_SERVICE_SCREEN_NAME));
            BorderPane myBorderPane = (BorderPane) sourceNode.getScene().getRoot();
            myBorderPane.setCenter(fxmlLoader.load());

            try {

                ServiceBean serviceBean = new ServiceBean(nameAddServiceTextField.getText(),
                        descriptionTextFiledAddService.getText(),
                        nameOfUsedProductTextField.getText(),
                        Double.parseDouble(priceTextField.getText()));

                BarberConfirmAddServiceController barberConfirmAddServiceController = fxmlLoader.getController() ;
                barberConfirmAddServiceController.display(serviceBean) ;

            }catch(NegativePriceException e){
                exceptionAddServiceLabel.setText("Insert Price is negative!");
            }

        }

    }


    @FXML
    public void onPricePicked(MouseEvent event) throws IOException {
        TextField sourceTextField = (TextField) event.getSource();
        if(sourceTextField == priceTextField){
            PricePicker pricePicker = new PricePicker(0, 0.0);
            priceTextField.setText(pricePicker.getPrice());
        }
    }

}

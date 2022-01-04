package first_view.barber;

import engineering.bean.ServiceBean;
import first_view.general.InternalBackController;
import first_view.pickers.PricePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import static engineering.otherclasses.NumericVerify.isNumeric;

public class BarberModifyServiceController {

    @FXML private TextField nameModifyServiceTextField;
    @FXML private TextField modifyServicePriceTextField;
    @FXML private TextField descriptionTextFieldModifyService;
    @FXML private TextField modifyServiceNameOfUsedProductTextField;
    @FXML private Button saveChangesButton;
    @FXML private Button deleteServiceButton;

    private ServiceBean serviceBean;

    private static final String BARBER_DELETE_SERVICE_SCREEN_NAME = "first_view/barber/barber_confirm_delete_service.fxml" ;

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Button sourceButton = (Button) event.getSource();

        if(sourceButton == saveChangesButton && nameModifyServiceTextField.getText() != null && isNumeric(modifyServicePriceTextField.getText())
                && descriptionTextFieldModifyService.getText() != null) {

            System.out.println("Modify");

            InternalBackController.getInternalBackControllerInstance().backToHome(sourceButton);

        }
        else if(sourceButton == deleteServiceButton) {

            InternalBackController.getInternalBackControllerInstance().onNextScreen(sourceButton);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(BARBER_DELETE_SERVICE_SCREEN_NAME));
            BorderPane myBorderPane = (BorderPane) (sourceButton.getScene()).getRoot();
            myBorderPane.setCenter(fxmlLoader.load());

            BarberConfirmDeleteServiceController barberConfirmDeleteServiceController = fxmlLoader.getController();
            barberConfirmDeleteServiceController.displayServiceToDelete(this.serviceBean);

        }

    }

    @FXML
    public void onPricePicked(MouseEvent event) throws IOException {
        TextField sourceTextField = (TextField) event.getSource();
        if(sourceTextField == modifyServicePriceTextField){
            PricePicker pricePicker = new PricePicker(0, 0.0);
            modifyServicePriceTextField.setText(pricePicker.getPrice());
        }
    }

    public void displayServiceToModify(ServiceBean serviceBean) {

        this.serviceBean = serviceBean;

        nameModifyServiceTextField.setText(serviceBean.getName());
        modifyServiceNameOfUsedProductTextField.setText(serviceBean.getNameOfUsedProduct());
        descriptionTextFieldModifyService.setText(serviceBean.getDescription());
        modifyServicePriceTextField.setText(Double.toString(serviceBean.getPrice()));
    }


}
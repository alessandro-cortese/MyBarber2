package first_view.barber;

import application_controller.ManageServiceController;
import engineering.bean.ServiceBean;
import engineering.exception.InsertNegativePriceException;
import first_view.general.InternalBackController;
import first_view.pickers.PricePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import static engineering.other_classes.NumericVerify.isNumeric;

public class BarberModifyServiceController {

    @FXML private TextField nameModifyServiceTextField;
    @FXML private TextField modifyServicePriceTextField;
    @FXML private TextField descriptionTextFieldModifyService;
    @FXML private TextField modifyServiceNameOfUsedProductTextField;
    @FXML private Button saveChangesButton;
    @FXML private Button deleteServiceButton;
    @FXML private Label modifyServiceExceptionLabelFirstView;

    private ServiceBean serviceBean;

    private static final String BARBER_DELETE_SERVICE_SCREEN_NAME = "first_view/barber/barber_confirm_delete_service.fxml" ;

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Button sourceButton = (Button) event.getSource();
        boolean flag = true;

        if(sourceButton == saveChangesButton && nameModifyServiceTextField.getText() != null && isNumeric(modifyServicePriceTextField.getText())
                && descriptionTextFieldModifyService.getText() != null) {

            ServiceBean updateServiceBean = null;
            try {
                updateServiceBean = new ServiceBean(nameModifyServiceTextField.getText(), descriptionTextFieldModifyService.getText(), modifyServiceNameOfUsedProductTextField.getText(), Double.parseDouble(modifyServicePriceTextField.getText()));
            } catch (InsertNegativePriceException e) {
                modifyServiceExceptionLabelFirstView.setText("Prezzo inserito non valido!");
                flag = false;
            }

            if(flag) {
                ManageServiceController manageServiceController = new ManageServiceController();
                manageServiceController.modifyService(serviceBean, updateServiceBean, InternalBackController.getInternalBackControllerInstance().getLoggedUser().getUserEmail());

                InternalBackController.getInternalBackControllerInstance().backToHome(sourceButton);
            }
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

        nameModifyServiceTextField.setText(serviceBean.getNameInfo());
        modifyServiceNameOfUsedProductTextField.setText(serviceBean.getNameOfUsedProductInfo());
        descriptionTextFieldModifyService.setText(serviceBean.getDescriptionInfo());
        modifyServicePriceTextField.setText(Double.toString(serviceBean.getPriceInfo()));
    }


}
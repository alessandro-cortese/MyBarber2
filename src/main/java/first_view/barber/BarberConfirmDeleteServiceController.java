package first_view.barber;

import engineering.bean.ServiceBean;
import first_view.general.InternalBackController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BarberConfirmDeleteServiceController {

    private static final String BARBER_HOME_SCREEN_NAME = "first_view/barber/barber_home.fxml" ;

    @FXML private TextField nameDeleteServiceTextField;
    @FXML private TextField priceDeleteServiceTextField;
    @FXML private TextField descriptionDeleteServiceTextField;
    @FXML private TextField nameOfServiceToDeleteTextField;
    @FXML private Button deleteConfirmButton;


    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {

        Node deleteEvent = (Node) event.getSource();

        if(deleteEvent == deleteConfirmButton) {
            System.out.println("Delete");
            InternalBackController.getInternalBackControllerInstance().onNextScreen(deleteEvent);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(BARBER_HOME_SCREEN_NAME));
            BorderPane myBorderPane = (BorderPane) deleteEvent.getScene().getRoot();
            myBorderPane.setCenter(fxmlLoader.load());
        }

    }

    public void displayServiceToDelete(ServiceBean serviceBean) {

        nameDeleteServiceTextField.setText(serviceBean.getName());
        priceDeleteServiceTextField.setText(Double.toString(serviceBean.getPrice()));
        descriptionDeleteServiceTextField.setText(serviceBean.getDescription());
        nameOfServiceToDeleteTextField.setText(serviceBean.getNameOfUsedProduct());

    }



}

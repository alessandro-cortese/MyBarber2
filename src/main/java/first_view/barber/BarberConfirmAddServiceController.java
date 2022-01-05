package first_view.barber;

import engineering.bean.ServiceBean;
import first_view.general.InternalBackController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BarberConfirmAddServiceController {

    @FXML private TextField nameConfirmedAddService ;
    @FXML private TextField priceConfirmedAddService ;
    @FXML private TextField descriptionConfirmedAddService ;
    @FXML private TextField nameOfUsedProductConfirmedAddService ;
    @FXML private Button saveConfirmButton;

    @FXML
    public void onButtonClicked(ActionEvent event) {

        Node sourceNode = (Node) event.getSource();

        if(sourceNode == saveConfirmButton) {
            InternalBackController.getInternalBackControllerInstance().backToHome(sourceNode);
        }

    }

    public void display(ServiceBean localServiceBean) {

        nameConfirmedAddService.setText(localServiceBean.getName()) ;
        priceConfirmedAddService.setText(Double.toString(localServiceBean.getPrice())) ;
        descriptionConfirmedAddService.setText(localServiceBean.getDescription()) ;
        nameOfUsedProductConfirmedAddService.setText(localServiceBean.getNameOfUsedProduct());

    }

}
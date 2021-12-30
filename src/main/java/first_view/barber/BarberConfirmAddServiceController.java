package first_view.barber;

import engineering.bean.ServiceBean;
import first_view.general.InternalBackController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BarberConfirmAddServiceController {

    private ServiceBean serviceBean ;

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

        this.serviceBean = localServiceBean;
        nameConfirmedAddService.setText(serviceBean.getName()) ;
        priceConfirmedAddService.setText(Double.toString(serviceBean.getPrice())) ;
        descriptionConfirmedAddService.setText(serviceBean.getDescription()) ;
        nameOfUsedProductConfirmedAddService.setText(serviceBean.getNameOfUsedProduct());

    }

}
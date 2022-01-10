package first_view.barber;

import application_controller.AddServiceController;
import engineering.bean.ServiceBean;
import engineering.bean.UserBean;
import first_view.general.InternalBackController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BarberConfirmAddServiceController {

    private ServiceBean serviceBean;

    @FXML private TextField nameConfirmedAddService ;
    @FXML private TextField priceConfirmedAddService ;
    @FXML private TextField descriptionConfirmedAddService ;
    @FXML private TextField nameOfUsedProductConfirmedAddService ;
    @FXML private Button saveConfirmButton;


    @FXML
    public void onButtonClicked(ActionEvent event) {

        Node sourceNode = (Node) event.getSource();
        UserBean userBean = InternalBackController.getInternalBackControllerInstance().getLoggedUser();


        if(sourceNode == saveConfirmButton) {
            InternalBackController.getInternalBackControllerInstance().backToHome(sourceNode);
            AddServiceController addServiceController = new AddServiceController();
            addServiceController.addService(serviceBean,userBean);
        }

    }

    public void display(ServiceBean localServiceBean) {

        this.serviceBean = localServiceBean;

        nameConfirmedAddService.setText(localServiceBean.getName()) ;
        priceConfirmedAddService.setText(Double.toString(localServiceBean.getPrice())) ;
        descriptionConfirmedAddService.setText(localServiceBean.getDescription()) ;
        nameOfUsedProductConfirmedAddService.setText(localServiceBean.getNameOfUsedProduct());

    }

}
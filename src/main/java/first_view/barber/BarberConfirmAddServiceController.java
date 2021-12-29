package first_view.barber;

import first_view.general.InternalBackController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class BarberConfirmAddServiceController {

    @FXML private Button saveConfirmButton;

    @FXML
    public void onButtonClicked(ActionEvent event) {

        Node sourceNode = (Node) event.getSource();

        if(sourceNode == saveConfirmButton) {
            InternalBackController.getInternalBackControllerInstance().backToHome(sourceNode);
        }


    }



}

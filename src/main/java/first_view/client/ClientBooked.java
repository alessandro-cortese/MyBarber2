package first_view.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import first_view.general.InternalBackController;

public class ClientBooked {

    @FXML
    private Button bookedButton;

    @FXML
    void onButtonClicked(ActionEvent event) {
        Node sourceButton = (Node) event.getSource();
        InternalBackController.getInternalBackControllerInstance().backToHome(sourceButton);
    }

}

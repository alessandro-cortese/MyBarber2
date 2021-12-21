package firstview.barber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import firstview.general.InternalBackController;

import java.io.IOException;

public class BarberModifyCenter {

    @FXML private Button saveButton;
    @FXML private Button modifyCenterSchedule;

    private static final String BARBER_SCHEDULE_SCREEN_NAME = "firstview/barber/barber_schedule.fxml";

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Node sourceNode = (Node) event.getSource();

        if(sourceNode == saveButton){
            InternalBackController.getInternalBackControllerInstance().backToHome(sourceNode);
            return ;
        }

        String nextScreenName ;
        if( sourceNode == modifyCenterSchedule){
            nextScreenName = BARBER_SCHEDULE_SCREEN_NAME ;
        }
        else {
            nextScreenName = null ;
        }
        if (nextScreenName != null) {
            InternalBackController.getInternalBackControllerInstance().onNextScreen(sourceNode);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(nextScreenName));
            BorderPane myBorderPane = (BorderPane) sourceNode.getScene().getRoot();
            myBorderPane.setCenter(fxmlLoader.load());
        }
    }

}

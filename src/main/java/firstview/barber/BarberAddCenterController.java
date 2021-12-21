package firstview.barber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import firstview.general.InternalBackController;

import java.io.IOException;

public class BarberAddCenterController {

    @FXML Button backButton ;
    @FXML Button continueButton ;

    private static final String BARBER_SCHEDULE_SCREEN_NAME = "firstview/barber/barber_schedule.fxml";

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {

        Node sourceNode = (Node) event.getSource();
        FXMLLoader fxmlLoader;
        if(sourceNode == continueButton){
            InternalBackController.getInternalBackControllerInstance().onNextScreen(sourceNode);
            Scene myScene = sourceNode.getScene() ;
            fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(BARBER_SCHEDULE_SCREEN_NAME));
            BorderPane myBorderPane = (BorderPane) myScene.getRoot();
            myBorderPane.setCenter(fxmlLoader.load());


        }



    }
}

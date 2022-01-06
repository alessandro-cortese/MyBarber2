package first_view.barber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.BorderPane;
import first_view.general.InternalBackController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BarberModifyCenter implements Initializable {

    @FXML private Button saveButton;
    @FXML private Button modifyCenterSchedule;
    @FXML private Spinner<Integer> slotTimeSpinner;
    @FXML private Spinner<Integer> numberOfSeatsSpinner;

    private static final String BARBER_SCHEDULE_SCREEN_NAME = "first_view/barber/barber_schedule.fxml";

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Node sourceNode = (Node) event.getSource();
        FXMLLoader fxmlLoader;
        BorderPane myBorderPane;


        if(sourceNode == saveButton){
            InternalBackController.getInternalBackControllerInstance().backToHome(sourceNode);
            return ;
        }


        if( sourceNode == modifyCenterSchedule){

            InternalBackController.getInternalBackControllerInstance().onNextScreen(sourceNode);
            fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(BARBER_SCHEDULE_SCREEN_NAME));
            myBorderPane = (BorderPane) sourceNode.getScene().getRoot();
            myBorderPane.setCenter(fxmlLoader.load());

            Integer slotMinutes = slotTimeSpinner.getValue();

            BarberScheduleTimeController barberScheduleTimeController = fxmlLoader.getController();
            barberScheduleTimeController.setSlotMinutes(slotMinutes);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        SpinnerValueFactory<Integer> firstSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 60);
        SpinnerValueFactory<Integer> secondSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);

        slotTimeSpinner.setValueFactory(firstSpinnerValueFactory);
        numberOfSeatsSpinner.setValueFactory(secondSpinnerValueFactory);

    }
}

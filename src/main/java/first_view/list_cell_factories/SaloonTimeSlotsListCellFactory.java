package first_view.list_cell_factories;

import engineering.bean.SaloonBean;
import engineering.time.ScheduleTime;
import engineering.time.TimeSlot;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class SaloonTimeSlotsListCellFactory  extends ListCell<TimeSlot> {
    private static final String LIST_ITEM_SALOON_TIMESLOTS="first_view/list_item/client_time_slot_item.fxml";
    private static final String INIT_TIME = "initTime";
    private static final String FINAL_TIME ="finalTime";
    private static final String SEAT_NUMBER="seatNumber"; //da implemetare sul fxml
    private Parent parentNode = null ;

    @Override
    protected void updateItem(TimeSlot item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null) {
            try {
                parentNode = new FXMLLoader(getClass().getClassLoader().getResource(LIST_ITEM_SALOON_TIMESLOTS)).load();

                Label initTime = (Label) parentNode.lookup("#" + INIT_TIME) ;
                Label finalTime = (Label) parentNode.lookup("#" + FINAL_TIME) ;
                Label seatNumber = (Label)parentNode.lookup("#" + SEAT_NUMBER);
                initTime.setText(String.valueOf(item.getFromTime()));
                finalTime.setText(String.valueOf(item.getToTime()));
                seatNumber.setText("Posti disponibili: "+ String.valueOf(item.getSeatAvailable()));

                setGraphic(parentNode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

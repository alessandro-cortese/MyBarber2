package first_view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class ListCellFactory extends ListCell<String> {

    private Parent parentNode = null ;

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null) {
            try {
                if (parentNode == null) parentNode = new FXMLLoader(getClass().getClassLoader().getResource("com/example/demo/client_see_appointments_list_item.fxml")).load() ;
                setGraphic(parentNode);

                Label appointmentDateLabel = (Label) parentNode.lookup("#appointmentDateLabel") ;
                appointmentDateLabel.setText(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

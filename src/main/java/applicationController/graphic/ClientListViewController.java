package applicationController.graphic;

import first_view.ObservableListNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientListViewController implements Initializable {
    @FXML
    private ListView<Node> saloonListView;
    private static final String APPOINTMENT_SALOON_ITEM = "first_view/listitem/take_saloon_item.fxml";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Node[] nodesList = new Node[10];
        for (int i = 0; i < 10; i++) {
            try {
                nodesList[i] = (new FXMLLoader(getClass().getClassLoader().getResource(APPOINTMENT_SALOON_ITEM))).load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ObservableListNode clientAppointmentsList = new ObservableListNode(nodesList);
        saloonListView.setItems(clientAppointmentsList);
    }
}

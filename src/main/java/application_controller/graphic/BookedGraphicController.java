package application_controller.graphic;

import first_view.ObservableListNode;
import first_view.general.InternalBackController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookedGraphicController implements Initializable {

    private static final String SERVICE_ITEM = "first_view/list_item/barber_service_list_item.fxml";

        @FXML
        private ListView<Node> serviceSelectedListView;

        @FXML
        void onBookedButton(ActionEvent event) throws IOException {
            InternalBackController internalBackController = InternalBackController.getInternalBackControllerInstance();
            internalBackController.backToHome((Node) event.getSource());


        }



        @Override
        public void initialize(URL location, ResourceBundle resources) {
            Node[] nodeService = new Node[10];
            for (int i = 0; i < 10; i++) {

                try {
                    nodeService[i] = (new FXMLLoader(getClass().getClassLoader().getResource(SERVICE_ITEM))).load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ObservableListNode clientAppointmentsList = new ObservableListNode(nodeService);
            serviceSelectedListView.setItems(clientAppointmentsList);
        }
    }

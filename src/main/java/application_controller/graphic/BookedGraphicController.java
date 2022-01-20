package application_controller.graphic;

import engineering.bean.ServiceBean;
import first_view.ObservableListNode;
import first_view.general.InternalBackController;
import first_view.list_cell_factories.ServiceListCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import model.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookedGraphicController{

    private static final String SERVICE_ITEM = "first_view/list_item/barber_service_list_item.fxml";

        @FXML
        private ListView<ServiceBean> serviceSelectedListView;

        @FXML
        void onBookedButton(ActionEvent event) throws IOException {
            InternalBackController internalBackController = InternalBackController.getInternalBackControllerInstance();
            internalBackController.backToHome((Node) event.getSource());
        }


    public void injectServicesList(List<ServiceBean> services) {
        serviceSelectedListView.setCellFactory(param -> new ServiceListCellFactory(SERVICE_ITEM));
        serviceSelectedListView.getItems().clear();
        serviceSelectedListView.setItems(FXCollections.observableList(services));

    }
}

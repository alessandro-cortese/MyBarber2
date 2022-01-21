package first_view.barber;

import application_controller.BarberManageOrderController;
import engineering.bean.UserBean;
import engineering.bean.buy_product.CartRowBean;
import engineering.bean.buy_product.VendorOrderBean;
import first_view.general.InternalBackController;
import first_view.list_cell_factories.BarberOrderListCellFactory;
import first_view.list_cell_factories.CartRowListCellFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class BarberManageOrderGUIController implements Initializable {

    @FXML private Label orderAddressLabel;
    @FXML private ListView<CartRowBean> orderCartListView;
    @FXML private ListView<VendorOrderBean> orderListView;
    @FXML private Label orderOwnerLabel;
    @FXML private Label orderTelephoneLabel;

    private final BarberManageOrderController barberManageOrderController ;

    public BarberManageOrderGUIController() {
        UserBean loggedUser = InternalBackController.getInternalBackControllerInstance().getLoggedUser();
        barberManageOrderController = new BarberManageOrderController(loggedUser) ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderListView.setCellFactory(param -> new BarberOrderListCellFactory());

        orderCartListView.setCellFactory(param -> new CartRowListCellFactory()) ;

        orderListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                List<CartRowBean> cartRowBeans = barberManageOrderController.showOrderCart(newValue);
                Collections.reverse(cartRowBeans);
                orderCartListView.setItems(FXCollections.observableList(cartRowBeans));
                orderTelephoneLabel.setText(newValue.getTelephone());
                orderAddressLabel.setText(newValue.getAddress());
                orderOwnerLabel.setText(newValue.getOrderOwner());
            }
            else {
                orderCartListView.setItems(FXCollections.observableList(new ArrayList<>()));
                orderTelephoneLabel.setText("");
                orderAddressLabel.setText("");
                orderOwnerLabel.setText("");
            }
        });
        viewAllOrders() ;
    }

    private void viewAllOrders() {
        List<VendorOrderBean> vendorOrderBeans = barberManageOrderController.showAllOrders() ;
        orderListView.setItems(FXCollections.observableList(vendorOrderBeans));
    }
}

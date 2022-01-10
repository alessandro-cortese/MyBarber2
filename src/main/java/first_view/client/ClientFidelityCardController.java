package first_view.client;

import application_controller.ManageCouponController;
import engineering.bean.FidelityCardBean;
import engineering.bean.UserBean;
import engineering.bean.buy_product.CouponBean;
import engineering.exception.InvalidCouponException;
import first_view.general.InternalBackController;
import first_view.list_cell_factories.CouponCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientFidelityCardController implements Initializable {

    @FXML private Button generateFiveButton ;
    @FXML private Button generateTenButton ;
    @FXML private Button generateTwentyButton ;
    @FXML private ListView<CouponBean> couponListView ;
    @FXML private Label pointsSaleLabel ;

    private final ManageCouponController manageCouponController ;

    public ClientFidelityCardController() {
        manageCouponController = new ManageCouponController() ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        couponListView.setCellFactory(param -> new CouponCellFactory());

        UserBean userBean = InternalBackController.getInternalBackControllerInstance().getLoggedUser() ;
        if (userBean != null) {
            FidelityCardBean fidelityCardBean = manageCouponController.showFidelityCard(userBean) ;
            couponListView.setItems(FXCollections.observableList(fidelityCardBean.getCouponBeans()));
            pointsSaleLabel.setText("Totale Punti " + fidelityCardBean.getPointsSale() );
        }
        generateFiveButton.setDisable(userBean == null);
        generateTenButton.setDisable(userBean == null);
        generateTwentyButton.setDisable(userBean == null);
    }

    @FXML
    public void onButtonClicked(ActionEvent event) {
        Node eventSource = (Node) event.getSource() ;

        UserBean loggedUser = InternalBackController.getInternalBackControllerInstance().getLoggedUser();
        if (loggedUser == null) {

        }

        if (eventSource == generateFiveButton) {
            getNewCoupon(5.0);
        }
        else if (eventSource == generateTenButton) {
            getNewCoupon(10.0);
        }
        else if (eventSource == generateTwentyButton) {
            getNewCoupon(20.0);
        }
    }

    public void getNewCoupon(Double couponValue) {
        CouponBean couponBean = new CouponBean("", couponValue);
        try {
            manageCouponController.generateNewCoupon(couponBean) ;
        } catch (InvalidCouponException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Errore nella Creazione del Coupon") ;
            alert.showAndWait() ;
        }
        initialize(null, null);
    }


}

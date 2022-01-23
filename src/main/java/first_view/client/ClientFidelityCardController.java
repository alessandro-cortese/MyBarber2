package first_view.client;

import application_controller.ManageCouponController;
import engineering.bean.FidelityCardBean;
import engineering.bean.UserBean;
import engineering.bean.buy_product.CouponBean;
import engineering.exception.InvalidCouponException;
import engineering.exception.NotExistentUserException;
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
import java.time.LocalTime;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import static first_view.list_cell_factories.BuyProductListCellFactory.EURO_SYMBOL;

public class ClientFidelityCardController implements Initializable {


    @FXML private Button generateCouponButton ;
    @FXML private ListView<CouponBean> couponCostListView ;

    @FXML private ListView<CouponBean> couponListView ;
    @FXML private Label pointsSaleLabel ;
    @FXML private Label userNameLabel ;

    private final ManageCouponController manageCouponController ;

    public ClientFidelityCardController() {
        manageCouponController = new ManageCouponController() ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        couponListView.setCellFactory(param -> new CouponCellFactory());

        couponCostListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<CouponBean> call(ListView<CouponBean> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(CouponBean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            String couponType = item.getExternalCouponType();
                            setText(String.format("SCONTO: -%.2f\t\t%s\t\tCOSTO IN PUNTI: %d", item.getCouponDiscount(), (couponType.compareTo("subtraction") == 0) ? EURO_SYMBOL : "%", item.getCouponPointsPrice()));
                            setStyle("-fx-font-size: 16 ; -fx-alignment: center");
                        } else setText(null);
                    }
                };
            }
        });

        UserBean userBean = InternalBackController.getInternalBackControllerInstance().getLoggedUser() ;
        if (userBean != null) {
            userNameLabel.setText("eMail " + userBean.getUserEmail());
            FidelityCardBean fidelityCardBean = null;
            try {
                fidelityCardBean = manageCouponController.showFidelityCard(userBean);
                updateView(fidelityCardBean) ;
            } catch (NotExistentUserException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage()) ;
                alert.showAndWait() ;
            }

        }
        generateCouponButton.setDisable(userBean == null);
        List<CouponBean> couponCosts = manageCouponController.showCouponCosts() ;
        couponCostListView.setItems(FXCollections.observableList(couponCosts));
    }

    private void updateView(FidelityCardBean fidelityCardBean) {
        couponListView.setItems(FXCollections.observableList(fidelityCardBean.getCouponBeans()));
        pointsSaleLabel.setText("Totale Punti " + fidelityCardBean.getPointsSale() );
    }

    @FXML
    public void onButtonClicked(ActionEvent event) {
        Node eventSource = (Node) event.getSource() ;

        if (eventSource == generateCouponButton) {
            getNewCoupon();
        }

    }

    public void getNewCoupon() {
        try {
            CouponBean couponBean = couponCostListView.selectionModelProperty().get().getSelectedItem(); ;
            if (couponBean != null) {
                FidelityCardBean fidelityCardBean = manageCouponController.generateNewCoupon(couponBean);
                updateView(fidelityCardBean);
            }
        } catch (InvalidCouponException | NotExistentUserException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage()) ;
            alert.showAndWait() ;
        }
    }


}

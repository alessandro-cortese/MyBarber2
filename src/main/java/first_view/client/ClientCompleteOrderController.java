package first_view.client;

import applicationController.BuyProductController;
import engineering.bean.buyProduct.CouponBean;
import engineering.bean.buyProduct.OrderInfoBean;
import engineering.bean.buyProduct.OrderTotalBean;
import engineering.exception.InvalidCouponException;
import first_view.general.InternalBackController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.sql.Date;
import java.time.Instant;

import static first_view.listCellFactories.BuyProductListCellFactory.EURO_SYMBOL;

public class ClientCompleteOrderController {

    @FXML private Button addCouponButton ;
    @FXML private TextField addressField ;
    @FXML private ListView<CouponBean> couponListView ;
    @FXML private Label orderTotalAmountLabel ;
    @FXML private Label acquiredPointsLabel ;
    @FXML private Button payWithPaypalButton ;
    @FXML private TextField telephoneField ;
    @FXML private TextField couponCodeField ;
    @FXML private Button payWithGooglePayButton ;

    private BuyProductController buyProductController ;
    private OrderTotalBean orderTotalBean ;


    @FXML
    public void onButtonClicked(ActionEvent event) {
        Node sourceNode = (Node) event.getSource() ;
        if (sourceNode == addCouponButton) {
            insertCoupon() ;
        }
        else if (sourceNode == payWithPaypalButton || sourceNode == payWithGooglePayButton) {
            if (!addressField.getText().isEmpty() && !telephoneField.getText().isEmpty()) {
                String paymentType = (sourceNode == payWithPaypalButton) ? "paypal" : "google" ;
                buy(paymentType);
                InternalBackController.getInternalBackControllerInstance().backToHome(sourceNode);
            }
            else {
                Alert alertDialog = new Alert(Alert.AlertType.ERROR, "Necessario completare tutti i campi!!") ;
                alertDialog.showAndWait() ;
            }
        }
        updateInfo();
    }

    private void insertCoupon() {
        CouponBean couponBean = new CouponBean(couponCodeField.getText()) ;
        try {
            buyProductController.applyCoupon(couponBean);
        } catch (InvalidCouponException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage()) ;
            alert.showAndWait() ;
        }
    }


    public void setApplicationController(BuyProductController buyProductController) {
        this.buyProductController = buyProductController ;
        viewOrder();
    }

    public void viewOrder() {
        orderTotalBean = buyProductController.showOrder() ;
        updateInfo();
    }

    private void updateInfo() {
        orderTotalAmountLabel.setText("Totale Ordine: "+ EURO_SYMBOL + orderTotalBean.getTotal());
        acquiredPointsLabel.setText("Punti Raccolti: " + orderTotalBean.getPoints());
        couponListView.setItems(FXCollections.observableList(orderTotalBean.getCouponBeans()));
    }

    private void buy(String paymentType) {
        OrderInfoBean orderInfoBean = new OrderInfoBean(addressField.getText(), telephoneField.getText(), paymentType, Date.from(Instant.now())) ;
        buyProductController.completeOrder(orderInfoBean);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Il tuo ordine è stato completato correttamente") ;
        alert.showAndWait() ;
    }
}

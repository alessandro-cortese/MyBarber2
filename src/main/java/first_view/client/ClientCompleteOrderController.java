package first_view.client;

import application_controller.BuyProductController;
import engineering.bean.AccessInfoBean;
import engineering.bean.UserBean;
import engineering.bean.buy_product.CouponBean;
import engineering.bean.buy_product.OrderInfoBean;
import engineering.bean.buy_product.OrderTotalBean;
import engineering.exception.InvalidCouponException;
import engineering.exception.NegativePriceException;
import engineering.exception.NotExistentUserException;
import first_view.general.InternalBackController;
import first_view.pickers.CredentialsPicker;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Date;
import java.time.Instant;

import static first_view.list_cell_factories.BuyProductListCellFactory.EURO_SYMBOL;

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
    public void onButtonClicked(ActionEvent event) throws IOException {
        Node sourceNode = (Node) event.getSource() ;
        if (sourceNode == addCouponButton) {
            insertCoupon() ;
        }
        else if (sourceNode == payWithPaypalButton || sourceNode == payWithGooglePayButton) {

            try {
                if (InternalBackController.getInternalBackControllerInstance().getLoggedUser() == null) login();

                if (!addressField.getText().isEmpty() && !telephoneField.getText().isEmpty()) {
                    String paymentType = (sourceNode == payWithPaypalButton) ? "paypal" : "google";
                    buy(paymentType);
                    InternalBackController.getInternalBackControllerInstance().backToHome(sourceNode);
                } else {
                    Alert alertDialog = new Alert(Alert.AlertType.ERROR, "COMPLETARE TUTTI I CAMPI DELL'ORDINE!!");
                    alertDialog.showAndWait();
                }

            } catch (NotExistentUserException e) {
                //Se il login non è andato bene non posso andare avanti quindi faccio return ;
                return;
            }
        }

        updateInfo();
    }

    private void login() throws IOException, NotExistentUserException {
        CredentialsPicker credentialsPicker = new CredentialsPicker() ;
        AccessInfoBean accessInfo = credentialsPicker.getAccessInfo() ;
        if (accessInfo != null) {
            try {
                UserBean loggedUser = buyProductController.login(accessInfo) ;
                InternalBackController.getInternalBackControllerInstance().setLoggedUser(loggedUser);
            }
            catch (NotExistentUserException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage()) ;
                alert.showAndWait() ;
                throw e ;
            }
        }
    }

    private void insertCoupon() {
        String stringCouponCode = couponCodeField.getText() ;
        if (!stringCouponCode.matches("[0-9]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "FORMATO DEL COUPON NON VALIDO") ;
            alert.showAndWait() ;
            return ;
        }

        CouponBean couponBean = new CouponBean(stringCouponCode) ;
        try {
            buyProductController.applyCoupon(couponBean);
        }
        catch (InvalidCouponException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage()) ;
            alert.showAndWait() ;
        }
        catch (NegativePriceException negativePriceException) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Il coupon non è applicabile") ;
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

    private void buy(String paymentType) throws NotExistentUserException {
        OrderInfoBean orderInfoBean = new OrderInfoBean(addressField.getText(), telephoneField.getText(), paymentType, Date.from(Instant.now())) ;
        UserBean loggedUser = InternalBackController.getInternalBackControllerInstance().getLoggedUser();
        buyProductController.completeOrder(orderInfoBean, loggedUser);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Il tuo ordine è stato completato correttamente") ;
        alert.showAndWait() ;
    }
}

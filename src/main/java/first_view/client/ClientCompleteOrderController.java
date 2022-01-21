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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientCompleteOrderController implements Initializable {

    @FXML private Button addCouponButton ;
    @FXML private TextField addressField ;
    @FXML private ListView<String> couponListView ;
    @FXML private Label orderTotalAmountLabel ;
    @FXML private Label acquiredPointsLabel ;
    @FXML private Button payWithPaypalButton ;
    @FXML private TextField telephoneField ;
    @FXML private TextField couponCodeField ;
    @FXML private Button payWithGooglePayButton ;

    private BuyProductController buyProductController ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        couponListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) setText("Codice Coupon: " + item);
                        else setText(null) ;

                    }
                };
            }
        });
    }


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
    }

    private void login() throws IOException, NotExistentUserException {
        CredentialsPicker credentialsPicker = new CredentialsPicker() ;
        AccessInfoBean accessInfo = credentialsPicker.getAccessInfo() ;
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

    private void insertCoupon() {
        String stringCouponCode = couponCodeField.getText() ;
        if (!stringCouponCode.matches("[0-9]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "FORMATO DEL COUPON NON VALIDO") ;
            alert.showAndWait() ;
            return ;
        }

        try {
            CouponBean couponBean = new CouponBean(stringCouponCode) ;
            OrderTotalBean orderTotalBean = buyProductController.applyCoupon(couponBean);
            updateInfo(orderTotalBean);
        }
        catch (InvalidCouponException | NegativePriceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage()) ;
            alert.showAndWait() ;
        }
    }

    public void setApplicationController(BuyProductController buyProductController) {
        this.buyProductController = buyProductController ;
        viewOrder();
    }

    public void viewOrder() {
        OrderTotalBean orderTotalBean = buyProductController.showOrder() ;
        updateInfo(orderTotalBean);
    }

    private void updateInfo(OrderTotalBean orderTotalBean) {
        orderTotalAmountLabel.setText(String.format("Totale Ordine: %.2f", orderTotalBean.getOrderTotal()));
        acquiredPointsLabel.setText("Punti Raccolti: " + orderTotalBean.getOrderPoints());

        couponListView.setItems(FXCollections.observableList(orderTotalBean.getExternalCouponCodes()));
    }

    private void buy(String paymentType) throws NotExistentUserException {
        OrderInfoBean orderInfoBean = new OrderInfoBean(addressField.getText(), telephoneField.getText(), paymentType) ;
        buyProductController.completeOrder(orderInfoBean);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Il tuo ordine è stato completato correttamente") ;
        alert.showAndWait() ;
    }


}

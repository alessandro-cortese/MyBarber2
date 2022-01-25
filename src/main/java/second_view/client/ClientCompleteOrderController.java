package second_view.client;

import application_controller.BuyProductController;
import engineering.bean.UserBean;
import engineering.bean.buy_product.CouponBean;
import engineering.bean.buy_product.OrderInfoBean;
import engineering.bean.buy_product.OrderTotalBean;
import engineering.exception.InvalidCouponException;
import engineering.exception.NegativePriceException;
import engineering.exception.NotExistentUserException;
import first_view.general.InternalBackController;
import first_view.list_cell_factories.CouponCodeCellFactory;
import first_view.pickers.CredentialsPicker;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import second_view.general.ScreenChanger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static first_view.list_cell_factories.BuyProductListCellFactory.EURO_SYMBOL;

public class ClientCompleteOrderController implements Initializable {

    @FXML private ListView<String> couponListView ;
    @FXML private TextField commandLine ;
    @FXML private TextField addressTextField ;
    @FXML private TextField telephoneTextField ;
    @FXML private Label orderTotalLabel ;


    private BuyProductController buyProductController ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        couponListView.setCellFactory(param -> new CouponCodeCellFactory());
    }


    @FXML
    public void onCommand(ActionEvent event) throws IOException {
        String command = commandLine.getText() ;

        commandLine.setStyle(null);
        commandLine.setText("");

        if (command.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return ;
        }
        else if (command.matches("set address .+")) {
            String commandInput = command.replace("set address ", "") ;
            addressTextField.setText(commandInput);
            return ;
        }
        else if (command.matches("set telephone [0-9]{10}")) {
            String commandInput = command.replace("set telephone ", "") ;
            telephoneTextField.setText(commandInput);
            return ;
        }
        else if (command.matches("add coupon .+")) {
            String commandInput = command.replace("add coupon ", "") ;
            insertCoupon(commandInput) ;
            return ;
        }
        else if (command.compareTo("buy") == 0) {
            if (telephoneTextField.getText().isEmpty() || addressTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Completare tutti i Campi") ;
                alert.showAndWait() ;
            }
            else {
                try {
                    if (ScreenChanger.getInstance().getLoggedUser() == null) login() ;
                    buy();
                    ScreenChanger.getInstance().goToHome(event);
                }
                catch (NotExistentUserException ignored) {}
            }
            return ;
        }

        commandLine.setStyle("-fx-border-color: red") ;
    }

    private void login() throws IOException, NotExistentUserException {
        CredentialsPicker credentialsPicker = new CredentialsPicker() ;
        UserBean userBean = credentialsPicker.doLogin() ;
        try {
            buyProductController.loadCustomer(userBean);
            ScreenChanger.getInstance().setLoggedUser(userBean);
        } catch (NotExistentUserException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
            throw e;
        }
    }

    private void buy() {
        OrderInfoBean orderInfoBean = new OrderInfoBean(addressTextField.getText(), telephoneTextField.getText(), "paypal") ;
        buyProductController.completeOrder(orderInfoBean);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "ORDINE ESEGUITO CON SUCCESSO") ;
        alert.showAndWait() ;
    }

    private void insertCoupon(String commandInput) {
        try {
            CouponBean couponBean = new CouponBean(commandInput) ;
            OrderTotalBean orderTotalBean = buyProductController.applyCoupon(couponBean);
            updateView(orderTotalBean);
        }
        catch (InvalidCouponException | NegativePriceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage()) ;
            alert.showAndWait() ;
        }
    }


    public void setApplicationController(BuyProductController buyProductController) {
        this.buyProductController = buyProductController ;
        OrderTotalBean orderTotalBean = buyProductController.showOrder() ;
        updateView(orderTotalBean) ;
    }

    private void updateView(OrderTotalBean orderTotalBean) {
        couponListView.setItems(FXCollections.observableList(orderTotalBean.getExternalCouponCodes()));
        orderTotalLabel.setText(String.format("Totale Ordine: %s %.2f" ,EURO_SYMBOL, orderTotalBean.getOrderTotal())) ;
    }

}

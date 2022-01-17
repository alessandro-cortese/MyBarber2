package second_view.client;

import application_controller.BuyProductController;
import engineering.bean.UserBean;
import engineering.bean.buy_product.CouponBean;
import engineering.bean.buy_product.OrderInfoBean;
import engineering.bean.buy_product.OrderTotalBean;
import engineering.exception.InvalidCouponException;
import engineering.exception.NegativePriceException;
import engineering.exception.NotExistentUserException;
import first_view.pickers.CredentialsPicker;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;

import static first_view.list_cell_factories.BuyProductListCellFactory.EURO_SYMBOL;

public class ClientCompleteOrderController {

    @FXML private ListView<CouponBean> couponListView ;
    @FXML private TextField commandLine ;
    @FXML private TextField addressTextField ;
    @FXML private TextField telephoneTextField ;
    @FXML private Label orderTotalLabel ;


    private BuyProductController buyProductController ;
    private OrderTotalBean orderTotalBean ;


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
        else if (command.matches("add coupon [0-9]+")) {
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
                    buy();
                    ScreenChanger.getInstance().goToHome(event);
                }
                catch (NotExistentUserException ignored) {
                }
            }

            return ;
        }

        commandLine.setStyle("-fx-border-color: red") ;
    }

    private void buy() throws IOException, NotExistentUserException {
        try {
            UserBean userBean = ScreenChanger.getInstance().getLoggedUser();
            if (userBean == null) {
                userBean = buyProductController.login((new CredentialsPicker()).getAccessInfo()) ;
                ScreenChanger.getInstance().setLoggedUser(userBean);
            }
            OrderInfoBean orderInfoBean = new OrderInfoBean(addressTextField.getText(), telephoneTextField.getText(), "paypal") ;
            buyProductController.completeOrder(orderInfoBean);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "ORDINE ESEGUITO CON SUCCESSO") ;
            alert.showAndWait() ;


        } catch (NotExistentUserException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage()) ;
            alert.showAndWait() ;
            throw e ;
        }
    }

    private void insertCoupon(String commandInput) {
        CouponBean couponBean = new CouponBean(commandInput) ;
        try {
            buyProductController.applyCoupon(couponBean);
            updateView();
        }
        catch (InvalidCouponException | NegativePriceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage()) ;
            alert.showAndWait() ;
        }
    }


    public void setApplicationController(BuyProductController buyProductController) {
        this.buyProductController = buyProductController ;
        orderTotalBean = buyProductController.showOrder() ;
        updateView() ;
    }

    private void updateView() {
        couponListView.setItems(FXCollections.observableList(orderTotalBean.getCouponBeans()));
        orderTotalLabel.setText(String.format("Totale Ordine: %s %.2f" ,EURO_SYMBOL, orderTotalBean.getTotal())) ;
    }


}

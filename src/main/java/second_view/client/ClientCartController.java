package second_view.client;

import application_controller.BuyProductController;
import engineering.bean.buy_product.CartBean;
import engineering.bean.buy_product.CartRowBean;
import engineering.bean.buy_product.ProductBean;
import first_view.list_cell_factories.CartRowListCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import second_view.general.ScreenChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static first_view.list_cell_factories.BuyProductListCellFactory.EURO_SYMBOL;
import static first_view.list_cell_factories.BuyProductListCellFactory.SECOND_VIEW;

public class ClientCartController implements Initializable {

    @FXML private TextField commandLine ;
    @FXML private ListView<CartRowBean> cartListView ;
    @FXML private Label cartTotalLabel ;

    BuyProductController buyProductController ;
    CartBean cartBean ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cartListView.setCellFactory(param -> new CartRowListCellFactory(SECOND_VIEW));
    }

    public void onCommand(ActionEvent event) throws IOException {
        String command = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");

        if (command.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return;
        }
        else if (command.matches("increment [0-9]+")) {
            Integer inputIndex = Integer.parseInt(command.replace("increment ", "")) ;
            if (inputIndex < cartListView.getItems().size()) {
                addProduct(inputIndex);
                return;
            }
        }
        else if (command.matches("decrement [0-9]+")) {
            Integer inputIndex = Integer.parseInt(command.replace("decrement ", "")) ;
            if (inputIndex < cartListView.getItems().size()) {
                removeProduct(inputIndex);
                return;
            }
        }
        else if (command.matches("delete [0-9]+")) {
            Integer inputIndex = Integer.parseInt(command.replace("delete ", "")) ;
            if (inputIndex < cartListView.getItems().size()) {
                Integer productQuantity = cartListView.getItems().get(inputIndex).getQuantity() ;
                for (int i = 0 ; i < productQuantity ; i++) {
                    removeProduct(inputIndex);
                }
                return;
            }
        }
        else if (command.compareTo("complete") == 0) {
            ClientCompleteOrderController completeOrderController = (ClientCompleteOrderController) ScreenChanger.getInstance().changeScreen(event, ScreenChanger.CLIENT_COMPLETE_ORDER_SCREEN);
            completeOrderController.setApplicationController(buyProductController) ;
            return ;
        }

        commandLine.setStyle("-fx-border-color: red");
    }

    private void removeProduct(Integer inputIndex) {
        CartRowBean cartRowBean = cartListView.getItems().get(inputIndex) ;
        buyProductController.removeProductFromCart(new ProductBean(cartRowBean.getIsbn()));
        updateView();
    }

    private void addProduct(Integer index) {
        ObservableList<CartRowBean> cartRowBeans = cartListView.getItems() ;
        CartRowBean cartRowBean = cartRowBeans.get(index) ;
        ProductBean productBean = new ProductBean(cartRowBean.getIsbn()) ;
        buyProductController.insertProductToCart(productBean);
        updateView();
    }

    public void setAppController(BuyProductController buyProductController) {
        this.buyProductController = buyProductController ;
        updateView();
    }

    private void updateView() {
        cartBean = buyProductController.showCart() ;
        cartListView.setItems(FXCollections.observableList(cartBean.getCartRowBeanArrayList()));
        cartTotalLabel.setText("Totale: " + EURO_SYMBOL + cartBean.getTotal());
    }


}

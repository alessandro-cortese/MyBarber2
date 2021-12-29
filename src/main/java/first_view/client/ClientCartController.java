package first_view.client;

import applicationController.BuyProductController;
import engineering.bean.buyProduct.CartBean;
import engineering.bean.buyProduct.CartRowBean;
import engineering.bean.buyProduct.ProductBean;
import first_view.listCellFactories.CartRowListCellFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCartController implements Initializable {

    @FXML private ListView<CartRowBean> cartListView ;
    @FXML private Label totalAmount ;
    @FXML private Button plusButton ;
    @FXML private Button minusButton ;
    @FXML private Button deleteButton ;

    private BuyProductController buyProductController ;
    private CartBean cartBean ;

    private static final String LIST_ITEM_RES = "first_view/listitem/client_buy_product_list_item.fxml";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cartListView.setCellFactory(param -> new CartRowListCellFactory());

        cartListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CartRowBean>() {
            @Override
            public void changed(ObservableValue<? extends CartRowBean> observable, CartRowBean oldValue, CartRowBean newValue) {
                plusButton.setDisable(newValue == null);
                deleteButton.setDisable(newValue == null);
                minusButton.setDisable(newValue == null);
            }
        });

        plusButton.setDisable(true);
        deleteButton.setDisable(true);
        minusButton.setDisable(true);
    }

    public void setApplicationController(BuyProductController buyProductController) {
        this.buyProductController = buyProductController ;
    }

    public void viewCart() {
        cartBean = buyProductController.showCart();
        updateInfo() ;
    }

    private void updateInfo() {
        cartListView.setItems(FXCollections.observableList(cartBean.getCartRowBeanArrayList()));
        totalAmount.setText(Double.toString(cartBean.getTotal()));
    }

    public void onButtonClicked(ActionEvent event) {
        Node sourceNode = (Node) event.getSource() ;

        if (sourceNode == plusButton) {
            addProduct() ;
        }
        else if (sourceNode == minusButton) {
            removeProduct() ;
        }
    }

    private void removeProduct() {
        CartRowBean cartRow = cartListView.getSelectionModel().getSelectedItem() ;
        ProductBean productBean = new ProductBean(cartRow.getIsbn()) ;
        buyProductController.removeProductFromCart(productBean);
        updateInfo();
    }

    private void addProduct() {
        CartRowBean cartRow = cartListView.getSelectionModel().getSelectedItem() ;
        ProductBean productBean = new ProductBean(cartRow.getIsbn()) ;
        buyProductController.insertProductToCart(productBean);
        updateInfo();
    }
}

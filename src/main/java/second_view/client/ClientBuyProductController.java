package second_view.client;

import applicationController.BuyProductController;
import engineering.bean.buyProduct.ProductBean;
import engineering.bean.buyProduct.ProductSearchInfoBean;
import first_view.listCellFactories.BuyProductListCellFactory;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import second_view.general.ScreenChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientBuyProductController implements Initializable {


    @FXML private TextField commandLine ;
    @FXML private ListView<ProductBean> productListView ;

    private BuyProductController buyProductController ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buyProductController = new BuyProductController() ;

        productListView.setCellFactory(param -> new BuyProductListCellFactory(BuyProductListCellFactory.SECOND_VIEW));

        searchProduct("");
    }


    @FXML
    public void onCommand(ActionEvent event) throws IOException {
        String command = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");
        String searchString = "search" ;
        String gotoString = "goto" ;
        if (command.matches("search .*")) {
            String commandInput = command.replace("search " , "") ;
            searchProduct(commandInput);
            return ;
        }
        else if (command.matches("goto [0-9]+")) {
            String commandInput = command.replace(gotoString + " " , "") ;
            Integer productIndex = Integer.parseInt(commandInput) ;
            if (productIndex < productListView.getItems().size()) {
                ProductBean productBean = productListView.getItems().get(productIndex);
                ClientSeeProductController clientSeeProductController = (ClientSeeProductController) ScreenChanger.getInstance().changeScreen(event, ScreenChanger.CLIENT_SEE_PRODUCT_SCREEN);
                clientSeeProductController.setProduct(productBean);
                clientSeeProductController.setController(buyProductController);
                return;
            }
        }
        else if (command.compareTo("cart") == 0) {
            ClientCartController cartController = (ClientCartController) ScreenChanger.getInstance().changeScreen(event, ScreenChanger.CLIENT_CART_SCREEN);
            cartController.setAppController(buyProductController) ;
        }
        else if (command.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return ;
        }

        commandLine.setStyle("-fx-border-color: red");
    }

    private void goToProduct(Integer productIndex) {

    }

    private void searchProduct(String searchText) {
        ProductSearchInfoBean productSearchInfoBean = new ProductSearchInfoBean(searchText) ;
        ArrayList<ProductBean> productBeans = buyProductController.filterProductList(productSearchInfoBean) ;
        productListView.setItems(FXCollections.observableList(productBeans));
    }


}

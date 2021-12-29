package first_view.client;

import applicationController.BuyProductController;
import engineering.bean.buyProduct.ProductBean;
import engineering.bean.buyProduct.ProductSearchInfoBean;
import first_view.general.InternalBackController;
import first_view.listCellFactories.BuyProductListCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientBuyProductController implements Initializable {

    @FXML private ListView<ProductBean> buyProductListView ;
    @FXML private Button addToCartButton ;
    @FXML private Button searchButton ;
    @FXML private TextField searchField ;
    @FXML private Label productNameLabel ;
    @FXML private Label productPriceLabel ;
    @FXML private Label productDescriptionLabel ;
    @FXML private Button goToCartButton ;

    private BuyProductController buyProductController;
    private ArrayList<ProductBean> productBeans ;

    private static final String LIST_ITEM_RES = "first_view/listitem/client_buy_product_list_item.fxml";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buyProductController = new BuyProductController() ;

        buyProductListView.setCellFactory(param -> new BuyProductListCellFactory());

        addToCartButton.setDisable(true);

        buyProductListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            productNameLabel.setText((newValue == null) ? "" : newValue.getName());
            productPriceLabel.setText((newValue == null) ? "" : Double.toString(newValue.getPrice()));
            productDescriptionLabel.setText((newValue == null) ? "" : newValue.getDescription());

            addToCartButton.setDisable(newValue == null);
        });


        searchProduct("");
    }

    @FXML
    public void onButtonClicked(ActionEvent event) {
        Node sourceNode = (Node) event.getSource() ;
        if (sourceNode == addToCartButton) {
            ProductBean selectedProduct = buyProductListView.getSelectionModel().getSelectedItem() ;
            buyProductController.insertProductToCart(selectedProduct);
        }
        else if (sourceNode == searchButton) {
            searchProduct(searchField.getText());
        }
        else if (sourceNode == goToCartButton) {

            try {
                InternalBackController.getInternalBackControllerInstance().onNextScreen(sourceNode);
                FXMLLoader newCenterNodeLoader = new FXMLLoader(getClass().getClassLoader().getResource("first_view/client/client_cart.fxml"));


                Scene myScene = sourceNode.getScene();
                BorderPane borderPane = (BorderPane) myScene.getRoot();
                borderPane.setCenter(newCenterNodeLoader.load());
                ClientCartController cartController = ((ClientCartController) newCenterNodeLoader.getController()) ;
                cartController.setApplicationController(buyProductController);
                cartController.viewCart() ;

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void searchProduct(String productName) {
        ProductSearchInfoBean searchInfoBean = new ProductSearchInfoBean(productName) ;
        productBeans = buyProductController.filterProductList(searchInfoBean) ;
        buyProductListView.setItems(FXCollections.observableList(productBeans));
    }

    private void addProduct() {}

}

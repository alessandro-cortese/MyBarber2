package first_view.listCellFactories;

import engineering.bean.buyProduct.CartRowBean;
import engineering.bean.buyProduct.ProductBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

import static first_view.listCellFactories.BuyProductListCellFactory.EURO_SYMBOL;

public class CartRowListCellFactory extends ListCell<CartRowBean> {

    private Parent parentNode = null ;

    private static final String LIST_ITEM_RES = "first_view/listitem/client_cart_row.fxml" ;
    private static final String NAME_LABEL_ID = "productNameLabel" ;
    private static final String PRICE_LABEL_ID = "productPriceLabel" ;
    private static final String QUANTITY_LABEL_ID = "productRowQuantity" ;

    @Override
    protected void updateItem(CartRowBean item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null) {
            try {
                if (parentNode == null) parentNode = new FXMLLoader(getClass().getClassLoader().getResource(LIST_ITEM_RES)).load() ;

                Label productNameLabel = (Label) parentNode.lookup("#" + NAME_LABEL_ID) ;
                Label productPriceLabel = (Label) parentNode.lookup("#" + PRICE_LABEL_ID) ;
                Label productQuantityLabel = (Label) parentNode.lookup("#" + QUANTITY_LABEL_ID) ;
                productNameLabel.setText(item.getName());
                productPriceLabel.setText(EURO_SYMBOL + " " + item.getPrice());
                productQuantityLabel.setText(Integer.toString(item.getQuantity()));

                setGraphic(parentNode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

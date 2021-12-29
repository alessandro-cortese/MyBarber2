package first_view.listCellFactories;

import engineering.bean.buyProduct.ProductBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class BuyProductListCellFactory extends ListCell<ProductBean> {

    private Parent parentNode = null ;

    private static final String LIST_ITEM_RES = "first_view/listitem/client_buy_product_list_item.fxml" ;
    private static final String NAME_LABEL_ID = "productNameLabel" ;
    private static final String PRICE_LABEL_ID = "productPriceLabel" ;
    public static final String EURO_SYMBOL = "\u20ac" ;

    @Override
    protected void updateItem(ProductBean item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null) {
            try {
                if (parentNode == null) parentNode = new FXMLLoader(getClass().getClassLoader().getResource(LIST_ITEM_RES)).load() ;

                Label productNameLabel = (Label) parentNode.lookup("#" + NAME_LABEL_ID) ;
                Label productPriceLabel = (Label) parentNode.lookup("#" + PRICE_LABEL_ID) ;
                productNameLabel.setText(item.getName());
                productPriceLabel.setText(EURO_SYMBOL + " " + item.getPrice());

                setGraphic(parentNode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

package second_view.listCellFactories;

import engineering.bean.buyProduct.ProductBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

import static first_view.listCellFactories.BuyProductListCellFactory.EURO_SYMBOL;

public class BuyProductListCellFactory extends ListCell<ProductBean> {

    private Parent parentNode = null ;

    private static final String LIST_ITEM_RES = "second_view/list_item/product_buy_list_item.fxml" ;
    private static final String LIST_ITEM_INDEX = "productIndexLabel" ;
    private static final String NAME_LABEL_ID = "productNameLabel" ;
    private static final String PRICE_LABEL_ID = "productPriceLabel" ;


    @Override
    protected void updateItem(ProductBean item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null) {
            try {
                if (parentNode == null) parentNode = new FXMLLoader(getClass().getClassLoader().getResource(LIST_ITEM_RES)).load() ;

                Label productNameLabel = (Label) parentNode.lookup("#" + NAME_LABEL_ID) ;
                Label productPriceLabel = (Label) parentNode.lookup("#" + PRICE_LABEL_ID) ;
                Label productIndexLabel = (Label) parentNode.lookup("#" + LIST_ITEM_INDEX) ;
                productNameLabel.setText(item.getName());
                productIndexLabel.setText(Integer.toString(this.getIndex()));
                productPriceLabel.setText(EURO_SYMBOL + " " + item.getPrice());

                setGraphic(parentNode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

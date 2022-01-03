package first_view.listCellFactories;

import applicationController.BuyProductController;
import engineering.bean.buyProduct.ProductBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class BuyProductListCellFactory extends ListCell<ProductBean> {

    public static final Integer FIRST_VIEW = 1 ;
    public static final Integer SECOND_VIEW = 2 ;

    private Parent parentNode = null ;

    private static final String LIST_ITEM_RES = "first_view/listitem/client_buy_product_list_item.fxml" ;
    private static final String NAME_LABEL_ID = "productNameLabel" ;
    private static final String PRICE_LABEL_ID = "productPriceLabel" ;
    private static final String INDEX_LABEL_ID = "productIndexLabel" ;
    public static final String EURO_SYMBOL = "\u20ac" ;

    private final Integer caller ;

    public BuyProductListCellFactory() {
        this(FIRST_VIEW) ;
    }

    public BuyProductListCellFactory(Integer caller) {
        this.caller = caller ;
    }



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

                if (caller == SECOND_VIEW) {
                    Label productIndexLabel = (Label) parentNode.lookup("#" + INDEX_LABEL_ID) ;
                    productIndexLabel.setText("Index " + this.getIndex());
                }
                setGraphic(parentNode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

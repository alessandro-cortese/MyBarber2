package first_view.list_cell_factories;

import engineering.bean.buy_product.ProductBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class BuyProductListCellFactory extends ListCell<ProductBean> {

    public static final Integer FIRST_VIEW = 1 ;
    public static final Integer SECOND_VIEW = 2 ;

    private Parent parentNode = null ;

    private static final String LIST_ITEM_RES = "first_view/list_item/client_buy_product_list_item.fxml";
    private static final String NAME_LABEL_ID = "productNameLabel" ;
    private static final String PRICE_LABEL_ID = "productPriceLabel" ;
    private static final String INDEX_LABEL_ID = "productIndexLabel" ;
    private static final String IMAGE_VIEW_ID = "productImageView" ;
    private static final String PROGRESS_BAR_ID = "imageProgressBar" ;
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
                ImageView productImageView = (ImageView) parentNode.lookup("#" + IMAGE_VIEW_ID) ;
                //Image rowImage = new Image("https://drive.googe.com/thumbnail?id=13E18W_hwUx1EYQo2FCYMAiA9qXRh8rPS",true) ;
                //Image rowImage = new Image("https://drive.googe.com/thumbnail?id=13E18W_hwUx1EYQo2FCYMAiA9qXRh8rPS", productImageView.getFitWidth(),productImageView.getFitHeight(),true,true, true) ;


                productNameLabel.setText(item.getName());
                productPriceLabel.setText(EURO_SYMBOL + " " + item.getPrice());
                //productImageView.setImage(rowImage);

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

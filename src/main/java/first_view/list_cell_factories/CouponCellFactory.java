package first_view.list_cell_factories;

import engineering.bean.buy_product.CartRowBean;
import engineering.bean.buy_product.CouponBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class CouponCellFactory extends ListCell<CouponBean> {

    private static final String LIST_ITEM_RES = "first_view/list_item/client_coupon_item.fxml" ;
    private static final String COUPON_CODE_LABEL_ID = "couponCodeLabel" ;
    private static final String COUPON_DISCOUNT_LABEL_ID = "couponDiscountLabel" ;

    private Parent parentNode = null ;


    @Override
    protected void updateItem(CouponBean item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null) {
            try {
                if (parentNode == null) parentNode = new FXMLLoader(getClass().getClassLoader().getResource(LIST_ITEM_RES)).load() ;

                Label couponCodeLabel = (Label) parentNode.lookup("#" + COUPON_CODE_LABEL_ID) ;
                Label couponDiscountLabel = (Label) parentNode.lookup("#" + COUPON_DISCOUNT_LABEL_ID) ;
                couponCodeLabel.setText("Codice: " + item.getCouponCode());
                couponDiscountLabel.setText("Sconto: " + Double.toString(item.getCouponDiscount()));

                setGraphic(parentNode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

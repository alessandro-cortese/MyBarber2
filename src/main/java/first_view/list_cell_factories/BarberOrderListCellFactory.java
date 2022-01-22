package first_view.list_cell_factories;

import engineering.bean.buy_product.VendorOrderBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;
import java.util.Map;

import static first_view.list_cell_factories.BuyProductListCellFactory.SECOND_VIEW;

public class BarberOrderListCellFactory extends ListCell<VendorOrderBean> {

    private static final String DATE_LABEL_ID = "dateLabel" ;
    private static final String ORDER_LABEL_ID = "orderNumberLabel" ;
    private static final String ITEM_INDEX_LABEL_ID = "itemIndexLabel" ;

    private static final String LIST_ITEM_RES = "first_view/list_item/barber_order_item.fxml" ;

    private Parent parentNode = null ;

    private Integer caller ;

    public BarberOrderListCellFactory(Integer caller) {
        this.caller = caller ;
    }


    @Override
    protected void updateItem(VendorOrderBean item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null) {
            try {
                if (parentNode == null) parentNode = new FXMLLoader(getClass().getClassLoader().getResource(LIST_ITEM_RES)).load() ;

                Label dateLabel = (Label) parentNode.lookup("#" + DATE_LABEL_ID) ;
                Label orderLabel = (Label) parentNode.lookup("#" + ORDER_LABEL_ID) ;
                Label itemIndexLabel = (Label) parentNode.lookup("#" + ITEM_INDEX_LABEL_ID) ;

                Map<String, Integer> dateMap = item.getExternalDate() ;
                String stringDate = String.format("%d - %d - %d", dateMap.get(VendorOrderBean.VENDOR_ORDER_YEAR_KEY), dateMap.get(VendorOrderBean.VENDOR_ORDER_MONTH_KEY), dateMap.get(VendorOrderBean.VENDOR_ORDER_DAY_KEY)) ;

                dateLabel.setText(stringDate);
                orderLabel.setText(item.getExternalOrderCode());
                if (caller == SECOND_VIEW) {
                    itemIndexLabel.setText("Index: " + this.getIndex());
                }
                setGraphic(parentNode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            setGraphic(null);
        }

    }
}

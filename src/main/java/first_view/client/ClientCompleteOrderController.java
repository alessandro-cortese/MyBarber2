package first_view.client;

import applicationController.BuyProductController;
import engineering.bean.buyProduct.CouponBean;
import engineering.bean.buyProduct.OrderTotalBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import static first_view.listCellFactories.BuyProductListCellFactory.EURO_SYMBOL;

public class ClientCompleteOrderController {

    @FXML private Button addCouponButton ;
    @FXML private TextField addressField ;
    @FXML private ListView<CouponBean> couponListView ;
    @FXML private Label orderTotalAmountLabel ;
    @FXML private Button payWithPaypalButton ;
    @FXML private TextField telephoneField ;
    @FXML private TextField couponCodeField ;

    private BuyProductController buyProductController ;
    private OrderTotalBean orderTotalBean ;


    @FXML
    public void onButtonClicked(ActionEvent event) {
        Node sourceNode = (Node) event.getSource() ;
        if (sourceNode == addCouponButton) {
            insertCoupon() ;
        }
        updateInfo();
    }

    private void insertCoupon() {
        CouponBean couponBean = new CouponBean(couponCodeField.getText()) ;
        buyProductController.applyCoupon(couponBean);
    }


    public void setApplicationController(BuyProductController buyProductController) {
        this.buyProductController = buyProductController ;
        viewOrder();
    }

    public void viewOrder() {
        orderTotalBean = buyProductController.showOrder() ;
        updateInfo();
    }

    private void updateInfo() {
        orderTotalAmountLabel.setText("Totale Ordine: "+ EURO_SYMBOL + orderTotalBean.getTotal());
    }
}

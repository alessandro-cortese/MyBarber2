package boundary.buyProduct;

import engineering.bean.buyProduct.OrderTotalBean;

public class BuyProductPaypalBoundary {

    public void pay(OrderTotalBean orderTotalBean) {
        System.out.println(orderTotalBean.getTotal());
    }
}

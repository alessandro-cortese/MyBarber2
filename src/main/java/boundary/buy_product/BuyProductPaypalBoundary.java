package boundary.buy_product;

import engineering.bean.buy_product.OrderTotalBean;

public class BuyProductPaypalBoundary {

    public void pay(OrderTotalBean orderTotalBean) {
        System.out.println(orderTotalBean.getTotal());
    }
}

package engineering.bean.buyProduct;

import java.util.ArrayList;

public class CartBean {

    private Double total ;
    private ArrayList<CartRowBean> cartRowBeanArrayList ;

    public CartBean(Double total, ArrayList<CartRowBean> cartRowBeanArrayList) {
        setTotal(total);
        setCartRowBeanArrayList(cartRowBeanArrayList);
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ArrayList<CartRowBean> getCartRowBeanArrayList() {
        return cartRowBeanArrayList;
    }

    public void setCartRowBeanArrayList(ArrayList<CartRowBean> cartRowBeanArrayList) {
        this.cartRowBeanArrayList = cartRowBeanArrayList;
    }
}

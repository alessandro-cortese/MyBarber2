package engineering.bean.buy_product;

import engineering.pattern.observer.Observer;
import model.buy_product.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static model.buy_product.Cart.*;

public class CartBean {

    private Double total ;
    private List<CartRowBean> cartRowBeanArrayList ;

    public CartBean() {
        this(0.0, new ArrayList<>()) ;
    }


    public CartBean(Double total, List<CartRowBean> cartRowBeans) {
        setTotal(total);
        setCartRowBeanArrayList(cartRowBeans) ;

    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<CartRowBean> getCartRowBeanArrayList() {
        return cartRowBeanArrayList;
    }

    public void setCartRowBeanArrayList(List<CartRowBean> cartRowBeanArrayList) {
        this.cartRowBeanArrayList = cartRowBeanArrayList;
    }

    public CartRowBean createRowBean(Map<String, String> rowInfo) {
        return new CartRowBean(Integer.parseInt(rowInfo.get(QUANTITY_KEY)),
                Integer.parseInt(rowInfo.get(ISBN_KEY)),
                rowInfo.get(NAME_KEY),
                Double.parseDouble(rowInfo.get(PRICE_KEY)));
    }
}

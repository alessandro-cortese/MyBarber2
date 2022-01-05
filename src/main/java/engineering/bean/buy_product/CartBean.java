package engineering.bean.buy_product;

import engineering.pattern.observer.Observer;
import model.buy_product.Cart;

import java.util.ArrayList;
import java.util.Map;

import static model.buy_product.Cart.*;

public class CartBean implements Observer {

    private Double total ;
    private ArrayList<CartRowBean> cartRowBeanArrayList ;
    private Cart observedCart ;

    public CartBean() {}


    public CartBean(Cart observedCart) {
        setTotal(0.0);
        setCartRowBeanArrayList(new ArrayList<>()) ;
        setObservedCart(observedCart);
        observedCart.attach(this);
        update();
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

    @Override
    public void update() {
        setTotal(observedCart.getTotal());
        ArrayList<Map<String,String>> rowsInfo = observedCart.getItemsInfo() ;
        cartRowBeanArrayList.clear();
        for (Map<String,String> rowInfo : rowsInfo) {
            cartRowBeanArrayList.add(createRowBean(rowInfo)) ;
        }
    }

    public CartRowBean createRowBean(Map<String, String> rowInfo) {
        return new CartRowBean(Integer.parseInt(rowInfo.get(QUANTITY_KEY)),
                Integer.parseInt(rowInfo.get(ISBN_KEY)),
                rowInfo.get(NAME_KEY),
                Double.parseDouble(rowInfo.get(PRICE_KEY)));
    }

    public void setObservedCart(Cart observedCart) {
        this.observedCart = observedCart;
    }
}

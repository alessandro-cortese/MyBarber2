package engineering.bean.buy_product;

import model.buy_product.Cart;
import model.buy_product.CartRow;

import java.util.ArrayList;
import java.util.List;

public class CartBeanBuilder {

    public static CartBean createCartBean(Cart cart) {
        List<CartRow> cartRows = cart.getCartRowArrayList() ;
        CartBean cartBean = new CartBean() ;
        cartBean.setTotal(cart.getTotal());
        List<CartRowBean> cartRowBeans = new ArrayList<>() ;
        for (CartRow cartRow : cartRows) {
            cartRowBeans.add(new CartRowBean(cartRow.getQuantity(), cartRow.getProductIsbn(), cartRow.getProductName(), cartRow.getProductPrice())) ;
        }
        cartBean.setCartRowBeanArrayList(cartRowBeans);
        return cartBean;
    }
}

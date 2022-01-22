package model.buy_product;

import engineering.pattern.decorator.Priceable;
import engineering.pattern.observer.Subject;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart extends Subject implements Priceable, Serializable {

    public static final String QUANTITY_KEY = "quantity" ;
    public static final String ISBN_KEY = "isbn" ;
    public static final String NAME_KEY = "name" ;
    public static final String PRICE_KEY = "price" ;
    public static final String VENDOR_KEY = "vendor" ;

    private final ArrayList<CartRow> cartRowArrayList ;

    public Cart() {
        super();
        cartRowArrayList = new ArrayList<>() ;
    }

    public void insertProduct(Product newProduct) {
        CartRow productRow = verifyPresent(newProduct) ;
        if (productRow == null) {
            cartRowArrayList.add(new CartRow(newProduct, 1)) ;
        }
        else {
            productRow.setQuantity(productRow.getQuantity() + 1);
        }
        super.notifyObservers();
    }

    public void removeProduct(Product rmvProduct) {
        CartRow productRow = verifyPresent(rmvProduct) ;
        if (productRow != null) {
            productRow.setQuantity(productRow.getQuantity() - 1);
            if (productRow.getQuantity() == 0) {
                cartRowArrayList.remove(productRow) ;
            }
        }
        super.notifyObservers();
    }


    @Nullable
    private CartRow verifyPresent(Product verifyProduct) {
        for (CartRow row : cartRowArrayList) {
            if (row.getProduct().equals(verifyProduct)) {
                return row ;
            }
        }
        return null ;
    }

    public ArrayList<String> getVendorsInfo() {
        ArrayList<String> vendorsInfo = new ArrayList<>() ;
        for (CartRow cartRow : cartRowArrayList) {
            if (!vendorsInfo.contains(cartRow.getProductVendor())) {
                vendorsInfo.add(cartRow.getProductVendor()) ;
            }
        }
        return vendorsInfo ;
    }


    public List<CartRow> getCartRowArrayList() {
        return this.cartRowArrayList ;
    }

    @Override
    public Double getPrice() {
        Double total = 0.0 ;
        for (CartRow row : cartRowArrayList) {
            total = total + row.getSubTotal() ;
        }
        return total ;
    }
}

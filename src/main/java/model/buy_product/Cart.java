package model.buy_product;

import engineering.pattern.decorator.Priceable;
import engineering.pattern.observer.Subject;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Map;

public class Cart extends Subject implements Priceable {

    public static final String QUANTITY_KEY = "quantity" ;
    public static final String ISBN_KEY = "isbn" ;
    public static final String NAME_KEY = "name" ;
    public static final String PRICE_KEY = "price" ;
    public static final String VENDOR_KEY = "vendor" ;

    private ArrayList<CartRow> cartRowArrayList ;

    public Cart() {
        super();
        cartRowArrayList = new ArrayList<>() ;
    }

    public void  insertProduct(Product newProduct) {
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

    public Double getTotal() {
        Double total = 0.0 ;
        for (CartRow row : cartRowArrayList) {
            total = total + row.getSubTotal() ;
        }
        return total ;
    }

    public ArrayList<Map<String, String>> getItemsInfo() {
        ArrayList<Map<String,String>> mapArrayList = new ArrayList<>() ;
        for (CartRow cartRow : cartRowArrayList) {
            Map<String,String> rowInfo = createRowInfo(cartRow) ;
            mapArrayList.add(rowInfo) ;
        }
        return mapArrayList ;
    }

    private Map<String, String> createRowInfo(CartRow cartRow) {
        return Map.of(QUANTITY_KEY, String.valueOf(cartRow.getQuantity()),
                ISBN_KEY, String.valueOf(cartRow.getProductIsbn()),
                NAME_KEY, String.valueOf(cartRow.getProductName()),
                PRICE_KEY, String.valueOf(cartRow.getProductPrice()) ,
                VENDOR_KEY, cartRow.getProductVendor()) ;
    }

    @Nullable
    private CartRow verifyPresent(Product verifyProduct) {
        for (CartRow row : cartRowArrayList) {
            if (row.getProduct() == verifyProduct) {
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

    public ArrayList<Map<String,String>> getRowsInfoByVendor(String vendor) {
        ArrayList<Map<String,String>> rowsInfo = new ArrayList<>() ;
        for (CartRow cartRow : cartRowArrayList) {
            if (cartRow.getProductVendor().compareTo(vendor) == 0) {
                rowsInfo.add(createRowInfo(cartRow)) ;
            }
        }
        return rowsInfo ;
    }

    @Override
    public Double getPrice() {
        return getTotal();
    }
}

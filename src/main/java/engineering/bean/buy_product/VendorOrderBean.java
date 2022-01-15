package engineering.bean.buy_product;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class VendorOrderBean {

    public static final String VENDOR_ORDER_YEAR_KEY = "YEAR" ;
    public static final String VENDOR_ORDER_MONTH_KEY = "MONTH" ;
    public static final String VENDOR_ORDER_DAY_KEY = "DAY" ;

    private String vendor ;
    private String address ;
    private String telephone ;
    private Map<String, Integer> date ;

    private List<CartRowBean> cartRowBeans ;

    public VendorOrderBean(String vendor, List<CartRowBean> cartRowBeans, String address, String telephone, Map<String, Integer> dateMap) {
        setVendor(vendor);
        setCartRowBeans(cartRowBeans);
        setAddress(address);
        setVendor(vendor);
        setDate(dateMap);
        setTelephone(telephone);
    }


    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getDay() {
        return date.get(VENDOR_ORDER_DAY_KEY) ;
    }

    public Integer getMoth() {
        return date.get(VENDOR_ORDER_MONTH_KEY) ;
    }

    public Integer getYear() {
        return date.get(VENDOR_ORDER_YEAR_KEY) ;
    }

    public void setDate(Map<String, Integer> date) {
        this.date = date;
    }

    public List<CartRowBean> getCartRowBeans() {
        return cartRowBeans;
    }

    public void setCartRowBeans(List<CartRowBean> cartRowBeans) {
        this.cartRowBeans = cartRowBeans;
    }
}

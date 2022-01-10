package engineering.bean.buy_product;

import java.util.Date;
import java.util.List;

public class VendorOrderBean {

    private String vendor ;
    private String address ;
    private String telephone ;
    private Date date ;

    private List<CartRowBean> cartRowBeans ;

    public VendorOrderBean(String vendor, List<CartRowBean> cartRowBeans, String address, String telephone, Date date) {
        setVendor(vendor);
        setCartRowBeans(cartRowBeans);
        setAddress(address);
        setVendor(vendor);
        setDate(date);
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CartRowBean> getCartRowBeans() {
        return cartRowBeans;
    }

    public void setCartRowBeans(List<CartRowBean> cartRowBeans) {
        this.cartRowBeans = cartRowBeans;
    }
}

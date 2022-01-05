package engineering.bean.buy_product;

import java.util.ArrayList;
import java.util.Date;

public class VendorOrderBean {

    private String vendor ;
    private String address ;
    private String telephone ;
    private Date date ;

    private ArrayList<CartRowBean> cartRowBeans ;

    public VendorOrderBean(String vendor, ArrayList<CartRowBean> cartRowBeans, String address, String telephone, Date date) {
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

    public ArrayList<CartRowBean> getCartRowBeans() {
        return cartRowBeans;
    }

    public void setCartRowBeans(ArrayList<CartRowBean> cartRowBeans) {
        this.cartRowBeans = cartRowBeans;
    }
}

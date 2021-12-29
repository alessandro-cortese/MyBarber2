package engineering.bean.buyProduct;

import model.buyProduct.Order;

import java.util.Date;

public class OrderInfoBean {

    private String address ;
    private String telephone ;
    private String paymentOption ;
    private Date date ;

    public OrderInfoBean(String address, String telephone, String paymentOption, Date date) {
        setAddress(address);
        setTelephone(telephone);
        setPaymentOption(paymentOption);
        setDate(date);
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

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

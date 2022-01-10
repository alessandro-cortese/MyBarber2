package engineering.bean.buy_product;

import java.util.Date;

public class OrderInfoBean {

    private String addressInfo;
    private String telephoneInfo;
    private String paymentOptionInfo;
    private Date dateInfo;

    public OrderInfoBean(String address, String telephone, String paymentOption, Date date) {
        setAddressInfo(address);
        setTelephoneInfo(telephone);
        setPaymentOptionInfo(paymentOption);
        setDateInfo(date);
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String getTelephoneInfo() {
        return telephoneInfo;
    }

    public void setTelephoneInfo(String telephoneInfo) {
        this.telephoneInfo = telephoneInfo;
    }

    public String getPaymentOptionInfo() {
        return paymentOptionInfo;
    }

    public void setPaymentOptionInfo(String paymentOptionInfo) {
        this.paymentOptionInfo = paymentOptionInfo;
    }

    public Date getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(Date dateInfo) {
        this.dateInfo = dateInfo;
    }
}

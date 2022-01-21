package engineering.bean.buy_product;

public class OrderInfoBean {

    private String addressInfo;
    private String telephoneInfo;
    private String paymentOptionInfo;

    public OrderInfoBean(String address, String telephone, String paymentOption) {
        setAddressInfo(address);
        setTelephoneInfo(telephone);
        setPaymentOptionInfo(paymentOption);
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
}

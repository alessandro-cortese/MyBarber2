package model.buy_product;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Order {

    private Integer orderCode ;

    private String address ;
    private String telephone ;
    private String paymentOption ;
    private String orderOwner ;
    private LocalDate date ;
    private Double finalPrice ;

    private List<CartRow> cartRows ;


    public Order() {}

    public Order(Integer orderCode, String address, String telephone, String orderOwner, LocalDate date) {
        setOrderCode(orderCode);
        setAddress(address);
        setAddress(telephone);
        setOrderOwner(orderOwner);
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public Integer getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getOrderOwner() {
        return orderOwner;
    }

    public void setOrderOwner(String orderOwner) {
        this.orderOwner = orderOwner;
    }

    public List<CartRow> getCartRows() {
        return cartRows;
    }

    public void setCartRows(List<CartRow> cartRows) {
        this.cartRows = cartRows;
    }
}

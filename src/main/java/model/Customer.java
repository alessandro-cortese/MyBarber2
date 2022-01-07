package model;

public class Customer extends User{

    private String creditCard;
    private String phone;


    public Customer(String email, String pass, String name, String surname, String creditCard, String phone){
        super(email, pass, name, surname);
        setCreditCard(creditCard);
        setPhone(phone);
    }

    public Customer(String email, String pass, String name, String surname) {
        super(email, pass, name, surname);
    }

    public String getPhone() {

        return phone;

    }

    public void setPhone(String phone) {

        this.phone = phone;

    }

    public String getCreditCard(){

        return creditCard;

    }

    public void setCreditCard(String creditCard){

        this.creditCard = creditCard;

    }

}

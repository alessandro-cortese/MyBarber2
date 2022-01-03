package model;

public class Customer extends User{

    private String name;
    private String surname;
    private String creditCard;
    private String phone;

    public Customer(String email, String pass, String name, String surname, String creditCard, String phone){
        super(email, pass);
        setName(name);
        setSurname(surname);
        setCreditCard(creditCard);
        setPhone(phone);
    }

    public Customer(String email, String pass, String name, String surname) {
        super(email, pass) ;
        setName(name);
        setSurname(surname);
    }

    public String getName(){
        return name;
    }
    public  void setName(String name){
        this.name = name;
    }
    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname=surname;
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

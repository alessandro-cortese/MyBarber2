package logic;

public class Customer extends User{

    private String name;
    private String surname;
    private long creditCard;

    public Customer(String email, String pass, String name, String surname, long creditCard){
        super(email, pass);
        this.name = name;
        this.surname = surname;
        this.creditCard = creditCard;
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

    public long getCreditCard(){
        return creditCard;
    }
    public void setCreditCard(long creditCard){
        this.creditCard = creditCard;
    }

}

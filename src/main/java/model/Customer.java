package model;

public class Customer extends User{

    private String phone;
    private Integer cardPoints ;


    public Customer(String email, String pass, String name, String surname, String phone){

        super(email, pass, name, surname);
        setPhone(phone);

    }

    public Customer(String email, String pass, String name, String surname, String phone, Integer cardPoints){
        super(email, pass, name, surname);
        setPhone(phone);
        setCardPoints(cardPoints);

    }

    public Customer(String email, String pass, String name, String surname, Integer cardPoints) {

        super(email, pass, name, surname);
        setCardPoints(cardPoints);

    }

    public String getPhone() {

        return phone;

    }

    public void setPhone(String phone) {

        this.phone = phone;

    }

    public Integer getCardPoints() {

        return cardPoints;

    }

    public void setCardPoints(Integer cardPoints) {

        this.cardPoints = cardPoints;

    }


}

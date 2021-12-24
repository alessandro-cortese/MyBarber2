package model;

public abstract class User {
    private  String email;
    private  String pass;
    private int pointsCard;

    protected User(String email, String pass){
        this.email = email;
        this.pass = pass;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPass(){
        return pass;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    public int getPointsCard(){
        return pointsCard;
    }
    public void setPointsCard(int pointsCard){
        this.pointsCard = pointsCard;
    }
}

package model;

public class Saloon {
    private String name;
    private String city;
    private String address;
    private String phone;

    public Saloon(String name, String city, String address, String phone){
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.city=city;

    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getAddress(){
        return address;
    }
    public  void  setAddress(String address){
        this.address = address;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}

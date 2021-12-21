package logic;

import java.util.Date;

public class Booking {
    private Date dateBooking = new Date();
    private String saloon;
    private String city;
    private String address;

    public Date getDateBooking() {
        return dateBooking;
    }
    public  void setDateBooking(Date dateBooking){
        this.dateBooking=dateBooking;
    }
    public String getSaloon(){
        return saloon;
    }
    public  void setSaloon(String saloon){
        this.saloon=saloon;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city=city;
    }
    public String getAddress(){
        return address;
    }
    public  void setAddress(String address){
        this.address = address;
    }
}

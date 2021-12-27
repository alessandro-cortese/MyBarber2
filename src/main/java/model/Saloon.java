package model;

public class Saloon {
    private String name;
    private String city;
    private String address;
    private String phone;
    private int slotTime;
    private int seatNumber;

    public Saloon(String name, String city, String address, String phone,int slotTime, int seatNumber){
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.city=city;
        this.seatNumber = seatNumber;
        this.slotTime = slotTime;

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
    public int getSlotTime(){
        return slotTime;
    }
    public void setSlotTime(int slotTime) {
        this.slotTime = slotTime;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}

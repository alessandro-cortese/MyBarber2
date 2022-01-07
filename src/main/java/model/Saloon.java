package model;
import java.sql.Time;
import java.time.LocalTime;

public class Saloon {

    private String name;
    private String city;
    private String address;
    private String phone;
    private LocalTime[][] timeSchedule;
    private Time slotTime;
    private int seatNumber;

    public Saloon(String city, String name, String address, String phone,Time slotTime, int seatNumber){

        this.extractedTimeSchedule();
        this.name = name;
        this.address = address;
        this.setPhone(phone);
        this.setCity(city);
        this.seatNumber = seatNumber;
        this.slotTime = slotTime;
    }

    private void extractedTimeSchedule(){

        this.timeSchedule = new LocalTime[2][2];

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

    public Time getSlotTime(){

        return slotTime;
    }


    public void setSlotTime(Time slotTime) {

        this.slotTime = slotTime;

    }


    public int getSeatNumber() {

        return seatNumber;

    }

    public void setSeatNumber(int seatNumber) {

        this.seatNumber = seatNumber;

    }

    public LocalTime[][] getTimeSchedule() {
        return timeSchedule;
    }

    public void setTimeSchedule(LocalTime[][] localTimes){

        this.timeSchedule = localTimes;

    }
}

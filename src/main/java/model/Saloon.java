package model;
import java.sql.Time;
import java.time.LocalTime;

public class Saloon {

    private String name;
    private String city;
    private String address;
    private String phone;
    private Time slotTime;
    private int seatNumber;
    private LocalTime[][] timeSchedule;
    private Integer[] numberOfSlots;

    public Saloon(String name, String[] cityAndAddress, String phone, Time slotTime, int seatNumber, LocalTime[][] localTimes, Integer[] numberOfSlots){

        this.name = name;
        this.city = cityAndAddress[0];
        this.address = cityAndAddress[1];
        this.phone = phone;
        this.slotTime = slotTime;
        this.seatNumber = seatNumber;
        this.timeSchedule = localTimes;
        this.numberOfSlots = numberOfSlots;

    }

    public Saloon(String name, String city, String address, String phone, Time slotTime, int seatNumber, Integer[] numberOfSlots){

        this.name = name ;
        this.city = city ;
        this.address = address ;
        this.phone = phone ;
        this.slotTime = slotTime ;
        this.seatNumber = seatNumber ;
        this.numberOfSlots = numberOfSlots ;

    }

    public Saloon(String city, String name, String address, String phone,Time slotTime, int seatNumber){

        this.extractedTimeSchedule();
        this.extractedNumberOfSlots();
        this.name = name;
        this.address = address;
        this.setPhone(phone);
        this.setCity(city);
        this.seatNumber = seatNumber;
        this.slotTime = slotTime;
    }

    public Saloon(String saloonName, Time openMorningTime, Time openAfternoonTime, Time closeMorningTime, Time closeAfternoonTime, Time intervalSlotTime) {

    }

    private void extractedNumberOfSlots(){

        this.numberOfSlots = new Integer[2];

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

    public void setNumberOfSlots(Integer[] numberOfSlots){

        this.numberOfSlots = numberOfSlots;

    }

    public Integer[] getNumberOfSlots() {

        return this.numberOfSlots;

    }

}
package model;

import java.sql.Time;

public class Saloon {

    private String name;
    private String city;
    private String address;
    private String phone;
    private Time slotTime;
    private int seatNumber;
    private Time openingMorningTime;
    private Time closeMorningTime;
    private Time openingAfternoonTime;
    private Time closeAfternoonTime;
    private Integer numberOfMorningSlots;
    private Integer numberOfAfternoonSlots;

    public Saloon(String name, String phone, String address, String city) {

        this.name = name;
        this.phone = phone;
        this.address = address;
        this.city = city;

    }

    public Saloon(String name, String[] cityAndAddress, String phone, Time slotTime, int seatNumber, Integer[] numberOfSlots){

        this.name = name;
        this.city = cityAndAddress[0];
        this.address = cityAndAddress[1];
        this.phone = phone;
        this.slotTime = slotTime;
        this.seatNumber = seatNumber;
        this.numberOfMorningSlots = numberOfSlots[0];
        this.numberOfAfternoonSlots = numberOfSlots[1];

    }

    public Saloon(String name, String city, String address, String phone, Time slotTime, int seatNumber, Integer[] numberOfSlots){

        this.name = name ;
        this.city = city ;
        this.address = address ;
        this.phone = phone ;
        this.slotTime = slotTime ;
        this.seatNumber = seatNumber ;
        this.numberOfMorningSlots = numberOfSlots[0] ;
        this.numberOfAfternoonSlots = numberOfSlots[1];

    }

    public Saloon(String city, String name, String address, String phone,Time slotTime, int seatNumber){

        this.name = name;
        this.address = address;
        this.setPhone(phone);
        this.setCity(city);
        this.seatNumber = seatNumber;
        this.slotTime = slotTime;

    }

    public Saloon(String saloonName, Time openMorningTime, Time openAfternoonTime, Time closeMorningTime, Time closeAfternoonTime, Time intervalSlotTime) {

        this.name = saloonName;
        this.openingMorningTime = openMorningTime;
        this.openingAfternoonTime = openAfternoonTime;
        this.closeAfternoonTime = closeAfternoonTime;
        this.closeMorningTime = closeMorningTime;
        this.slotTime = intervalSlotTime;

    }

    public Saloon(String saloonName, Time[][] time, Time intervalSlotTime, int[] numberSlotTime) {

        this.name = saloonName;
        this.closeMorningTime= time[0][1];
        this.closeAfternoonTime = time[1][0];
        this.openingMorningTime = time[0][0];
        this.openingAfternoonTime = time[1][1];
        this.slotTime = intervalSlotTime;
        this.numberOfMorningSlots = numberSlotTime[0];
        this.numberOfAfternoonSlots = numberSlotTime[1];

    }
    public String getName(){

        return this.name;

    }

    public void setName(String name){

        this.name = name;

    }

    public String getCity(){

        return this.city;

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

    public Integer getNumberOfMorningSlots() {

        return numberOfMorningSlots;

    }

    public void setNumberOfMorningSlots(Integer numberOfMorningSlots) {

        this.numberOfMorningSlots = numberOfMorningSlots;

    }

    public Integer getNumberOfAfternoonSlots() {

        return numberOfAfternoonSlots;

    }

    public void setNumberOfAfternoonSlots(Integer numberOfAfternoonSlots) {

        this.numberOfAfternoonSlots = numberOfAfternoonSlots;

    }

    public Time getOpeningMorningTime() {

        return openingMorningTime;

    }

    public void setOpeningMorningTime(Time openingMorningTime) {

        this.openingMorningTime = openingMorningTime;

    }

    public Time getCloseMorningTime() {

        return closeMorningTime;

    }

    public void setCloseMorningTime(Time closeMorningTime) {

        this.closeMorningTime = closeMorningTime;

    }

    public Time getOpeningAfternoonTime() {

        return openingAfternoonTime;

    }

    public void setOpeningAfternoonTime(Time openingAfternoonTime) {

        this.openingAfternoonTime = openingAfternoonTime;

    }

    public Time getCloseAfternoonTime() {

        return closeAfternoonTime;

    }

    public void setCloseAfternoonTime(Time closeAfternoonTime) {

        this.closeAfternoonTime = closeAfternoonTime;

    }

}
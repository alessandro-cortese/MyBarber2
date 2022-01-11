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
    private LocalTime openingMorningTime;
    private LocalTime closeMorningTime;
    private LocalTime openingAfternoonTime;
    private LocalTime closeAfternoonTime;
    private Integer numberOfMorningSlots;
    private Integer numberOfAfternoonSlots;

    public Saloon(String name, String[] cityAndAddress, String phone, Time slotTime, int seatNumber, LocalTime[][] localTimes, Integer[] numberOfSlots){

        this.name = name;
        this.city = cityAndAddress[0];
        this.address = cityAndAddress[1];
        this.phone = phone;
        this.slotTime = slotTime;
        this.seatNumber = seatNumber;
        this.openingMorningTime = localTimes[0][1];
        this.closeMorningTime = localTimes[0][2];
        this.openingAfternoonTime = localTimes[1][0];
        this.closeAfternoonTime = localTimes[1][1];
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

    public LocalTime getOpeningMorningTime() {

        return openingMorningTime;

    }

    public void setOpeningMorningTime(LocalTime openingMorningTime) {

        this.openingMorningTime = openingMorningTime;

    }

    public LocalTime getCloseMorningTime() {

        return closeMorningTime;

    }

    public void setCloseMorningTime(LocalTime closeMorningTime) {

        this.closeMorningTime = closeMorningTime;

    }

    public LocalTime getOpeningAfternoonTime() {

        return openingAfternoonTime;

    }

    public void setOpeningAfternoonTime(LocalTime openingAfternoonTime) {

        this.openingAfternoonTime = openingAfternoonTime;

    }

    public LocalTime getCloseAfternoonTime() {

        return closeAfternoonTime;

    }

    public void setCloseAfternoonTime(LocalTime closeAfternoonTime) {

        this.closeAfternoonTime = closeAfternoonTime;

    }

}
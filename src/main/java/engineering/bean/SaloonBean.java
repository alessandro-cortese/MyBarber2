package engineering.bean;

import java.sql.Time;
import java.time.LocalTime;

public class SaloonBean {

    private String name;
    private String address;
    private String phone;
    private String city;
    private Time slotTime;
    private  int seatNumber;
    private Time openingMorningTime;
    private Time closeMorningTime;
    private Time openingAfternoonTime;
    private Time closeAfternoonTime;
    private Integer numberOfMorningSlots;
    private Integer numberOfAfternoonSlots;


    public SaloonBean(){

    } //CONSTRUCTOR-NO ARGS MUST BE EMPTY!

    public SaloonBean(boolean type, String string){

        if(type) //if type is true, then the string inserted is "by name"
            this.name = name;
        else    // by city
            this.city = city;

    }

    public SaloonBean(String saloonName) {

        this.name = saloonName;

    }

    public SaloonBean(String name,String address,String city, String phone, int seatNumber, int slotTimeMinutes){

        this.address = address;
        this.name = name;
        this.phone = phone;
        this.seatNumber = seatNumber;
        this.city = city;
        this.slotTime = Time.valueOf(LocalTime.of(0, slotTimeMinutes));

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getPhone() {

        return phone;

    }

    public void setPhone(String phone) {

        this.phone = phone;

    }

    public String getAddress() {

        return address;

    }

    public void setAddress(String address) {

        this.address = address;

    }

    public int getSeatNumber() {

        return seatNumber;

    }

    public void setSeatNumber(int seatNumber) {

        this.seatNumber = seatNumber;

    }

    public Time getSlotTime() {

        return slotTime;

    }

    public void setSlotTime(Time slotTime) {

        this.slotTime = slotTime;

    }

    public String getCity() {

        return city;

    }

    public void setCity(String city) {

        this.city = city;

    }

    public void setNumberOfMorningSlots(Integer morningSlots) {

        this.numberOfMorningSlots = morningSlots;

    }

    public Integer getNumberOfMorningSlots() {

        return this.numberOfMorningSlots;

    }

    public void setNumberOfAfternoonSlots(Integer afternoonSlots) {

        this.numberOfAfternoonSlots = afternoonSlots;

    }

    public Integer getNumberOfAfternoonSlots() {

        return this.numberOfAfternoonSlots;

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

        return Time.valueOf(String.valueOf(closeAfternoonTime));

    }

    public void setCloseAfternoonTime(Time closeAfternoonTime) {

        this.closeAfternoonTime = closeAfternoonTime;

    }

}
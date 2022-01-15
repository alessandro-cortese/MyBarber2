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
    private Time openingMorningTimeInfo;
    private Time closeMorningTimeInfo;
    private Time openingAfternoonTimeInfo;
    private Time closeAfternoonTimeInfo;
    private Integer numberOfMorningSlotsInfo;
    private Integer numberOfAfternoonSlotsInfo;


    public SaloonBean(){

    } //CONSTRUCTOR-NO ARGS MUST BE EMPTY!

    public SaloonBean(boolean type, String string){

        if(type) //if type is true, then the string inserted is "by name"
            this.name = string;
        else    // by city
            this.city = string;

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

    public void setNumberOfMorningSlotsInfo(Integer morningSlots) {

        this.numberOfMorningSlotsInfo = morningSlots;

    }

    public Integer getNumberOfMorningSlotsInfo() {

        return this.numberOfMorningSlotsInfo;

    }

    public void setNumberOfAfternoonSlotsInfo(Integer afternoonSlots) {

        this.numberOfAfternoonSlotsInfo = afternoonSlots;

    }

    public Integer getNumberOfAfternoonSlotsInfo() {

        return this.numberOfAfternoonSlotsInfo;

    }

    public Time getOpeningMorningTimeInfo() {

        return openingMorningTimeInfo;

    }

    public void setOpeningMorningTimeInfo(Time openingMorningTimeInfo) {

        this.openingMorningTimeInfo = openingMorningTimeInfo;

    }

    public Time getCloseMorningTimeInfo() {

        return closeMorningTimeInfo;

    }

    public void setCloseMorningTimeInfo(Time closeMorningTimeInfo) {

        this.closeMorningTimeInfo = closeMorningTimeInfo;

    }

    public Time getOpeningAfternoonTimeInfo() {

        return openingAfternoonTimeInfo;

    }

    public void setOpeningAfternoonTimeInfo(Time openingAfternoonTimeInfo) {

        this.openingAfternoonTimeInfo = openingAfternoonTimeInfo;

    }

    public Time getCloseAfternoonTimeInfo() {

        return closeAfternoonTimeInfo;
    }

    public void setCloseAfternoonTimeInfo(Time closeAfternoonTimeInfo) {

        this.closeAfternoonTimeInfo = closeAfternoonTimeInfo;

    }

}
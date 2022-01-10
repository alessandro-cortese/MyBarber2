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
    private LocalTime[][] timeSchedule;
    private Integer[] numberOfSlots;

    public SaloonBean(){

        this.extractTimeSchedule();
        this.extractNumberOfSlots();

    } //CONSTRUCTOR-NO ARGS MUST BE EMPTY!

    public SaloonBean(boolean type, String string){

        this.extractTimeSchedule();

        if(type)
            this.name=name;
        else
            this.city=city;

    }

    private void extractTimeSchedule(){

        this.timeSchedule = new LocalTime[2][2];

    }

    private void extractNumberOfSlots() {

        this.numberOfSlots = new Integer[2];

    }

    public SaloonBean(String name,String address,String city, String phone, int seatNumber, int slotTimeMinutes){

        this.extractTimeSchedule();
        this.extractNumberOfSlots();
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

    public void setTimeSchedule(LocalTime[][] localTimes){

        this.timeSchedule = localTimes;

    }

    public void setNumberOfSlots(Integer[] numberOfSlots) {

        this.numberOfSlots = numberOfSlots;

    }

    public Integer[] getNumberOfSlots() {

        return this.numberOfSlots;

    }

}

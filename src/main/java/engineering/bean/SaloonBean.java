package engineering.bean;

public class SaloonBean {
    private String name;
    private String address;
    private String phone;
    private String city;
    private String slotTime;
    private  int seatNumber;

    public SaloonBean(){} //CONSTRUCTOR-NO ARGS MUST BE EMPTY!

    public SaloonBean(String name,String address,String city, String phone, String slotTime, int seatNumber){
        this.address=address;
        this.name=name;
        this.phone=phone;
        this.seatNumber=seatNumber;
        this.slotTime=slotTime;
        this.city=city;
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

    public String getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(String slotTime) {
        this.slotTime = slotTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

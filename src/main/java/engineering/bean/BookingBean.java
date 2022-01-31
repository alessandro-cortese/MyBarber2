package engineering.bean;

import model.Saloon;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookingBean {
    private  String saloonName;
    private String date;
    private Time toTime;
    private Time fromTime;
    private String nameCustomer;
    private String surnameCustomer;




    public BookingBean(){}

    public BookingBean(String saloonName, Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("EEEEEE", Locale.ENGLISH);
        String dayOfWeek =  formatter.format(date);
        this.date=dayOfWeek;

        this.saloonName = saloonName;
    }

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFromTime(Time fromTime) {
        this.fromTime = fromTime;
    }

    public Time getToTime() {
        return toTime;
    }

    public Time getFromTime() {
        return fromTime;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public String getSurnameCustomer() {
        return surnameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public void setSurnameCustomer(String surnameCustomer) {
        this.surnameCustomer = surnameCustomer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        String dayOfWeek = new SimpleDateFormat("EEEE").format(date);
        this.date = dayOfWeek;
    }

    public String getSaloonName() {
        return saloonName;
    }

    public void setSaloonName(String saloonName) {
        this.saloonName = saloonName;
    }
}

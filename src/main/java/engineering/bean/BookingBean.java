package engineering.bean;

import model.Saloon;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingBean {
    private  String saloonName;
    private Time time;
    private Date date;


    public BookingBean(){}

    public BookingBean(String saloonName, Date date){
        this.date=date;
        this.saloonName = saloonName;

    }
    public BookingBean(Time time){
        this.time = time;
    }

}

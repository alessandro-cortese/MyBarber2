package engineering.bean;

import model.Saloon;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingBean {
    private List<Saloon> listSaloon = new ArrayList<Saloon>();
    private Time time;
    private LocalDate date;


    //public BookingBean(){}

    //public BookingBean(ListSaloon, ){

    //}
    public BookingBean(Time time, LocalDate date){
        this.date=date;
        this.time= time;
    }


}

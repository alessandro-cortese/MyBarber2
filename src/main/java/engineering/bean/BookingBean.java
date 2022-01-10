package engineering.bean;

import model.Saloon;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingBean {
    private  String saloonName;
    private List<Saloon> listSaloon = new ArrayList<Saloon>();
    private Time time;
    private LocalDate date;


    //public BookingBean(){}

    //public BookingBean(ListSaloon, ){

    //}
    public BookingBean(String saloonName, LocalDate date){
        this.date=date;
        this.saloonName = saloonName;

    }
    public BookingBean(Time time){
        this.time = time;
    }

}

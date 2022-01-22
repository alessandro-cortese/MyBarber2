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


    public BookingBean(){}

    public BookingBean(String saloonName, Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("EEEEEE", Locale.ENGLISH);
        String dayOfWeek =  formatter.format(date);
        this.date=dayOfWeek;

        this.saloonName = saloonName;
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

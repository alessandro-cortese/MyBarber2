package engineering.bean;

import java.sql.Date;
import java.sql.Time;

public class TimeSlotBean {
    private int seatAvailable;
    private Time fromTime;
    private Time toTime;
    private int index;


    public TimeSlotBean(){}

    public TimeSlotBean(Time fromTime, Time toTime, String date){
        this.fromTime= fromTime;
        this.toTime= toTime;


    }

    public Time getFromTime() {
        return fromTime;
    }

    public Time getToTime() {
        return toTime;
    }


    public void setFromTime(Time fromTime) {
        this.fromTime = fromTime;
    }

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }

    public int getSeatAvailable() {
        return seatAvailable;
    }

    public void setSeatAvailable(int seatAvailable) {
        this.seatAvailable = seatAvailable;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public int getIndex() {
        return index;
    }
}

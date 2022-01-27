package engineering.time;

import java.sql.Time;


public class TimeSlot {//***************************CLASSE DI INGEGNERIZZAZIONE NON FACENTE PARTE DEL DOMINIO
    private int seatAvailable; //posti disponibili
    private Time fromTime;     //orario inizio timeSlot
    private Time toTime;       //orario fine timeSlot


    public int getSeatAvailable() {
        return seatAvailable;
    }

    public void setSeatAvailable(int seatAvailable) {
        this.seatAvailable = seatAvailable;
    }

    public Time getFromTime() {
        return fromTime;
    }

    public void setFromTime(Time fromTime) {
        this.fromTime = fromTime;
    }

    public Time getToTime() {
        return toTime;
    }

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }

}

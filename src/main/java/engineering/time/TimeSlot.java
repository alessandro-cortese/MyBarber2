package engineering.time;

import java.sql.Time;
import java.util.Date;

public class TimeSlot {//***************************CLASSE DI INGEGNERIZZAZIONE NON FACENTE PARTE DEL DOMINIO
    private int seatAvalaible; //posti disponibili
    private Time fromTime;     //orario inizio timeSlot
    private Time toTime;       //orario fine timeSlot

    public int getSeatAvalaible() {
        return seatAvalaible;
    }

    public void setSeatAvalaible(int seatAvalaible) {
        this.seatAvalaible = seatAvalaible;
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

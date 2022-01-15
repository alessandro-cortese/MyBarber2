package engineering.time;

import engineering.bean.SaloonBean;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ScheduleTime {   //classi di ingegnerizzazione che aggrega i time slot della mattina
    private List<TimeSlot> timeSlotList = new ArrayList<>();
    private TimeSlot timeSlot;
    private Time timeOpen;
    private Time sum;
    private Time sumTime;

    public ScheduleTime(SaloonBean saloonTimeSlots){
        timeOpen = saloonTimeSlots.getOpeningMorningTimeInfo();
        System.out.println(timeOpen);
        sumTime = new Time(timeOpen.getTime()); //return hours in milliseconds

        for (int i = 0; i< saloonTimeSlots.getNumberOfMorningSlotsInfo(); i++){
                timeSlot = new TimeSlot();
                timeSlot.setFromTime(sumTime);
                sumTime.setTime(sumTime.getTime() + saloonTimeSlots.getSlotTime().getTime());
                timeSlot.setToTime(sumTime);
                timeSlot.setSeatAvailable(saloonTimeSlots.getSeatNumber());
                timeSlotList.add(timeSlot);

        }

        timeOpen = saloonTimeSlots.getOpeningAfternoonTimeInfo();
        sumTime = new Time(timeOpen.getTime());

        for (int i =0; i < saloonTimeSlots.getNumberOfAfternoonSlotsInfo();i++){
            timeSlot = new TimeSlot();
            sumTime.setTime(sumTime.getTime() + saloonTimeSlots.getSlotTime().getTime());
            timeSlot.setToTime(sumTime);
            timeSlot.setSeatAvailable(saloonTimeSlots.getSeatNumber());
            timeSlotList.add(timeSlot);
        }
    }

    public List<TimeSlot> timeSlotsIstance(){
        return timeSlotList;
    }







}

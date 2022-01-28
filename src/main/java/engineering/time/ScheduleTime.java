package engineering.time;


import model.Saloon;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleTime {
    private  int seatNumber;
    private  int slotTimeAfternoon;
    private  int slotTimeMorning;
    private  LocalTime intervalSlotTime;   //classi di ingegnerizzazione che aggrega i time slot della mattina
    private List<TimeSlot> timeSlotList;
    private Time timeOpen;
    private Time sumTime;
    TimeSlot timeSlot;

    public ScheduleTime(Saloon saloonTimeSlots){
        timeSlotList = new ArrayList<TimeSlot>();
        timeOpen = saloonTimeSlots.getOpeningMorningTime();
        sumTime = new Time(timeOpen.getTime());
        seatNumber = saloonTimeSlots.getSeatNumber();


        intervalSlotTime = saloonTimeSlots.getSlotTime();
        slotTimeMorning = saloonTimeSlots.getNumberOfMorningSlots();
        slotTimeAfternoon = saloonTimeSlots.getNumberOfAfternoonSlots();

    }

        public List<TimeSlot> CreateSlotTime(){

            for (int i = 0; i< slotTimeMorning ; i++){
                timeSlot = new TimeSlot();
                long in = timeOpen.getTime();
                timeOpen = new Time(in);
                timeSlot.setFromTime(timeOpen);

                int minute = intervalSlotTime.getMinute();
                int millisec = minute*60000;

                timeOpen.setTime(timeOpen.getTime());
                long i2 = timeOpen.getTime()+millisec;
                timeOpen = new Time(i2);
                System.out.println("dopo: "+timeOpen);
                timeSlot.setToTime(timeOpen);
                timeSlot.setSeatAvailable(seatNumber);

                timeSlotList.add(timeSlot);

            }
            return timeSlotList;
        }

       /* timeOpen = saloonTimeSlots.getOpeningAfternoonTimeInfo();
        sumTime = new Time(timeOpen.getTime());
        System.out.println(sumTime);


        for (int i = saloonTimeSlots.getNumberOfMorningSlotsInfo() +1 ; i < saloonTimeSlots.getNumberOfAfternoonSlotsInfo();i++){
            timeSlot = new TimeSlot();
            sumTime.setTime(sumTime.getTime() + saloonTimeSlots.getSlotTime().getTime());
            timeSlot.setToTime(sumTime);
            timeSlot.setSeatAvailable(saloonTimeSlots.getSeatNumber());
            timeSlotList.add(timeSlot);
        }*/

}

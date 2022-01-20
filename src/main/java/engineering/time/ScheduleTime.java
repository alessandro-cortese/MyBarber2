package engineering.time;

import engineering.bean.SaloonBean;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleTime {
    private  int seatNumber;
    private  Integer slotTimeAfternoon;
    private  Integer slotTimeMorning;
    private  LocalTime intervalSlotTime;   //classi di ingegnerizzazione che aggrega i time slot della mattina
    private List<TimeSlot> timeSlotList;
    private Time timeOpen;
    private Time sumTime;

    public ScheduleTime(SaloonBean saloonTimeSlots){
        timeOpen = saloonTimeSlots.getOpeningMorningTimeInfo();
        seatNumber = saloonTimeSlots.getSeatNumber();
        System.out.println(saloonTimeSlots.getOpeningMorningTimeInfo());
        System.out.println(saloonTimeSlots.getNumberOfMorningSlotsInfo());
        sumTime = new Time(timeOpen.getTime()); //return hours in milliseconds
        intervalSlotTime = saloonTimeSlots.getSlotTime();
        System.out.println(intervalSlotTime);
        slotTimeMorning = saloonTimeSlots.getNumberOfMorningSlotsInfo();
        slotTimeAfternoon = saloonTimeSlots.getNumberOfAfternoonSlotsInfo();

    }

        public List<TimeSlot> CreateSlotTime(){

            timeSlotList = new ArrayList<>();
            TimeSlot timeSlot;
            for (int i = 0; i< slotTimeMorning ; i++){
                timeSlot = new TimeSlot();
                long in = timeOpen.getTime();
                timeOpen = new Time(in);
                timeSlot.setFromTime(timeOpen);
                int minute = intervalSlotTime.getMinute();
                int millisec = minute*60000;

                System.out.println("prima: "+timeOpen);
                timeOpen.setTime(timeOpen.getTime());
                long i2 = timeOpen.getTime()+millisec;
                timeOpen = new Time(i2);
                System.out.println("dopo: "+timeOpen);
                timeSlot.setToTime(timeOpen);
                timeSlot.setSeatAvailable(seatNumber);
                timeSlotList.add(timeSlot);

            }

            for (TimeSlot time : timeSlotList){
                //System.out.println(time.getFromTime());
                //System.out.println(time.getToTime());
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

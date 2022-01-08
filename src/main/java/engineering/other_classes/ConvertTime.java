package engineering.other_classes;

public class ConvertTime {

    private ConvertTime(){}

    public static int convertTime(String time){

        String[] localTime = time.split(":");
        int hours = Integer.parseInt(localTime[0]);
        int minutes = Integer.parseInt(localTime[1]);

        return (hours * 60) + minutes;

    }

}

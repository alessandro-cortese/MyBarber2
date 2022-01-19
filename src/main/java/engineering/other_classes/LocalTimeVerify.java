package engineering.other_classes;

import java.time.DateTimeException;
import java.time.LocalTime;

public class LocalTimeVerify {

    private LocalTimeVerify() {}

    public static boolean isLocalTime(String[] times){
        try{
            LocalTime.of(Integer.parseInt(times[0]), Integer.parseInt(times[1]));
            return true;
        }catch(DateTimeException e){
            return false;
        }

    }

}

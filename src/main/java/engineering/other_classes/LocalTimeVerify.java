package engineering.other_classes;

import java.time.DateTimeException;
import java.time.LocalTime;

public class LocalTimeVerify {

    private LocalTimeVerify() {}

    public static LocalTime isLocalTime(String[] times){
        try{
            return LocalTime.of(Integer.parseInt(times[0]), Integer.parseInt(times[1]));
        }catch(DateTimeException e){
            return null;
        }

    }

}

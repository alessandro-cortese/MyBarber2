package engineering.otherclasses;

import javafx.scene.Node;

public class NumericVerify {

    public static boolean isNumeric(String insertString) {
        try{
            Double.parseDouble(insertString);
            return true;
        }catch (NumberFormatException e){
            return false;
        }

    }

}

package engineering.exception;

import javafx.scene.control.Alert;

public class InvalidTimeSlot extends Exception {

    public  InvalidTimeSlot(){
        super();
    }

    public InvalidTimeSlot(String message){
        super(message);
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

}

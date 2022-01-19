package second_view.barber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;

import java.time.DateTimeException;
import java.time.LocalTime;

import static engineering.other_classes.NumericVerify.isNumeric;

public class BarberAddNewSaloonController {

    @FXML private TextField addSaloonCommandLine;
    @FXML private TextField addSaloonNameTextField;
    @FXML private TextField addSaloonAddressTextField;
    @FXML private TextField addSaloonCityTextField;
    @FXML private TextField addSaloonPhoneTextField;
    @FXML private TextField addSaloonSlotTimeTextField;
    @FXML private TextField addSaloonSeatsNumberTimeTextField;
    @FXML private TextField addSaloonOpenMorningTimeTextField;
    @FXML private TextField addSaloonCloseMorningTimeTextField;
    @FXML private TextField addSaloonOpenAfternoonTimeTextField;
    @FXML private TextField addSaloonCloseAfternoonTimeTextField;
    @FXML private Label errorAddSaloonLabel;

    private static final String SET_NAME_NEW_SALOON_COMMAND = "set name";
    private static final String SET_ADDRESS_NEW_SALOON_COMMAND = "set address";
    private static final String SET_CITY_NEW_SALOON_COMMAND = "set city";
    private static final String SET_PHONE_NEW_SALOON_COMMAND = "set phone number";
    private static final String SET_SLOT_TIME_NEW_SALOON_COMMAND = "set slot time";
    private static final String SET_SEATS_NUMBER_NEW_SALOON_COMMAND = "set seats number";
    private static final String SET_OPEN_MORNING_TIME_COMMAND = "set open morning time";
    private static final String SET_CLOSE_MORNING_TIME_COMMAND = "set close morning time";
    private static final String SET_OPEN_AFTERNOON_TIME_COMMAND = "set open afternoon time";
    private static final String SET_CLOSE_AFTERNOON_TIME_COMMAND = "set close afternoon time";

    @FXML
    public void onCommand(ActionEvent event) {

        String addSaloonCommandLineLocal = addSaloonCommandLine.getText();
        addSaloonCommandLine.setText("");
        addSaloonCommandLine.setStyle(null);
        errorAddSaloonLabel.setText("");
        String newField;
        String[] times;

        if(addSaloonCommandLineLocal.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return ;
        }
        else if(addSaloonCommandLineLocal.startsWith(SET_NAME_NEW_SALOON_COMMAND)) {
            newField = addSaloonCommandLineLocal.replace(SET_NAME_NEW_SALOON_COMMAND + " ", "");
            addSaloonNameTextField.setText(newField);
            return ;
        }
        else if(addSaloonCommandLineLocal.startsWith(SET_ADDRESS_NEW_SALOON_COMMAND)) {
            newField = addSaloonCommandLineLocal.replace(SET_ADDRESS_NEW_SALOON_COMMAND + " ", "");
            addSaloonAddressTextField.setText(newField);
            return ;
        }else if(addSaloonCommandLineLocal.startsWith(SET_CITY_NEW_SALOON_COMMAND)) {
            newField = addSaloonCommandLineLocal.replace(SET_CITY_NEW_SALOON_COMMAND + " ", "");
            addSaloonCityTextField.setText(newField);
            return ;
        }
        else if(addSaloonCommandLineLocal.startsWith(SET_PHONE_NEW_SALOON_COMMAND)) {
            newField = addSaloonCommandLineLocal.replace(SET_PHONE_NEW_SALOON_COMMAND + " ", "");
            addSaloonPhoneTextField.setText(newField);
            return ;
        }
        else if(addSaloonCommandLineLocal.startsWith(SET_SLOT_TIME_NEW_SALOON_COMMAND)) {
            newField = addSaloonCommandLineLocal.replace(SET_PHONE_NEW_SALOON_COMMAND + " ", "");
            addSaloonSlotTimeTextField.setText(newField);
            return;
        }
        else if(addSaloonCommandLineLocal.startsWith(SET_SEATS_NUMBER_NEW_SALOON_COMMAND)) {
            newField = addSaloonCommandLineLocal.replace(SET_SEATS_NUMBER_NEW_SALOON_COMMAND + " ", "");
            if(isNumeric(newField)) {
                addSaloonSeatsNumberTimeTextField.setText(newField);
                return;
            }
        }
        else if(addSaloonCommandLineLocal.startsWith(SET_OPEN_MORNING_TIME_COMMAND)) {
            times = addSaloonCommandLineLocal.replace(SET_OPEN_MORNING_TIME_COMMAND + " ", "").split("-");
            newField = addSaloonCommandLineLocal.replace(SET_OPEN_MORNING_TIME_COMMAND + " ", "");
            if(isNumeric(times[0]) && isNumeric(times[1]) && isLocalTime(times)){
                addSaloonOpenMorningTimeTextField.setText(newField);
                return;
            }
            else {
                displayErrorTime();
            }
        }
        else if(addSaloonCommandLineLocal.startsWith(SET_CLOSE_MORNING_TIME_COMMAND)) {
            times = addSaloonCommandLineLocal.replace(SET_CLOSE_MORNING_TIME_COMMAND + " ", "").split("-");
            newField = addSaloonCommandLineLocal.replace(SET_CLOSE_MORNING_TIME_COMMAND + " ", "");
            if(isNumeric(times[0]) && isNumeric(times[1]) && isLocalTime(times)) {
                addSaloonCloseMorningTimeTextField.setText(newField);
                return;
            }
            else{
                displayErrorTime();
            }
        }
        else if(addSaloonCommandLineLocal.startsWith(SET_OPEN_AFTERNOON_TIME_COMMAND)) {
            times = addSaloonCommandLineLocal.replace(SET_OPEN_AFTERNOON_TIME_COMMAND + " ", "").split("-");
            newField = addSaloonCommandLineLocal.replace(SET_OPEN_AFTERNOON_TIME_COMMAND + " ", "");
            if(isNumeric(times[0]) && isNumeric(times[1]) && isLocalTime(times)) {
                addSaloonOpenAfternoonTimeTextField.setText(newField);
                return;
            }
            else{
                displayErrorTime();
            }
        }
        else if(addSaloonCommandLineLocal.startsWith(SET_CLOSE_AFTERNOON_TIME_COMMAND)) {
            times = addSaloonCommandLineLocal.replace(SET_CLOSE_AFTERNOON_TIME_COMMAND + " ", "").split("-");
            newField = addSaloonCommandLineLocal.replace(SET_CLOSE_AFTERNOON_TIME_COMMAND + " ", "");
            if(isNumeric(times[0]) && isNumeric(times[1]) && isLocalTime(times)) {
                addSaloonCloseAfternoonTimeTextField.setText(newField);
                return;
            }
            else{
                displayErrorTime();
            }
        }

        addSaloonCommandLine.setStyle("-fx-border-color: red");

    }

    public void displayErrorTime() {
        errorAddSaloonLabel.setText("Wrong insert time");
    }

    private boolean isLocalTime(String[] times){

        try{
            LocalTime localTime = LocalTime.of(Integer.parseInt(times[0]), Integer.parseInt(times[1]));
            return true;
        }catch(DateTimeException e){
            return false;
        }

    }

}
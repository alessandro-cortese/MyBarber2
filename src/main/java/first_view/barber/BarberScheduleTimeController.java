package first_view.barber;

import first_view.general.InternalBackController;
import first_view.pickers.TimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;


public class BarberScheduleTimeController {

    public BarberScheduleTimeController(){
        this.extractLocalTime();
    }

    public void setSlotMinutes(Integer slotMinutes) {

        this.slotMinutes = slotMinutes;
        this.extractNumberOfSlot();

    }


    private LocalTime[][] localTime;

    private Integer[] numberOfSlot;

    private Integer slotMinutes;

    @FXML private Button saveButton;
    @FXML private TextField openMorningTime;
    @FXML private TextField closeMorningTime;
    @FXML private TextField openAfternoonTime;
    @FXML private TextField closeAfternoonTime;

    private void extractLocalTime(){
        localTime = new LocalTime[2][2];
    }

    private void extractNumberOfSlot(){
        numberOfSlot = new Integer[2];
    }

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Button sourceButton = (Button) event.getSource();
        int minutes;

        for (int i = 0; i < localTime.length; i++) {
            for (int j = 0; j < localTime[i].length - 1; j++){
                minutes = ((localTime[i][j + 1].getHour() * 60) + localTime[i][j + 1].getMinute()) - ((localTime[i][j].getHour() * 60) + localTime[i][j].getMinute());
                numberOfSlot[i] = minutes / slotMinutes;
            }
        }

        for (Integer integer : numberOfSlot) {
            System.out.println(integer);
        }

        if(sourceButton == saveButton){
            InternalBackController.getInternalBackControllerInstance().backToHome((Node) event.getSource());
        }

    }

    @FXML
    public void onChangeTime(MouseEvent event) throws IOException {

        TimePicker timePicker = null;
        TextField textField = (TextField) event.getSource();

        String[] time;

        if(textField == openMorningTime || textField == closeMorningTime) {
            timePicker = new TimePicker(7, 13);
        }
        else if(textField == openAfternoonTime || textField == closeAfternoonTime) {
            timePicker = new TimePicker(14, 21);
        }

        textField.setText(timePicker.getTime());
        time = textField.getText().split(":");

        for(int i = 0; i < time.length; i++){
            if(textField == openMorningTime){
                localTime[0][0] = LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0);
            }
            else if(textField == closeMorningTime){
                localTime[0][1] = LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0);
            }
            else if(textField == openAfternoonTime){
                localTime[1][0] = LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0);
            }
            else if(textField == closeAfternoonTime){
                localTime[1][1] = LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0);
            }
        }


    }

}

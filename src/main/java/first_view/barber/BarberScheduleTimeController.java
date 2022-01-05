package first_view.barber;

import first_view.general.InternalBackController;
import first_view.pickers.TimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.time.LocalTime;


public class BarberScheduleTimeController {

    @FXML private CheckBox mondayMorningCheckBox;
    @FXML private CheckBox mondayAfternoonCheckBox;
    @FXML private CheckBox dayTwoMorningCheckBox;
    @FXML private CheckBox dayTwoAfternoonCheckBox;
    @FXML private CheckBox dayThreeMorningCheckBox;
    @FXML private CheckBox dayThreeAfternoonCheckBox;
    @FXML private CheckBox dayFourAfternoonCheckBox;
    @FXML private CheckBox dayFourMorningCheckBox;
    @FXML private CheckBox dayFiveMorningCheckBox;
    @FXML private CheckBox dayFiveAfternoonCheckBox;
    @FXML private CheckBox daySixMorningCheckBox;
    @FXML private CheckBox daySixAfternoonCheckBox;
    @FXML private TextField mondayMorningOpen;
    @FXML private TextField mondayMorningClose;
    @FXML private TextField mondayAfternoonOpen;
    @FXML private TextField mondayAfternoonClose;
    @FXML private TextField dayTwoMorningOpen;
    @FXML private TextField dayTwoMorningClose;
    @FXML private TextField dayTwoAfternoonOpen;
    @FXML private TextField dayTwoAfternoonClose;
    @FXML private TextField dayThreeMorningOpen;
    @FXML private TextField dayThreeMorningClose;
    @FXML private TextField dayThreeAfternoonOpen;
    @FXML private TextField dayThreeAfternoonClose;
    @FXML private TextField dayFourMorningOpen;
    @FXML private TextField dayFourMorningClose;
    @FXML private TextField dayFourAfternoonOpen;
    @FXML private TextField dayFourAfternoonClose;
    @FXML private TextField dayFiveMorningOpen;
    @FXML private TextField dayFiveMorningClose;
    @FXML private TextField dayFiveAfternoonOpen;
    @FXML private TextField dayFiveAfternoonClose;
    @FXML private TextField daySixMorningOpen;
    @FXML private TextField daySixMorningClose;
    @FXML private TextField daySixAfternoonOpen;
    @FXML private TextField daySixAfternoonClose;

    private CheckBox[] arrayCheckBox;

    private void extractedCheckBox() {
        arrayCheckBox = new CheckBox[]{
                mondayMorningCheckBox,
                mondayAfternoonCheckBox,
                dayTwoMorningCheckBox,
                dayTwoAfternoonCheckBox,
                dayThreeMorningCheckBox,
                dayThreeAfternoonCheckBox,
                dayFourMorningCheckBox,
                dayFourAfternoonCheckBox,
                dayFiveMorningCheckBox,
                dayFiveAfternoonCheckBox,
                daySixMorningCheckBox,
                daySixAfternoonCheckBox
        };
    }

    private TextField[][] arrayTextFiled;

    private void extractedTextField(){

        arrayTextFiled = new TextField[][]{
                {mondayMorningOpen, mondayMorningClose },
                {mondayAfternoonOpen, mondayAfternoonClose},
                {dayTwoMorningOpen, dayTwoMorningClose },
                {dayTwoAfternoonOpen, dayTwoAfternoonClose},
                {dayThreeMorningOpen, dayThreeMorningClose},
                {dayThreeAfternoonOpen, dayThreeAfternoonClose},
                {dayFourMorningOpen, dayFourMorningClose},
                {dayFourAfternoonOpen, dayFourAfternoonClose},
                {dayFiveMorningOpen, dayFiveMorningClose},
                {dayFiveAfternoonOpen, dayFiveAfternoonClose},
                {daySixMorningOpen, daySixMorningClose},
                {daySixAfternoonOpen, daySixAfternoonClose}
        };
    }

    private LocalTime[][] localTime;

    private LocalTime localTime2;

    private void extractedLocalTime(){
        localTime = new LocalTime[12][2];
    }

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Button sourceButton = (Button) event.getSource();

        System.out.println(localTime2);

        int i;
        int j;

        for (i = 0; i < localTime.length; i++)
            for(j = 0; j < localTime[i].length; j++)
                System.out.println(localTime[i][j]);

        if(sourceButton.getId().equals("saveButton")){
            InternalBackController.getInternalBackControllerInstance().backToHome((Node) event.getSource());
        }

    }

    @FXML
    public void onCheckSelected(ActionEvent event){
        CheckBox localCheckBox = (CheckBox) event.getSource();
        this.extractedCheckBox();
        this.extractedTextField();
        int i;
        boolean flag = true;
        int index = 0;

        for(i = 0; i < arrayCheckBox.length && flag; i++) {
            if(localCheckBox == arrayCheckBox[i]) {
                index = i;
                flag = false;
            }
        }

        if(arrayCheckBox[index].isSelected()){

            arrayTextFiled[index][0].setVisible(false);
            arrayTextFiled[index][1].setVisible(false);

        }else{

            arrayTextFiled[index][0].setVisible(true);
            arrayTextFiled[index][1].setVisible(true);

        }

    }

    @FXML
    public void onChangeTime(MouseEvent event) throws IOException {
        TextField textField = (TextField) event.getSource();
        this.extractedLocalTime();
        this.extractedTextField();
        int i;
        int j;
        int rawIndex = 0;
        boolean flag = true;
        TimePicker timePicker;


        for(i = 0; i < arrayTextFiled.length && flag; i++) {
            for(j = 0; j < arrayTextFiled[i].length && flag; j++) {
                if(textField == arrayTextFiled[i][j]){
                    rawIndex = i;
                    flag = false;
                }
            }
        }

       if(rawIndex % 2 == 0){
           timePicker = new TimePicker(7, 13);
       }else{
           timePicker = new TimePicker(14, 21);
       }

       textField.setText(timePicker.getTime());
       
       String localHour = textField.getText();
       String[] hour = localHour.split(":");

       for(i = 0; i < arrayTextFiled.length; i++) {
           for(j = 0; j < arrayTextFiled[i].length; j++) {
               if(textField == arrayTextFiled[i][j]) {
                   saveTime(hour, i, j);
                   System.out.println(localTime[i][j]);
                   System.out.println(i);
                   System.out.println(j);
                    return;
               }
           }
       }

    }

    private void saveTime(String[] hour, int raw, int column){
        localTime[raw][column] = LocalTime.of(Integer.parseInt(hour[0]), Integer.parseInt(hour[1]), 0);
    }

}
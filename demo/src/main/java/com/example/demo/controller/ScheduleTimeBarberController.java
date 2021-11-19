package com.example.demo.controller;

import TimePicker.TimePicker;
<<<<<<< Updated upstream
import com.example.demo.OtherBarberScheduler.SchedulerRowController;
=======
import eu.hansolo.tilesfx.Test;
>>>>>>> Stashed changes
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.w3c.dom.Text;

import java.io.IOException;


public class ScheduleTimeBarberController {

    private CheckBox[] arrayCheckBox;

    {
        extractedCheckBox();
    }

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

    {
        extractedTextField();
    }

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


    @FXML
    private CheckBox mondayMorningCheckBox;

    @FXML
    private CheckBox mondayAfternoonCheckBox;

    @FXML
    private CheckBox dayTwoMorningCheckBox;

    @FXML
    private CheckBox dayTwoAfternoonCheckBox;

    @FXML
    private CheckBox dayThreeMorningCheckBox;

    @FXML
    private CheckBox dayThreeAfternoonCheckBox;

    @FXML
    private CheckBox dayFourAfternoonCheckBox;

    @FXML
    private CheckBox dayFourMorningCheckBox;

    @FXML
    private CheckBox dayFiveMorningCheckBox;

    @FXML
    private CheckBox dayFiveAfternoonCheckBox;

    @FXML
    private CheckBox daySixMorningCheckBox;

    @FXML
    private CheckBox daySixAfternoonCheckBox;


    @FXML
    private TextField mondayMorningOpen;
    @FXML
    private TextField mondayMorningClose;

    @FXML
    private TextField mondayAfternoonOpen;
    @FXML
    private TextField mondayAfternoonClose;

    @FXML
    private TextField dayTwoMorningOpen;
    @FXML
    private TextField dayTwoMorningClose;

    @FXML
    private TextField dayTwoAfternoonOpen;
    @FXML
    private TextField dayTwoAfternoonClose;

    @FXML
    private TextField dayThreeMorningOpen;
    @FXML
    private TextField dayThreeMorningClose;

    @FXML
    private TextField dayThreeAfternoonOpen;
    @FXML
    private TextField dayThreeAfternoonClose;

    @FXML
    private TextField dayFourMorningOpen;
    @FXML
    private TextField dayFourMorningClose;

    @FXML
    private TextField dayFourAfternoonOpen;
    @FXML
    private TextField dayFourAfternoonClose;

    @FXML
    private TextField dayFiveMorningOpen;
    @FXML
    private TextField dayFiveMorningClose;

    @FXML
    private TextField dayFiveAfternoonOpen;
    @FXML
    private TextField dayFiveAfternoonClose;

    @FXML
    private TextField daySixMorningOpen;
    @FXML
    private TextField daySixMorningClose;

    @FXML
    private TextField daySixAfternoonOpen;
    @FXML
    private TextField daySixAfternoonClose;

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Button sourceButton = (Button) event.getSource();
        FXMLLoader fxmlLoader;

        if(sourceButton.getId().equals("saveButton")){
            Scene myScene = (Scene) sourceButton.getScene();
            fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("com/example/demo/barber_home.fxml"));
            BorderPane myBorderPane = (BorderPane) myScene.getRoot();
            myBorderPane.setCenter(fxmlLoader.load());
        }

    }

    @FXML
    public void onCheckSelected(ActionEvent event){
        CheckBox localCheckBox = (CheckBox) event.getSource();
        this.extractedCheckBox();
        this.extractedTextField();
        int i = 0;
        boolean flag = true;
        int index = 0;

        for(i = 0; i < arrayCheckBox.length && flag; i++) {
            if(localCheckBox == arrayCheckBox[i]) {
                index = i;
                System.out.println(index);
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
        this.extractedTextField();
        int i = 0;
        int j = 0;
        int rawIndex = 0;
        int columnIndex = 0;
        boolean flag = true;

        for(i = 0; i < arrayTextFiled.length && flag; i++) {
            for(j = 0; j < arrayTextFiled[i].length && flag; j++) {
                if(textField == arrayTextFiled[i][j]){
                    rawIndex = i;
                    columnIndex = j;
                    flag = false;
                }
            }
        }

       if(rawIndex % 2 == 0){

           ((TextField) event.getSource()).setText((new TimePicker(7, 13)).getTime());

       }else{

           ((TextField) event.getSource()).setText((new TimePicker(14, 21)).getTime());

       }

    }


}

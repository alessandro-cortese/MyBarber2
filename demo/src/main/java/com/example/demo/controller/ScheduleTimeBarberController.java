package com.example.demo.controller;

import TimePicker.TimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ScheduleTimeBarberController {

    @FXML
    private CheckBox mondayMorningCheckBox;

    @FXML
    private CheckBox mondayAfternoonCheckBox;

    @FXML
    private TextField mondayMorningOpen;
    @FXML
    private TextField mondayMorningClose;
    @FXML
    private TextField mondayAfternoonOpen;
    @FXML
    private TextField mondayAfternoonClose;


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
        if(localCheckBox == mondayMorningCheckBox){
            if(mondayMorningCheckBox.isSelected()){

                mondayMorningOpen.setVisible(false);
                mondayMorningClose.setVisible(false);

            }else{

                mondayMorningOpen.setVisible(true);
                mondayMorningClose.setVisible(true);

            }
        }else if(localCheckBox == mondayAfternoonCheckBox){
            if(mondayAfternoonCheckBox.isSelected()){

                mondayAfternoonOpen.setVisible(false);
                mondayAfternoonClose.setVisible(false);

            }else{

                mondayAfternoonOpen.setVisible(true);
                mondayAfternoonClose.setVisible(true);

            }
        }

    }

    private void cheBoxSelected(CheckBox checkBox) {




    }

    @FXML
    public void onChangeTime(MouseEvent event) throws IOException {
        ((TextField) event.getSource()).setText((new TimePicker(0,12)).getTime());
    }



}

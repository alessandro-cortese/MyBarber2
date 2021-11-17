package com.example.demo.controller;

import com.example.demo.TimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ProvaTimePicker {

    @FXML
    public void onChangeTime(MouseEvent event) throws IOException {
        ((TextField) event.getSource()).setText((new TimePicker(7,12)).getTime());
    }
}

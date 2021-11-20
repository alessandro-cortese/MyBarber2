package com.example.demo.controller;

import PricePicker.PricePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BarberAddServiceController {

    @FXML private Button saveButton;
    @FXML private TextField priceTextField;

    private static final String HOME_BARBER_SCREEN_NAME = "com/example/demo/barber_home.fxml";

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Button sourceButton = (Button) event.getSource();
        if(sourceButton == saveButton){
            this.changeBorderPane(sourceButton.getScene(), HOME_BARBER_SCREEN_NAME);
        }
    }

    private void changeBorderPane(Scene scene, String string) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(string));
        BorderPane myBorderPane = (BorderPane) scene.getRoot();
        myBorderPane.setCenter(fxmlLoader.load());
    }

    @FXML
    public void onPricePicked(MouseEvent event) throws IOException {
        TextField sourceTextField = (TextField) event.getSource();
        if(sourceTextField == priceTextField){
            PricePicker pricePicker = new PricePicker(0, 0.0);
            priceTextField.setText(pricePicker.getPrice());
        }
    }

}

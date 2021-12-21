package view_1.otherbarberscheduler;

import view_1.pickers.TimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

import java.io.IOException;

public class SchedulerRowController {

    /*
        Notare che vengono gestite separatamente gli item di ogni riga: ogni riga ha il suo controller che
        si occupa di gestirlo e dal controller principale si possono prendere le info ciclando sull'array di controller.
     */
    @FXML private CheckBox morningCheckBox ;
    @FXML private CheckBox afternoonCheckBox ;

    @FXML private TextField morningOpen ;
    @FXML private TextField morningClose ;
    @FXML private TextField afternoonOpen ;
    @FXML private TextField afternoonClose ;



    @FXML
    public void onChangeTime(MouseEvent event) throws IOException {
        /*
            Serve per impostare l'orario sulla TextField cliccata.
            Viene invocato il metodo getTime del TimePicker che ritorna una stringa con l'orario selezionato
         */
        TextField sourceNode = (TextField) event.getSource() ;
        if (sourceNode == morningOpen || sourceNode == morningClose) {
            ((TextField) event.getSource()).setText((new TimePicker(7, 12)).getTime());
        }
        else {
            ((TextField) event.getSource()).setText((new TimePicker(13, 21)).getTime());
        }
    }


    @FXML
    public void onCheckSelected(ActionEvent event) {
        //Rende non selezionabili le CheckBox corrispondenti
        if (event.getSource() == morningCheckBox) {
            morningOpen.setDisable(morningCheckBox.isSelected());
            morningClose.setDisable(morningCheckBox.isSelected());
        }
        else if (event.getSource() == afternoonCheckBox) {
            afternoonOpen.setDisable(afternoonCheckBox.isSelected());
            afternoonClose.setDisable(afternoonCheckBox.isSelected());
        }
    }

    public Pair<Boolean, Boolean> getOpenStatus() {
        return new Pair<>(morningCheckBox.isSelected(), afternoonCheckBox.isSelected()) ;
    }

    public Pair<String, String> getMorningTime() {
        return new Pair<>(morningOpen.getText(), morningClose.getText()) ;
    }

    public Pair<String, String> getAfternoonTime() {
        return new Pair<>(afternoonOpen.getText(), afternoonClose.getText()) ;
    }
}

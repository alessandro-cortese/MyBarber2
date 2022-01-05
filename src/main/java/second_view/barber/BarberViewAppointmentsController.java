package second_view.barber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;

import java.time.LocalDate;

import static engineering.other_classes.NumericVerify.isNumeric;

public class BarberViewAppointmentsController {

    @FXML private TextField viewAppointmentsCommandLine;
    @FXML private TextField saloonNameFieldViewAppointments;
    @FXML private DatePicker datePickerViewAppointments;
    @FXML private TextField slotIndexFieldViewAppointments;

    @FXML
    public void onCommand(ActionEvent event) {

        String viewAppointmentsCommand = viewAppointmentsCommandLine.getText();
        viewAppointmentsCommandLine.setText("");
        viewAppointmentsCommandLine.setStyle(null);

        if(viewAppointmentsCommand.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return ;
        }
        else if(viewAppointmentsCommand.startsWith("select")) {
            if(selectCommand(viewAppointmentsCommand))
                return ;
        }
        else if(viewAppointmentsCommand.startsWith("delete")) {

            int slotIndex = Integer.parseInt(viewAppointmentsCommand.replace("delete" + " ", ""));
            slotIndexFieldViewAppointments.setStyle(String.valueOf(slotIndex));
            return ;

        }
        else if(viewAppointmentsCommand.compareTo("view") == 0) {

            return ;

        }

        viewAppointmentsCommandLine.setStyle("-fx-border-color: red");

    }

    private boolean selectCommand(String insertCommand){

        String viewAppointmentsField;
        boolean handled = false;

        if(insertCommand.startsWith("select saloon")) {

            viewAppointmentsField = insertCommand.replace("select saloon" + " ", "");
            saloonNameFieldViewAppointments.setText(viewAppointmentsField);
            handled = true;

        }
        else if (insertCommand.startsWith("select date")) {

            datePickerViewAppointments.setValue(LocalDate.of(2022, 1, 46));

            viewAppointmentsField = insertCommand.replace("select date" + " ", "");
            if (manageInsertDate(viewAppointmentsField)) {
                handled = true;
            }
        }
        else if (insertCommand.startsWith("select slot")) {

            viewAppointmentsField = insertCommand.replace("select slot" + " ", "");
            if (isNumeric(viewAppointmentsField)) {
                slotIndexFieldViewAppointments.setText(viewAppointmentsField);
                handled = true;
            }

        }

        return handled;

    }

    private boolean manageInsertDate(String date) {

        boolean dateHandled = false;
        String[] valueOfInsertDate;
        valueOfInsertDate = date.split("-");



        return dateHandled;

    }
}
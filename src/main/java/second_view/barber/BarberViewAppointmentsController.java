package second_view.barber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;

import java.time.DateTimeException;
import java.time.LocalDate;

import static engineering.other_classes.NumericVerify.isNumeric;

public class BarberViewAppointmentsController {

    private static final String SELECT_DATE_COMMAND = "select date";
    private static final String SELECT_SALOON_COMMAND = "select saloon";
    private static final String SELECT_SLOT_COMMAND = "select slot";

    @FXML private TextField viewAppointmentsCommandLine;
    @FXML private TextField saloonNameFieldViewAppointments;
    @FXML private TextField slotIndexFieldViewAppointments;
    @FXML private TextField dateTextField;
    @FXML private Label errorLabel;

    private DatePicker datePickerViewAppointments;

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

        if(insertCommand.startsWith(SELECT_SALOON_COMMAND)) {

            viewAppointmentsField = insertCommand.replace(SELECT_SALOON_COMMAND + " ", "");
            saloonNameFieldViewAppointments.setText(viewAppointmentsField);
            handled = true;

        }
        else if (insertCommand.startsWith(SELECT_DATE_COMMAND)) {

            datePickerViewAppointments = new DatePicker();
            viewAppointmentsField = insertCommand.replace(SELECT_DATE_COMMAND + " ", "");
            if (manageInsertDate(viewAppointmentsField)) {
                handled = true;
                LocalDate date = datePickerViewAppointments.getValue();
                dateTextField.setText(date.toString());
            }

        }
        else if (insertCommand.startsWith(SELECT_SLOT_COMMAND)) {

            viewAppointmentsField = insertCommand.replace(SELECT_SLOT_COMMAND + " ", "");
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

        try{

            datePickerViewAppointments.setValue(LocalDate.of(Integer.parseInt(valueOfInsertDate[0]), Integer.parseInt(valueOfInsertDate[1]), Integer.parseInt(valueOfInsertDate[2])));
            dateHandled = true;
            errorLabel.setText("");

        }catch (DateTimeException e){

            errorLabel.setText("Invalid entered Date!");

        }

        return dateHandled;

    }
}
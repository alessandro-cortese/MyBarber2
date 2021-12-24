package second_view.barber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;

public class BarberViewAppointmentsController {

    @FXML private TextField viewAppointmentsCommandLine;
    @FXML private TextField saloonNameFieldViewAppointments;
    @FXML private TextField dateFieldViewAppointments;
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

            System.out.println("View");
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

            viewAppointmentsField = insertCommand.replace("select date" + " ", "");
            if (manageInsertDate(viewAppointmentsField)) {
                dateFieldViewAppointments.setText(viewAppointmentsField);
                handled = true;
            }
        }
        else if (insertCommand.startsWith("select slot")) {

            viewAppointmentsField = insertCommand.replace("select slot" + " ", "");
            slotIndexFieldViewAppointments.setText(viewAppointmentsField);
            handled = true;

        }

        return handled;

    }

    private boolean manageInsertDate(String date) {

        boolean dateHandled = false;
        String[] valueOfInsertDate;
        valueOfInsertDate = date.split("-");

        if (valueOfInsertDate[1].compareTo("2") == 0 && Integer.parseInt(valueOfInsertDate[2]) <= 28)
            dateHandled = true;
        else if (valueOfInsertDate[1].compareTo("1") == 0 && Integer.parseInt(valueOfInsertDate[2]) <= 31)
            dateHandled = true;
        else if (valueOfInsertDate[1].compareTo("3") == 0 && Integer.parseInt(valueOfInsertDate[2]) <= 31)
            dateHandled = true;
        else if (valueOfInsertDate[1].compareTo("4") == 0 && Integer.parseInt(valueOfInsertDate[2]) <= 30)
            dateHandled = true;
        else if (valueOfInsertDate[1].compareTo("5") == 0 && Integer.parseInt((valueOfInsertDate[2])) <= 31)
            dateHandled = true;
        else if (valueOfInsertDate[1].compareTo("6") == 0 && Integer.parseInt(valueOfInsertDate[2]) <= 30)
            dateHandled = true;
        else if (valueOfInsertDate[1].compareTo("7") == 0 && Integer.parseInt(valueOfInsertDate[2]) <= 31)
            dateHandled = true;
        else if (valueOfInsertDate[1].compareTo("8") == 0 && Integer.parseInt(valueOfInsertDate[2]) <= 31)
            dateHandled = true;
        else if (valueOfInsertDate[1].compareTo("9") == 0 && Integer.parseInt(valueOfInsertDate[2]) <= 30)
            dateHandled = true;
        else if (valueOfInsertDate[1].compareTo("10") == 0 && Integer.parseInt(valueOfInsertDate[2]) <= 31)
            dateHandled = true;
        else if (valueOfInsertDate[1].compareTo("11") == 0 && Integer.parseInt(valueOfInsertDate[2]) <= 30)
            dateHandled = true;
        else if (valueOfInsertDate[1].compareTo("12") == 0 && Integer.parseInt(valueOfInsertDate[2]) <= 31)
            dateHandled = true;

        return dateHandled;

    }
}
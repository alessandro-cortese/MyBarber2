package second_view.client;

import engineering.bean.SaloonBean;
import engineering.time.TimeSlot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import second_view.general.ScreenChanger;

import java.io.IOException;

public class ClientBookAppointmentController {

    @FXML private TextField commandLine ;
    @FXML private ListView serviceListView ;
    @FXML private ListView serviceSelectedListView;
    @FXML private TextField dateText;
    @FXML private TextField saloonName;
    @FXML private TextField hourText;


    @FXML
    public void onCommand(ActionEvent event) throws IOException {
        String commandText = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");


        if (commandText.matches("service add [0-9]+")) {

            return;
        }
        else if (commandText.matches("service remove [0-9]+")) {
            //Rimuove indice da lista dei servizi aggiunti
            System.out.println(commandText.replace("service remove ", ""));
            return ;
        }
        else if (commandText.compareTo("book") == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"il prezzo totale e': "+"17.00$\nVerrai rimandato alla home" );
            alert.showAndWait();
            ButtonType buttonType =alert.getResult();
            String result =buttonType.getText();
            System.out.println(result);
            if(result.compareTo("OK")==0) //vai alla home
                ScreenChanger.getInstance().goToHome(event);
            return;
        }
        else if (commandText.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return;
        }

        commandLine.setStyle("-fx-border-color: red");

    }

    public void injectDateHourSaloonInfo(TimeSlot timeSlotInfo, SaloonBean saloonBean, String date) {
        dateText.setText(date);
        hourText.setText(timeSlotInfo.getFromTime()+" - "+timeSlotInfo.getToTime());
        saloonName.setText(saloonBean.getName());


    }
}

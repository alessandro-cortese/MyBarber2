package second_view.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;

import java.util.concurrent.TimeUnit;

public class ClientBookPreventivePriceGraphicController {

    @FXML
    private TextField CVVField;

    @FXML
    private TextField commandLine;

    @FXML
    private ListView<?> couponListView;

    @FXML
    private TextField creditCardField;

    @FXML
    private TextField dateField;

    @FXML
    private Button enterButton;

    @FXML
    private Label totalPriceLabel;

    @FXML private Label bookLabel;

    @FXML
    void onCommand(ActionEvent event) throws InterruptedException {
        String commandText = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");

        if (commandText.matches("add coupon .*")) {

            return;
        }
        else if (commandText.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return;
        }
        else if(commandText.compareTo("book") == 0){
            bookLabel.setText("Prenotazione avvenuta con successo, controlla la tua posta email!");
        }
        else if(commandText.compareTo("home") == 0){
            ScreenChanger.getInstance().goToHome(event);
            return;
        }
        else if(commandText.matches("remove coupon .*")){

            return;
        }
        else if (commandText.matches("set credit card [0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")){

            return;
        }
        else if (commandText.matches("set expired date \\d{4}-\\d{2}")){
            String date = commandText.replace("set date ", "") ;
            dateField.setText(date);
            return;
        }
        else if(commandText.matches("set cvv code [0-9][0-9][0-9]")){
            String cvv = commandText.replace("set cvv code","");
            CVVField.setText(cvv);
            return;
        }
        else if(commandText.matches("donation to onlus [SN]")){
            return;
        }

        commandLine.setStyle("-fx-border-color: red");

    }

}

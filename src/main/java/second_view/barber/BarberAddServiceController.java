package second_view.barber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;


import java.io.IOException;

public class BarberAddServiceController {

    @FXML private TextField addServiceCommandLine;
    @FXML private TextField nameAddServiceField;
    @FXML private TextField descriptionAddServiceField;
    @FXML private TextField addServicePriceField;
    @FXML private TextField addServiceUsedProductNameField;


    @FXML
    public void onCommand(ActionEvent event) throws IOException {

        String addServiceCommand = addServiceCommandLine.getText();
        addServiceCommandLine.setText("");
        addServiceCommandLine.setStyle(null);


        if(addServiceCommand.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event) ;
            return ;
        }
        else if(addServiceCommand.startsWith("set")){
            if(handlerSetCommand(addServiceCommand)){
                return ;
            }
        }

        addServiceCommandLine.setStyle("-fx-border-color: red");

    }

    private boolean handlerSetCommand(String insertCommand){

        String addServiceNewField = "";
        boolean flag = false;

        if(insertCommand.startsWith("set name")) {
            addServiceNewField = insertCommand.replace("set name" + " ", "") ;
            if (addServiceNewField.compareTo("set name") != 0){
                nameAddServiceField.setText(addServiceNewField) ;
                flag =  true;
            }
        }
        else if(insertCommand.startsWith("set description")) {
            addServiceNewField = insertCommand.replace("set description" + " ", "") ;
            if(addServiceNewField.compareTo("set description") != 0){
                descriptionAddServiceField.setText(addServiceNewField) ;
                flag =  true;
            }
        }
        else if(insertCommand.startsWith("set price")) {
            addServiceNewField = insertCommand.replace("set price" + " ", "") ;
            if(addServiceNewField.compareTo("set price") != 0){
                addServicePriceField.setText(addServiceNewField);
                flag =  true;
            }
        }
        else if(insertCommand.startsWith("set product used")) {
            addServiceNewField = insertCommand.replace("set product used" + " ", "");
            if(addServiceNewField.compareTo("set product used") != 0) {
                addServiceUsedProductNameField.setText(addServiceNewField);
                flag =  true;
            }
        }

        return flag;

    }

}

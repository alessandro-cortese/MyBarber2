package second_view.barber;

import application_controller.AddServiceController;
import engineering.bean.ServiceBean;
import engineering.exception.NegativePriceException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;


import java.io.IOException;

import static engineering.other_classes.NumericVerify.isNumeric;

public class BarberAddServiceController {

    @FXML private TextField addServiceCommandLine;
    @FXML private TextField nameAddServiceField;
    @FXML private TextField descriptionAddServiceField;
    @FXML private TextField addServicePriceField;
    @FXML private TextField addServiceUsedProductNameField;
    @FXML private Label addServiceExceptionLabelSecondView;

    private static final String SET_NAME_COMMAND = "set name" ;
    private static final String SET_DESCRIPTION_COMMAND = "set description" ;
    private static final String SET_PRICE_COMMAND = "set price" ;
    private static final String SET_USED_PRODUCT_COMMAND = "set product used" ;


    @FXML
    public void onCommand(ActionEvent event) throws IOException {

        String addServiceCommand = addServiceCommandLine.getText();
        addServiceExceptionLabelSecondView.setText("");
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
        else if(addServiceCommand.compareTo("add") == 0 && getterTex()){
                try {
                    ServiceBean serviceBean = new ServiceBean(nameAddServiceField.getText(), descriptionAddServiceField.getText(), addServiceUsedProductNameField.getText(), Double.parseDouble(addServicePriceField.getText()));
                    AddServiceController addServiceController = new AddServiceController();
                    //addServiceController.addService(serviceBean);
                }catch(NegativePriceException e){
                    addServiceExceptionLabelSecondView.setText("Insert price is negative!");
                }

                ScreenChanger.getInstance().goToHome(event);
                return ;
        }

        addServiceCommandLine.setStyle("-fx-border-color: red");

    }

    private boolean getterTex(){
        return nameAddServiceField.getText() != null && addServicePriceField.getText() != null && descriptionAddServiceField.getText() != null;
    }

    private boolean handlerSetCommand(String insertCommand){

        String addServiceNewField;
        boolean flag = false;

        if(insertCommand.startsWith(SET_NAME_COMMAND)) {
            addServiceNewField = insertCommand.replace(SET_NAME_COMMAND + " ", "") ;
            if (addServiceNewField.compareTo(SET_NAME_COMMAND) != 0){
                nameAddServiceField.setText(addServiceNewField) ;
                flag =  true;
            }
        }
        else if(insertCommand.startsWith(SET_DESCRIPTION_COMMAND)) {
            addServiceNewField = insertCommand.replace(SET_DESCRIPTION_COMMAND + " ", "") ;
            if(addServiceNewField.compareTo(SET_DESCRIPTION_COMMAND) != 0){
                descriptionAddServiceField.setText(addServiceNewField) ;
                flag =  true;
            }
        }
        else if(insertCommand.startsWith(SET_PRICE_COMMAND)) {
            addServiceNewField = insertCommand.replace(SET_PRICE_COMMAND + " ", "") ;
            if(addServiceNewField.compareTo(SET_PRICE_COMMAND) != 0 && isNumeric(addServiceNewField)){
                addServicePriceField.setText(addServiceNewField);
                flag =  true;
            }
        }
        else if(insertCommand.startsWith(SET_USED_PRODUCT_COMMAND)) {
            addServiceNewField = insertCommand.replace(SET_USED_PRODUCT_COMMAND + " ", "");
            if(addServiceNewField.compareTo(SET_USED_PRODUCT_COMMAND) != 0) {
                addServiceUsedProductNameField.setText(addServiceNewField);
                flag =  true;
            }
        }

        return flag;

    }

}

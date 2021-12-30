package second_view.barber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;

import static engineering.otherclasses.NumericVerify.isNumeric;

public class BarberManageServiceController {

    @FXML private TextField manageServiceCommandLine;
    @FXML private TextField manageServiceNameTextField;
    @FXML private TextField manageServiceDescriptionTextField;
    @FXML private TextField manageServicePriceTextField;
    @FXML private TextField manageServiceNameOfUsedProductTextField;

    @FXML
    public void onCommand(ActionEvent event) {

        String manageServiceCommandLineLocal = manageServiceCommandLine.getText();
        manageServiceCommandLine.setText("");
        manageServiceCommandLine.setStyle(null);

        if(manageServiceCommandLineLocal.compareTo("back") == 0){
            ScreenChanger.getInstance().onBack(event);
            return ;
        }
        else if(manageServiceCommandLineLocal.startsWith("select service")) {
            String nameOfSelectedService = manageServiceCommandLineLocal.replace("select service" + " ", "");
            manageServiceNameTextField.setText(nameOfSelectedService);
            return ;
        }
        else if(manageServiceCommandLineLocal.startsWith("overwrite")) {
            if(overwriteHandler(manageServiceCommandLineLocal)) {
                return ;
            }
        }
        else if(manageServiceCommandLineLocal.compareTo("modify") == 0){
            System.out.println("Modify");
            return;
            // TODO modify this method when you know the DAO classes
        }
        else if(manageServiceCommandLineLocal.compareTo("delete") == 0) {
            System.out.println("Delete");
            return;
            // TODO modify this method when you know the DAO classes
        }

        manageServiceCommandLine.setStyle("-fx-border-color: red");
    }

    private boolean overwriteHandler(String command) {

        boolean flag = false;
        String newManageServiceField;

        if(command.startsWith("overwrite name")) {

            newManageServiceField = command.replace("overwrite name" + " ", "");
            manageServiceNameTextField.setText(newManageServiceField);
            flag = true;

        }
        else if(command.startsWith("overwrite price")) {
            newManageServiceField = command.replace("overwrite price" + " ", "");
            if(isNumeric(newManageServiceField)) {
                manageServicePriceTextField.setText(newManageServiceField);
                flag = true;
            }
        }
        else if(command.startsWith("overwrite description")) {
            newManageServiceField = command.replace("overwrite descriptio" + " ", "");
            manageServiceDescriptionTextField.setText(newManageServiceField);
            flag = true;
        }
        else if(command.startsWith("overwrite name of used product")) {
            newManageServiceField = command.replace("overwrite name of used product" + " ", "");
            manageServiceNameOfUsedProductTextField.setText(newManageServiceField);
            flag = true;
        }

        return flag;
    }

}

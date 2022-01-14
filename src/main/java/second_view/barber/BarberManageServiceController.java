package second_view.barber;

import application_controller.ManageServiceController;
import engineering.bean.ServiceBean;
import engineering.exception.InsertNegativePriceException;
import engineering.exception.NegativePriceException;
import first_view.list_cell_factories.ServiceListCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static engineering.other_classes.NumericVerify.isNumeric;

public class BarberManageServiceController implements Initializable {

    @FXML private TextField manageServiceCommandLine;
    @FXML private TextField manageServiceNameTextField;
    @FXML private TextField manageServiceDescriptionTextField;
    @FXML private TextField manageServicePriceTextField;
    @FXML private TextField manageServiceNameOfUsedProductTextField;
    @FXML private TextField manageServiceSlotIndexTextField;
    @FXML private ListView<ServiceBean> manageServiceListView;

    private List<ServiceBean> serviceBeanList;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        ManageServiceController manageServiceController = new ManageServiceController();
        manageServiceController.setUserBean(ScreenChanger.getInstance().getLoggedUser());

        try{
            serviceBeanList = manageServiceController.getAllService();
        } catch (InsertNegativePriceException e) {
            e.printStackTrace();
        }


        manageServiceListView.setCellFactory(param-> new ServiceListCellFactory(true));

        manageServiceListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            assert newValue != null;
            manageServiceNameTextField.setText(newValue.getNameInfo());
            manageServiceDescriptionTextField.setText(newValue.getDescriptionInfo());
            manageServiceNameOfUsedProductTextField.setText(newValue.getNameOfUsedProductInfo());
            manageServicePriceTextField.setText(Double.toString(newValue.getPriceInfo()));
            manageServiceSlotIndexTextField.setText(Integer.toString(serviceBeanList.indexOf(newValue)));
        });

        manageServiceListView.getItems().clear();
        manageServiceListView.setItems(FXCollections.observableList(serviceBeanList));
    }


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
            return;
        }
        else if(manageServiceCommandLineLocal.compareTo("delete") == 0) {
            return;
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

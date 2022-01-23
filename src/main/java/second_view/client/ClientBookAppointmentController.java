package second_view.client;

import application_controller.BookingController;
import engineering.bean.SaloonBean;
import engineering.bean.ServiceBean;
import engineering.bean.TimeSlotBean;
import engineering.exception.InvalidIndexSelected;
import engineering.exception.ServiceNotFoundException;
import first_view.list_cell_factories.ServiceListCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import second_view.general.ScreenChanger;

import java.io.IOException;
import java.util.List;

public class ClientBookAppointmentController {

    private static final String CLIENT_SERVICE_ITEM ="first_view/list_item/client_service_item.fxml";

    @FXML private TextField commandLine ;
    @FXML private ListView<ServiceBean> serviceListView ;
    @FXML private ListView serviceSelectedListView;
    @FXML private TextField dateText;
    @FXML private TextField saloonName;
    @FXML private TextField hourText;
    
    private List<ServiceBean> serviceBeanList;
    private boolean secondView;
    private double totalPrice = 0.0;


    @FXML
    public void onCommand(ActionEvent event) throws IOException {
        String commandText = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");


        if (commandText.matches("select service [0-9]+")) {
            String index = commandText.replace("select service ", "");
            int in = Integer.parseInt(index);
            verifyIndexSlotTime(in);
            return;
        }
        else if (commandText.matches("remove service [0-9]+")) {
            //Rimuove indice da lista dei servizi aggiunti
            commandText.replace("remove service ", "");
            return ;
        }
        else if (commandText.compareTo("book") == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"il prezzo totale e': "+"17.00$\nVerrai rimandato alla home" );
            alert.showAndWait();
            ButtonType buttonType =alert.getResult();
            String result =buttonType.getText();
            if(result.compareTo("OK")==0)
                ScreenChanger.getInstance().goToHome(event);
            return;
        }
        else if (commandText.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return;
        }

        commandLine.setStyle("-fx-border-color: red");

    }

    private void verifyIndexSlotTime(int in) {
       /* boolean flag = false;
        for (ServiceBean serviceBean : serviceListView.getItems()) {
            int i = serviceBean.getIndex();
            int ind = Integer.parseInt(index);
            if (ind == i) {
                flag = true;
                timeSlotInfo = new TimeSlotBean();
                timeSlotInfo.setFromTime(timeSlot.getFromTime());
                timeSlotInfo.setToTime(timeSlot.getToTime());
                timeSlotInfo.setSeatAvailable(timeSlotInfo.getSeatAvailable());
                break;
            }
        }
        if (!flag)
            throw new InvalidIndexSelected("slot time non valido");*/
    }

    public void injectDateHourSaloonInfo(TimeSlotBean timeSlotInfo, SaloonBean saloonBean, String date) throws ServiceNotFoundException {
        secondView= true;
        serviceListView.setCellFactory(param -> new ServiceListCellFactory(CLIENT_SERVICE_ITEM,secondView));
        
        dateText.setText(date);
        hourText.setText(timeSlotInfo.getFromTime()+" - "+timeSlotInfo.getToTime());
        saloonName.setText(saloonBean.getName());
        BookingController bookingController = new BookingController();
        serviceBeanList = bookingController.SearchServices(saloonBean);
        serviceListView.setItems(FXCollections.observableList(serviceBeanList));






    }
}

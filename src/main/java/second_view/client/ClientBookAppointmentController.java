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
import java.util.ArrayList;
import java.util.List;

public class ClientBookAppointmentController {

    private static final String CLIENT_SERVICE_ITEM ="first_view/list_item/client_service_item.fxml";

    @FXML private TextField commandLine ;
    @FXML private ListView<ServiceBean> serviceListView ;
    @FXML private ListView<ServiceBean> serviceSelectedListView;
    @FXML private TextField dateText;
    @FXML private TextField saloonName;
    @FXML private TextField hourText;
    
    private List<ServiceBean> serviceBeanList;
    private boolean secondView;
    private List<ServiceBean> servicesBeanSelected;


    @FXML
    public void onCommand(ActionEvent event) throws IOException, InvalidIndexSelected {
        String commandText = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");


        if (commandText.matches("select service [0-9]+")) {
            String index = commandText.replace("select service ", "");
            int in = Integer.parseInt(index);
            verifyIndexSlotTime(in,true);
            ServiceBean serviceBean = serviceListView.getItems().get(in);
            servicesBeanSelected.add(serviceBean);
            serviceSelectedListView.setItems(FXCollections.observableList(servicesBeanSelected));
            return;
        }
        else if (commandText.matches("remove service [0-9]+")) {
            //Rimuove indice da lista dei servizi aggiunti
            String index = commandText.replace("remove service ", "");
            int in = Integer.parseInt(index);
            verifyIndexSlotTime(in,false);
            serviceSelectedListView.getItems().remove(in);
            return ;
        }
        else if (commandText.compareTo("book") == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Prenotazione avvenuta con successo! controlla l'email che ti abbiamo spedito!\nVerrai rimandato alla home" );
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

    private void verifyIndexSlotTime(int in, boolean rs) throws InvalidIndexSelected {
        boolean flag = false;
        ListView<ServiceBean> listView;
        if(rs)
            listView = serviceListView;
        else
            listView = serviceSelectedListView;

            if (in <= listView.getItems().size())
                flag = true;
            if (!flag)
                throw new InvalidIndexSelected("slot time non valido");
    }

    public void injectDateHourSaloonInfo(TimeSlotBean timeSlotInfo, SaloonBean saloonBean, String date) throws ServiceNotFoundException {
        secondView= true;
        servicesBeanSelected = new ArrayList<>();
        serviceListView.setCellFactory(param -> new ServiceListCellFactory(CLIENT_SERVICE_ITEM,secondView));
        serviceSelectedListView.setCellFactory(param -> new ServiceListCellFactory(CLIENT_SERVICE_ITEM,secondView));
        dateText.setText(date);
        hourText.setText(timeSlotInfo.getFromTime()+" - "+timeSlotInfo.getToTime());
        saloonName.setText(saloonBean.getName());
        BookingController bookingController = new BookingController();
        serviceBeanList = bookingController.searchServices(saloonBean);
        serviceListView.setItems(FXCollections.observableList(serviceBeanList));






    }
}

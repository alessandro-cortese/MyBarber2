package application_controller.graphic;

import application_controller.BookingController;
import engineering.bean.SaloonBean;
import engineering.bean.ServiceBean;
import engineering.time.TimeSlot;
import first_view.list_cell_factories.ServiceListCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ScheduleBookingGraphicController{

    private static final String SERVICE_ITEM = "first_view/list_item/barber_service_list_item.fxml";


    private String saloonAddress;
    private String city;

    @FXML
    private Button bookedButton;

    @FXML
    private ImageView saloonImage;

    @FXML
    private ListView serviceListView;
    @FXML
    private Label dateLabel;

     @FXML
     private Label hourLabelInit;
     @FXML
     private Label hourLabelFinal;

     @FXML
     private Label nameSaloonLabel;
     @FXML
     private Label citySaloonLabel;
     @FXML
     private Label addressSaloonLabel;
     @FXML
     private Label phoneSaloonLabel;


    @FXML
    private ListView<String> serviceSelectedListView;
    private String nameSal;
    private String saloonAddr;
    private String saloonCity;
    private String saloonPhone;
    private int seatNumber;
    private static final String CLIENT_BOOKED_SCREEN_NAME = "first_view/client/client_booked.fxml";
    private SaloonBean saloonInfo;
    private List<TimeSlot> servicesList;
    private List<ServiceBean> servicesSaloonList;
    private TimeSlot timeSlotInfo;
    private LocalDate dateBooking;

    @FXML
    void onButtonSaloonClicked(ActionEvent event) throws IOException{
        Button sourceButton = (Button) event.getSource();
        FXMLLoader fxmlLoaderNode = new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_BOOKED_SCREEN_NAME));
        Parent newCenterNode = fxmlLoaderNode.load();
        Scene myScene = sourceButton.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        borderPane.setCenter(newCenterNode);
    }

    @FXML
    void selectService(ActionEvent event) throws IOException {

    }


    public void InjectServiceSaloon(){
        serviceListView.setCellFactory(param -> new ServiceListCellFactory());

        BookingController bookingController = new BookingController();
        servicesSaloonList = bookingController.SearchServices(saloonInfo);
        if(servicesSaloonList.isEmpty()){
            System.out.println("non funge");
        }
        serviceListView.getItems().clear();
        serviceListView.setItems(FXCollections.observableList(servicesSaloonList));
        phoneSaloonLabel.setText(saloonInfo.getPhone());
        nameSaloonLabel.setText(saloonInfo.getName());
        citySaloonLabel.setText(saloonInfo.getCity());
        addressSaloonLabel.setText(saloonInfo.getAddress());
        hourLabelInit.setText(String.valueOf(timeSlotInfo.getFromTime()));
        hourLabelFinal.setText(String.valueOf(timeSlotInfo.getToTime()));
        dateLabel.setText(String.valueOf(dateBooking));

        }


    public void displaySaloon(SaloonBean saloonBean, TimeSlot timeSlot, LocalDate date) {
        saloonInfo = new SaloonBean();
        timeSlotInfo = new TimeSlot();
        this.saloonInfo.setName(saloonBean.getName());
        this.saloonInfo.setAddress(saloonBean.getAddress());
        this.saloonInfo.setCity(saloonBean.getCity());
        this.saloonInfo.setPhone(saloonBean.getPhone());
        this.saloonInfo.setSeatNumber(saloonBean.getSeatNumber());
        //this.timeSlotInfo.setFromTime(timeSlot.getFromTime());
        //this.timeSlotInfo.setToTime(timeSlot.getToTime());
        this.dateBooking = date;
    }

}


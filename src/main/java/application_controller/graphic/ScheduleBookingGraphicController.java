package application_controller.graphic;

import application_controller.BookingController;
import engineering.bean.SaloonBean;
import engineering.bean.ServiceBean;
import engineering.exception.InsertNegativePriceException;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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


    private static final String CLIENT_BOOKED_SCREEN_NAME = "first_view/client/client_booked.fxml";
    private static final String CLIENT_SERVICE_ITEM ="first_view/list_item/client_service_item.fxml";
    private SaloonBean saloonInfo;
    private List<ServiceBean> servicesSaloonList;
    private TimeSlot timeSlotInfo;
    private LocalDate dateBooking;

    @FXML
    private Label indexLabel;

    @FXML
    private Label indexServiceOfListLabel;

    @FXML
    private Label serviceNameLabel;

    @FXML
    private  Label servicePriceLabel;

    private ServiceBean service;
    private static List<ServiceBean> serviceListSelected;

    public ScheduleBookingGraphicController(){
        serviceListSelected = new ArrayList<>();
    }

    @FXML
    void selectServiceOnListView(MouseEvent event) throws InsertNegativePriceException {
        service = new ServiceBean();
        service.setNameInfo(serviceNameLabel.getText());
        service.setPriceInfo(Double.parseDouble(servicePriceLabel.getText()));
        serviceListSelected.add(service);
        System.out.println("servizio selezionato" + serviceNameLabel.getText());
        System.out.println("size " + serviceListSelected.size());

    }

    @FXML
    void onButtonSaloonClicked(ActionEvent event) throws IOException{
        Button sourceButton = (Button) event.getSource();
        FXMLLoader fxmlLoaderNode = new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_BOOKED_SCREEN_NAME));
        Parent newCenterNode = fxmlLoaderNode.load();
        Scene myScene = sourceButton.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        borderPane.setCenter(newCenterNode);
        BookedGraphicController bookedGraphicController = fxmlLoaderNode.getController();
        bookedGraphicController.injectServicesList(serviceListSelected);


    }





    public void InjectServiceSaloon(){
        serviceListView.setCellFactory(param -> new ServiceListCellFactory(CLIENT_SERVICE_ITEM));

        BookingController bookingController = new BookingController();
        servicesSaloonList = bookingController.SearchServices(saloonInfo);
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
        this.timeSlotInfo.setFromTime(timeSlot.getFromTime());
        this.timeSlotInfo.setToTime(timeSlot.getToTime());
        this.dateBooking = date;
    }

}


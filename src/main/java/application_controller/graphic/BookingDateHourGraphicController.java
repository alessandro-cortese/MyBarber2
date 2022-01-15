package application_controller.graphic;

import application_controller.BookingController;
import engineering.bean.BookingBean;
import engineering.bean.SaloonBean;
import engineering.time.ScheduleTime;
import engineering.time.TimeSlot;
import first_view.list_cell_factories.SaloonListCellFactory;
import first_view.list_cell_factories.SaloonTimeSlotsListCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class BookingDateHourGraphicController {
        private static final String CLIENT_SALOON_CENTER_SCREEN_NAME = "first_view/client/client_saloon_center.fxml";
        private String  saloonName;
        private String saloonCity;
        private String saloonAddress;
        private String saloonPhone;
        private int seatNumber;
        private List<TimeSlot> timeSlotList;
        @FXML
        private Button ConfirmDateHourButton;

        @FXML
        private TextField hourTextField;

        @FXML
        private Label exceptionDateLabel;

        @FXML
        private Label exceptionHourLabel;

        @FXML
        private Label generalException;

        @FXML
        private DatePicker dateBooking;

        @FXML
        private SplitMenuButton timeSlotSplitButton;
        @FXML
        private Label  saloonNameLabel;


        @FXML
        private ImageView saloonImage;

        @FXML
        private ListView timeSlotListView;
        @FXML
        private Label HourLabel;

        private SaloonBean timeSlotSaloon;

        private Time slotTime;


        @FXML
        void onButtonClicked(ActionEvent event) throws IOException {
                Button sourceButton = (Button) event.getSource();
                FXMLLoader fxmlLoaderNode = new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_SALOON_CENTER_SCREEN_NAME));
                //ScheduleBookingGraphicController scheduleBookingGraphicController = fxmlLoader.getController();
               // scheduleBookingGraphicController.displaySaloon(new SaloonBean(saloonName, saloonAddress, saloonCity, saloonPhone,slotTime, seatNumber));
                Parent newCenterNode = fxmlLoaderNode.load();
                Scene myScene = sourceButton.getScene();
                BorderPane borderPane = (BorderPane) myScene.getRoot();
                borderPane.setCenter(newCenterNode);
        }

        @FXML
        public void selectSlotTime(){
                LocalDate date = dateBooking.getValue();
                BookingController bookingController = new BookingController();

        }


        @FXML
        public void ConfirmHour(ActionEvent event) throws IOException, ParseException { //qui chiamo la BookingDAO


        }

        public void display(SaloonBean saloonBean) { //setto la saloonBean del sottoscritto controller
                timeSlotSaloon = new SaloonBean();
                this.timeSlotSaloon.setName(saloonBean.getName());
                this.timeSlotSaloon.setAddress(saloonBean.getAddress());
                this.timeSlotSaloon.setCity(saloonBean.getCity());
                this.timeSlotSaloon.setPhone(saloonBean.getPhone());
                this.timeSlotSaloon.setSeatNumber(saloonBean.getSeatNumber());
                this.timeSlotSaloon.setSlotTime(saloonBean.getSlotTime());
                this.timeSlotSaloon.setCloseAfternoonTimeInfo(saloonBean.getCloseAfternoonTimeInfo());
                this.timeSlotSaloon.setCloseMorningTimeInfo(saloonBean.getCloseMorningTimeInfo());
                this.timeSlotSaloon.setOpeningAfternoonTimeInfo(saloonBean.getOpeningAfternoonTimeInfo());
                this.timeSlotSaloon.setOpeningMorningTimeInfo(saloonBean.getOpeningMorningTimeInfo());
                this.timeSlotSaloon.setNumberOfAfternoonSlotsInfo(saloonBean.getNumberOfAfternoonSlotsInfo());
                this.timeSlotSaloon.setNumberOfMorningSlotsInfo(saloonBean.getNumberOfMorningSlotsInfo());
        }

        public void injectSaloonIntoDateHour() {
                timeSlotListView.setCellFactory(param -> new SaloonTimeSlotsListCellFactory());

                searchTimeSlots();//recupero gli slot Time dal db

                timeSlotListView.getItems().clear();
                timeSlotList = new ScheduleTime(timeSlotSaloon).timeSlotsIstance();
                timeSlotListView.setItems(FXCollections.observableList(timeSlotList));
                saloonNameLabel.setText(timeSlotSaloon.getName());

        }

        private void searchTimeSlots() {
                SaloonBean saloonBean = new SaloonBean(timeSlotSaloon.getName());
                BookingController bookingController = new BookingController();
                timeSlotSaloon = bookingController.searchTimeSlots(saloonBean);//bean che ritona gli estremi mattina e pomeriggio e poi devo calcolare gli slot time e metterli in timeSlotList
        }
}


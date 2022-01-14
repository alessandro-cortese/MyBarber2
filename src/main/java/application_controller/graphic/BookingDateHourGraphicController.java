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

public class BookingDateHourGraphicController implements Initializable {
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
        private Label saloonNameLabel;

        @FXML
        private ImageView saloonImage;

        @FXML
        private ListView timeSlotListView;

        private Time slotTime;
        private SaloonBean timeSlotSaloon;

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

                SaloonBean saloonBean = new SaloonBean(saloonName);
                BookingController bookingController = new BookingController();
                bookingController.searchTimeSlots(saloonBean);


        }




        public void display(SaloonBean saloonBean) {

                this.saloonName = saloonBean.getName();
                this.saloonAddress =saloonBean.getAddress();
                this.saloonCity = saloonBean.getCity();
                this.saloonPhone =saloonBean.getPhone();
                this.seatNumber = saloonBean.getSeatNumber();
                this.slotTime = saloonBean.getSlotTime();

                saloonNameLabel.setText(saloonName);
        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                timeSlotListView.setCellFactory(param -> new SaloonTimeSlotsListCellFactory());
                System.out.println(saloonName+1);
                searchTimeSlots();//recupero gli slot Time dal db
                timeSlotListView.getItems().clear();

                timeSlotList = new ScheduleTime(timeSlotSaloon).timeSlotsIstance();
                timeSlotListView.setItems(FXCollections.observableList(timeSlotList));

        }

        private void searchTimeSlots() {
                SaloonBean saloonBean = new SaloonBean(saloonName);
                System.out.println(saloonName);
                BookingController bookingController = new BookingController();
                timeSlotSaloon = bookingController.searchTimeSlots(saloonBean);//bean che ritona gli estremi mattina e pomeriggio e poi devo calcolare gli slot time e metterli in timeSlotList
        }
}


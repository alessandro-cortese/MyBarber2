package application_controller.graphic;

import application_controller.BookingController;
import engineering.bean.SaloonBean;
import engineering.bean.TimeSlotBean;
import engineering.exception.ServiceNotFoundException;
import engineering.time.TimeSlot;
import first_view.list_cell_factories.SaloonTimeSlotsListCellFactory;
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
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

public class BookingDateHourGraphicController {

        private static final String CLIENT_SALOON_CENTER_SCREEN_NAME = "first_view/client/client_saloon_center.fxml";

        private TimeSlotBean timeSlotBean;

        @FXML
        private Button confirmDateHourButton;

        @FXML
        private Label exceptionDateLabel;

        @FXML
        private Label exceptionHourLabel;

        @FXML
        private DatePicker dateBooking;

        @FXML
        private Label  saloonNameLabel;


        @FXML
        private ImageView saloonImage;

        @FXML
        private ListView<TimeSlotBean> timeSlotListView;
        @FXML
        private Label hourLabel;

        private List<TimeSlotBean> timeSlotSaloon;

        @FXML
        private Label initTime;
        @FXML
        private Label finalTime;
        @FXML
        private Label seatNumberLabel;

        private SaloonBean timeSlotSaloonInfo;
        private LocalDate date;
        public BookingDateHourGraphicController(){
                timeSlotListView = new ListView<>();
        }

        @FXML
        void onButtonClicked(ActionEvent event) throws IOException, ServiceNotFoundException {
                Button sourceButton = (Button) event.getSource();
                FXMLLoader fxmlLoaderNode = new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_SALOON_CENTER_SCREEN_NAME));
                Parent newCenterNode = fxmlLoaderNode.load();
                Scene myScene = sourceButton.getScene();
                BorderPane borderPane = (BorderPane) myScene.getRoot();
                borderPane.setCenter(newCenterNode);
                ScheduleBookingGraphicController scheduleBookingGraphicController = fxmlLoaderNode.getController();

                scheduleBookingGraphicController.displaySaloon(timeSlotSaloonInfo, timeSlotBean,date );
                scheduleBookingGraphicController.injectServiceSaloon();
        }


        @FXML
        public void confirmHour(ActionEvent event) {
                date = dateBooking.getValue();

        }



        public void display(SaloonBean saloonBean) {
                timeSlotSaloonInfo = new SaloonBean();

                this.timeSlotSaloonInfo.setName(saloonBean.getName());
                this.timeSlotSaloonInfo.setCity(saloonBean.getCity());
                this.timeSlotSaloonInfo.setPhone(saloonBean.getPhone());
                this.timeSlotSaloonInfo.setAddress(saloonBean.getAddress());
        }

        public void injectSaloonIntoDateHour() {
                timeSlotListView.setCellFactory(param -> new SaloonTimeSlotsListCellFactory(true));

                searchTimeSlots();

                timeSlotListView.setItems(FXCollections.observableList(timeSlotSaloon));

        }

        private void searchTimeSlots() {
                SaloonBean saloonBean = new SaloonBean(timeSlotSaloonInfo.getName());
                saloonNameLabel.setText(timeSlotSaloonInfo.getName());

                BookingController bookingController = new BookingController();
                timeSlotSaloon = bookingController.searchTimeSlots(saloonBean);//FAR RITORNARE PURE GLI I SEAT TIME
        }

        @FXML
        public void slotTimeSelected(){
                timeSlotBean = timeSlotListView.getSelectionModel().getSelectedItem();
        }

}


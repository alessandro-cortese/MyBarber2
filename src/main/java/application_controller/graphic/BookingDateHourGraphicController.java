package application_controller.graphic;

import application_controller.BookingController;
import engineering.bean.SaloonBean;
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

public class BookingDateHourGraphicController {
        private static final String CLIENT_SALOON_CENTER_SCREEN_NAME = "first_view/client/client_saloon_center.fxml";
        private String  saloonName;
        private String saloonCity;
        private String saloonAddress;
        private String saloonPhone;
        private int seatNumber;
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
                BookingController.retrieveSlotTime(date);
        }


        @FXML
        public void confirmDateHour(ActionEvent event) throws IOException, ParseException { //qui chiamo la BookingDAO
               String bookingTime = hourTextField.getText()+":00";
                LocalDate date = dateBooking.getValue();
                Time time = Time.valueOf(bookingTime);
                //BookingBean bookingBean = new BookingBean(time, date);
                //BookingController bookingController = new BookingController().VerifyBooking(bookingBean);
                System.out.println(date);
                System.out.println(time);

        }

        public void display(SaloonBean saloonBean) {

                this.saloonName = saloonBean.getName();
                this.saloonAddress =saloonBean.getAddress();
                this.saloonCity = saloonBean.getCity();
                this.saloonPhone =saloonBean.getPhone();
                this.seatNumber = saloonBean.getSeatNumber();
                this.slotTime = saloonBean.getSlotTime();
        }
}


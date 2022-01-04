package applicationController.graphic;

import engineering.bean.SaloonBean;
import first_view.pickers.TimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
        private Label saloonNameLabel;

        @FXML
        private ImageView saloonImage;

        @FXML
        void OnButtonClicked(ActionEvent event) throws IOException {
                Button sourceButton = (Button) event.getSource();
                Parent newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_SALOON_CENTER_SCREEN_NAME))).load();
                Scene myScene = sourceButton.getScene();
                BorderPane borderPane = (BorderPane) myScene.getRoot();
                borderPane.setCenter(newCenterNode);
        }

        @FXML
        public void onChangeTime(MouseEvent event) throws IOException {
                ((TextField) event.getSource()).setText((new TimePicker(0, 24)).getTime());
        }


        @FXML
        public void ConfirmDateHour(ActionEvent event) throws IOException, ParseException { //qui chiamo la BookingDAO
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
        }
}


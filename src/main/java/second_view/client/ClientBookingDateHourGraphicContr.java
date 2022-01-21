package second_view.client;

import application_controller.BookingController;
import engineering.bean.SaloonBean;
import engineering.bean.TimeSlotBean;
import engineering.exception.InvalidTimeSlot;
import engineering.exception.SaloonNotFoundException;
import engineering.time.ScheduleTime;
import engineering.time.TimeSlot;
import first_view.ObservableListNode;
import first_view.list_cell_factories.SaloonTimeSlotsListCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import second_view.general.ScreenChanger;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;


public class ClientBookingDateHourGraphicContr{

    public static final String CLIENT_SLOTTIME_ITEM = "first_view/list_item/client_slotTime_item.fxml";

    @FXML
    private TextField commandLine;

    @FXML
    private Button enterButton;

    @FXML
    private Label initTime;
    @FXML
    private Label finalTime;
    @FXML
    private Label indexLabel;
    @FXML
    private Label seatNumberLabel;

    @FXML
    private ListView<TimeSlot> hourListView;

    @FXML
    private TextField slotTimeField;

    @FXML
    private TextField dateField;
    @FXML
    private  TextArea descriptionTextArea;

    @FXML
    private TextField saloonName;

    @FXML
    private TextField image;
    private SaloonBean saloonBean;
    private SaloonBean timeSlotSaloon;
    private List<TimeSlot> timeSlotList;
    private TimeSlot timeSlot;
    private String index;
    private TimeSlot timeSlotInfo;

    @FXML
    void onCommand(ActionEvent event) throws IOException, InvalidTimeSlot {
        String command = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");

        if (command.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return;
        }
        else if (command.matches("set date \\d{4}-\\d{2}-\\d{2}")){
            String date = command.replace("set date ", "") ;
            dateField.setText(date);
            return;
        }
        else if (command.matches("select slot time [0-9]+")) {
            index = command.replace("select slot time ", "");
            int in = Integer.parseInt(index);
            System.out.println(in);
            try {
                verifyIndexSlotTime();
            } catch (Exception e) {
                InvalidTimeSlot e1 = new InvalidTimeSlot(e.getMessage());
                throw e1;
            }
            slotTimeField.setText(index);
            return ;
        }
        else if (command.compareTo("confirm") == 0) { //manda una notifica al controllore per ottenere le informazioni necessarie

            String date = dateField.getText();

            BookingController bookingController = new BookingController();
            //bookingController.checkDateHour(timeSlotBean); DA FINIRE
            return ;
        }
        else if (command.compareTo("continue booking") == 0) {
            ScreenChanger.getInstance().changeScreen(event, ScreenChanger.CLIENT_BOOK_APPOINTMENT_SCREEN);
            return ;
        }

        commandLine.setStyle("-fx-border-color: red");
    }

    private void verifyIndexSlotTime() throws Exception {
        boolean flag = false;
        for (TimeSlot timeSlot : hourListView.getItems()) {
            int i = timeSlot.getIndex();
            int ind = Integer.parseInt(index);
            if (ind == i) {
                flag = true;
                timeSlotInfo = new TimeSlot();
                timeSlotInfo.setFromTime(timeSlot.getFromTime());
                timeSlotInfo.setToTime(timeSlot.getToTime());
                timeSlotInfo.setSeatAvailable(timeSlotInfo.getSeatAvailable());
                break;
            }
        }
        if (!flag)
            throw new Exception("slot time non valido");
    }

    public void InjectSaloonInfo(SaloonBean saloonBeanInfo) {
        saloonBean = new SaloonBean();
        saloonBean.setName(saloonBeanInfo.getName());
        saloonBean.setCity(saloonBeanInfo.getCity());
        saloonBean.setAddress(saloonBeanInfo.getAddress());
        saloonBean.setPhone(saloonBeanInfo.getPhone());

        hourListView.setCellFactory(param -> new SaloonTimeSlotsListCellFactory());
        SaloonBean saloonBean = new SaloonBean(saloonBeanInfo.getName());
        saloonName.setText(saloonBeanInfo.getName());

        BookingController bookingController = new BookingController();
        timeSlotSaloon = bookingController.searchTimeSlots(saloonBean);//FAR RITORNARE PURE GLI I SEAT TIME
        timeSlotList = new ScheduleTime(timeSlotSaloon).CreateSlotTime();
        int i=0;
        for(TimeSlot timeSlot : timeSlotList){
            timeSlot.setIndex(i);
            i++;
        }

        hourListView.setItems(FXCollections.observableList(timeSlotList));
    }

}


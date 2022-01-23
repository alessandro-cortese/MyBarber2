package second_view.client;

import application_controller.BookingController;
import engineering.bean.BookingBean;
import engineering.bean.SaloonBean;
import engineering.bean.TimeSlotBean;
import engineering.exception.InvalidIndexSelected;
import engineering.exception.SaloonNotFoundException;
import engineering.time.TimeSlot;
import first_view.list_cell_factories.SaloonTimeSlotsListCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import second_view.general.ScreenChanger;

import java.io.IOException;
import java.sql.Date;
import java.util.List;


public class ClientBookingDateHourGraphicContr{

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
    private ListView<TimeSlotBean> hourListView;

    @FXML
    private TextField slotTimeField;

    @FXML
    private TextField dateField;

    @FXML
    private  TextArea descriptionTextArea;

    @FXML
    private TextField saloonName;

    private SaloonBean saloonBean;
    private List<TimeSlotBean> timeSlotSaloon;
    private List<TimeSlotBean> timeSlotList;
    private String index;
    private TimeSlotBean timeSlotInfo;
    private String date;

    @FXML
    void onCommand(ActionEvent event) throws IOException {
        String command = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");
        try {

            if (command.compareTo("back") == 0) {
                ScreenChanger.getInstance().onBack(event);
                return;
            } else if (command.matches("set date \\d{4}-\\d{2}-\\d{2}")) {
                String date = command.replace("set date ", "");
                dateField.setText(date);
                return;
            } else if (command.matches("select slot time [0-9]+")) {
                index = command.replace("select slot time ", "");
                int in = Integer.parseInt(index);
                System.out.println(in);
                verifyIndexSlotTime();
                slotTimeField.setText(index);
                return;

            } else if (command.compareTo("confirm") == 0) { //manda una notifica al controllore per ottenere le informazioni necessarie
                date = dateField.getText();
                if(date =="")
                    throw new SaloonNotFoundException("Inserire la data correttamente ");
                Date dateBook = Date.valueOf(date);

                BookingController bookingController = new BookingController();
                BookingBean bookingBean = new BookingBean(saloonBean.getName(), dateBook);

                bookingController.checkDateHour(bookingBean);
                return;
            } else if (command.compareTo("continue booking") == 0) {
                ClientBookAppointmentController clientBookAppointmentController = (ClientBookAppointmentController) ScreenChanger.getInstance().changeScreen(event, ScreenChanger.CLIENT_BOOK_APPOINTMENT_SCREEN);
                clientBookAppointmentController.injectDateHourSaloonInfo(timeSlotInfo,saloonBean,date);
                return;
            }
        } catch (InvalidIndexSelected e) {
            e.printStackTrace();
        } catch (SaloonNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        commandLine.setStyle("-fx-border-color: red");
    }

    private void verifyIndexSlotTime() throws InvalidIndexSelected {
        boolean flag = false;
        for (TimeSlotBean timeSlot : hourListView.getItems()) {
            int i = timeSlot.getIndex();
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
            throw new InvalidIndexSelected("slot time non valido");
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

        int i=0;
        for(TimeSlotBean timeSlot : timeSlotSaloon){
            timeSlot.setIndex(i);
            i++;
        }
        hourListView.setItems(FXCollections.observableList(timeSlotSaloon));
    }



}


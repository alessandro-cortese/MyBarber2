package first_view.barber;

import application_controller.BarberSeeAppointmentsController;
import engineering.bean.BookingBean;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.util.List;

public class BarberListAppointmentsController {

    @FXML private ListView<Node> appointmentsListView;
    @FXML private TextField saloonText;
    @FXML private Button confirmButton;
    @FXML private DatePicker date;

    private static final String BARBER_APPOINTMENTS_LIST_ITEM = "first_view/list_item/barber_appointments_item.fxml";




    @FXML
    public void onConfirm(ActionEvent event){
        String saloonName = saloonText.getText();
        Date dateBook = Date.valueOf(date.getValue());
        BookingBean bookingBean = new BookingBean(saloonName,dateBook);
        BarberSeeAppointmentsController barberSeeAppointmentsController = new BarberSeeAppointmentsController();
        List<BookingBean> bookingBeanList = barberSeeAppointmentsController.retrieveAppointment(bookingBean);

        appointmentsListView.setItems(FXCollections.observableList(bookingBeanList));




    }




}


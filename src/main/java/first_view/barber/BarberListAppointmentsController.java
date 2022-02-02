package first_view.barber;

import application_controller.BarberSeeAppointmentsController;
import engineering.bean.BookingBean;
import engineering.exception.BookingNotFoundExcption;
import engineering.exception.SaloonNotFoundException;
import first_view.list_cell_factories.BarberAppointmentsListCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.sql.Date;
import java.util.List;

public class BarberListAppointmentsController {

    @FXML private ListView<BookingBean> appointmentsListView;
    @FXML private TextField saloonText;
    @FXML private Button confirmButton;
    @FXML private DatePicker date;






    @FXML
    public void onConfirm(ActionEvent event){
        appointmentsListView.setCellFactory(param -> new BarberAppointmentsListCellFactory(false));

        String saloonName = saloonText.getText();
        Date dateBook = Date.valueOf(date.getValue());
        BookingBean bookingBean = new BookingBean(saloonName,dateBook);
        BarberSeeAppointmentsController barberSeeAppointmentsController = new BarberSeeAppointmentsController();
        List<BookingBean> bookingBeanList = null;
        try {
            bookingBeanList = barberSeeAppointmentsController.retrieveAppointment(bookingBean);
        } catch (SaloonNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.showAndWait();
            return;
        } catch (BookingNotFoundExcption e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.showAndWait();
            return;
        }
        appointmentsListView.setItems(FXCollections.observableList(bookingBeanList));




    }




}


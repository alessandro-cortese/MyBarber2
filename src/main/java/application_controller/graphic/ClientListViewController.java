package application_controller.graphic;

import application_controller.BookingController;
import engineering.bean.SaloonBean;
import first_view.list_cell_factories.SaloonListCellFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.sql.Time;
import java.util.List;


public class ClientListViewController {
    @FXML
    private ListView<SaloonBean> saloonListView;


    private static final String APPOINTMENT_SALOON_ITEM = "first_view/list_item/take_saloon_item.fxml";
    private static final String CLIENT_TAKE_APPOINTMENT ="first_view/client/client_take_appointment.fxml";

    private boolean firstView=true;
    private String saloonName;
    private String saloonAddress;
    private String saloonCity;
    private String saloonPhone;
    private int seatNumber;
    private Time slotTime;
    private BookingController bookingController;
    private List<SaloonBean> saloonBeanList;
    private BookingGraphicController bookingGraphicController;
    private SaloonBean saloonBean;


    public void injectSaloonList(List<SaloonBean> saloonBeanList) {
        saloonListView.setCellFactory(param -> new SaloonListCellFactory(firstView));
        this.saloonBeanList = saloonBeanList;
        saloonListView.setItems(FXCollections.observableArrayList(saloonBeanList));
    }
}

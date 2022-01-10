package application_controller.graphic;

import application_controller.BookingController;
import engineering.bean.SaloonBean;
import first_view.list_cell_factories.SaloonListCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;


public class ClientListViewController implements Initializable {
    @FXML
    private ListView<SaloonBean> saloonListView;

    @FXML
    private Label placeSaloonItem;

    @FXML
    Label labelSaloonItem;

    @FXML
    private Button saloonButton;

    private static final String APPOINTMENT_SALOON_ITEM = "first_view/list_item/take_saloon_item.fxml";


    private String saloonName;
    private String saloonAddress;
    private String saloonCity;
    private String saloonPhone;
    private int seatNumber;
    private Time slotTime;
    private BookingController bookingController;
    private List<SaloonBean> saloonBeanList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saloonListView.setCellFactory(param -> new SaloonListCellFactory());
        saloonListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });
        saloonListView.getItems().clear();
        saloonListView.setItems(FXCollections.observableList(saloonBeanList));

        /* FARE COSI
        *  ProductSearchInfoBean searchInfoBean = new ProductSearchInfoBean(productName) ;
        List<ProductBean> productBeans = buyProductController.filterProductList(searchInfoBean);
        buyProductListView.getItems().clear();
        buyProductListView.setItems(FXCollections.observableList(productBeans));
        *
        * */
    }


    public void ListCityBean(List<SaloonBean> list) {
        this.saloonBeanList = list;
    }
}

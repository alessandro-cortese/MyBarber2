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


    private boolean firstView;
    private List<SaloonBean> saloonBeanList;



    public void injectSaloonList(List<SaloonBean> saloonBeanList) {
        firstView=true;
        saloonListView.setCellFactory(param -> new SaloonListCellFactory(firstView));
        this.saloonBeanList = saloonBeanList;
        saloonListView.setItems(FXCollections.observableArrayList(saloonBeanList));
    }
}

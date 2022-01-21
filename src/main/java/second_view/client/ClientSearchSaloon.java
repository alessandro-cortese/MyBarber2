package second_view.client;

import application_controller.BookingController;
import engineering.bean.SaloonBean;
import first_view.list_cell_factories.SaloonListCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ClientSearchSaloon implements Initializable{

    @FXML TextField commandLine ;
    @FXML
    ListView<SaloonBean> saloonListView;

    public static final String CLIENT_SALOON_ITEM= "second_view/list_item/client_saloon_item.fxml";
    private SaloonBean saloonBean;
    private static List<SaloonBean> saloonBeanList;
    private static  boolean f=true;


    @FXML
    public void onCommand(ActionEvent event) throws Exception {
        String command = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");

        if (command.matches("search name .*")) {//. qualsiasi carattere
            String saloonName = command.replace("search name", "");

        }
        else if (command.matches("search city .*")) {
            String saloonCity = command.replace("search city ","");
            System.out.println(saloonCity);
            saloonBean = new SaloonBean(false, saloonCity);
            BookingController bookingController = new BookingController();
            List<SaloonBean> saloonBeanList = bookingController.searchByCitySaloon(saloonBean);
            for (SaloonBean saloonBean: saloonBeanList){
                System.out.println(saloonBean.getCity());
            }
            saloonListView.setItems(FXCollections.observableList(saloonBeanList));

            return;

        }
        else if (command.matches("select [0-9]+")) { //stringa composta da numeri  da 0 a 9 e almeno un carattere
            //Controllo che ci sia il salone con indice dato
            ScreenChanger.getInstance().changeScreen(event, ScreenChanger.CLIENT_BOOK_DATE_HOUR);
            return;
        }

        else if (command.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return ;
        }

        commandLine.setStyle("-fx-border-color: red");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        saloonListView.setCellFactory(param -> new SaloonListCellFactory(true));


    }


}

package second_view.client;

import application_controller.BookingController;
import engineering.bean.SaloonBean;
import engineering.exception.SaloonNotFoundException;
import first_view.list_cell_factories.SaloonListCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;

import java.io.IOException;
import java.util.List;


public class ClientSearchSaloon {

    @FXML private TextField commandLine ;
    @FXML
    private ListView<SaloonBean> saloonListView;
    @FXML
    private Label slotIndexLabel;
    @FXML
    private Label saloonNameLabel;
    @FXML
    private Label saloonCityLabel;

    private SaloonBean saloonBean;
    private List<SaloonBean> saloonBeanList;
    private SaloonBean saloonBeanInfo;

    public ClientSearchSaloon(){
        saloonListView = new ListView<>() ;
    }

    @FXML
    public void onCommand(ActionEvent event) {
        String command = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");

        try {

            if (command.matches("search name .*")) {//. qualsiasi carattere

                return;//STUB

            } else if (command.matches("search city .*")) {

                saloonListView.setCellFactory(param -> new SaloonListCellFactory(false));

                String saloonCity = command.replace("search city ", "");
                System.out.println(saloonCity);
                saloonBean = new SaloonBean(false, saloonCity);
                BookingController bookingController = new BookingController();

                saloonBeanList = bookingController.searchByCitySaloon(saloonBean);


                int i = 0;
                for (SaloonBean saloonBean : saloonBeanList) {
                    saloonBean.setIndex(i);
                    i++;
                    System.out.println(saloonBean.getIndex());
                    System.out.println(saloonBean.getCity());
                    System.out.println(saloonBean.getName());

                }
                saloonListView.getItems().clear();
                saloonListView.setItems(FXCollections.observableList(saloonBeanList));
                return;

            } else if (command.matches("select [0-9]+")) { //stringa composta da numeri  da 0 a 9 e almeno un carattere
                String index = command.replace("select ", "");
                int in = Integer.parseInt(index);
                verifyValidIndex(in);

                ClientBookingDateHourGraphicContr clientBookingDateHourGraphicContr = (ClientBookingDateHourGraphicContr) ScreenChanger.getInstance().changeScreen(event, ScreenChanger.CLIENT_BOOK_DATE_HOUR);
                clientBookingDateHourGraphicContr.InjectSaloonInfo(saloonBeanInfo);
                return;

            } else if (command.compareTo("back") == 0) {
                ScreenChanger.getInstance().onBack(event);
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SaloonNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        commandLine.setStyle("-fx-border-color: red");
    }

    private void verifyValidIndex(int index) throws SaloonNotFoundException {
        boolean flag = false;
        for (SaloonBean saloonBean : saloonListView.getItems()) {
            int i = saloonBean.getIndex();
            if (index == i) {
                flag = true;
                saloonBeanInfo = new SaloonBean();
                saloonBeanInfo.setName(saloonBean.getName());
                saloonBeanInfo.setCity(saloonBean.getCity());
                saloonBeanInfo.setAddress(saloonBean.getAddress());
                saloonBeanInfo.setPhone(saloonBean.getPhone());
                break;
            }
        }
        if (!flag) {
            throw new SaloonNotFoundException("slot index non valido");
        }
    }
}

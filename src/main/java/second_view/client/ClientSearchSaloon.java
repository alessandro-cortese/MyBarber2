package second_view.client;

import first_view.ObservableListNode;
import first_view.list_cell_factories.CartRowListCellFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import second_view.general.ScreenChanger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ClientSearchSaloon implements Initializable {

    @FXML TextField commandLine ;
    @FXML
    ListView saloonListView;

    public static final String CLIENT_SALOON_ITEM="second_view/client_saloon_item.fxml";


    @FXML
    public void onCommand(ActionEvent event) throws IOException {
        String command = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");

        if (command.matches("search name .*")) {//. qualsiasi carattere
            String saloonName = commandLine.getText();

        }
        else if (command.matches("search city .*")) {

        }
        else if (command.matches("select [0-9]+")) { //stringa composta da numeri  da 0 a 9 e almeno un carattere
            //Controllo che ci sia il salone con indice dato
            ScreenChanger.getInstance().changeScreen(event, ScreenChanger.CLIENT_BOOK_DATEHOUR);
        }

        else if (command.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return ;
        }

        commandLine.setStyle("-fx-border-color: red");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodesList = new Node[10];
        for (int i = 0; i < 10; i++) {

            try {
                nodesList[i] = (new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_SALOON_ITEM))).load();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        ObservableListNode clientAppointmentsList = new ObservableListNode(nodesList);
        saloonListView.setItems(clientAppointmentsList);
    }
}

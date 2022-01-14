package application_controller.graphic;

import application_controller.BookingController;
import engineering.bean.SaloonBean;
import first_view.general.InternalBackController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookingGraphicController{


    public static final String CLIENT_TAKE_SALOONLIST_SCREEN_NAME = "first_view/client/client_take_saloon.fxml";
    private static final String CLIENT_BOOKED_SCREEN_NAME = "first_view/client/client_booked.fxml";
    private static final String CLIENT_DATEHOUR= "first_view/client/client_appointment_Hour&Date.fxml";

    @FXML
    private Button bookedButton;

    @FXML
    private SplitMenuButton catalogueBooking;

    @FXML
    private DatePicker dateBooking;

    @FXML
    private ImageView saloonImage;

    @FXML
    private Label saloonName;

    @FXML
    private TextField textTime;

    @FXML
    private Button confirmDateHourButton;

    @FXML
    private ListView serviceSelectedListView;

    @FXML
    private Label seatNumberLabel;

    @FXML
    private ListView<Node> saloonListView= new ListView<>();
    @FXML
    private Button saloonButton;

    @FXML
    private Button saloonNextButton;

    @FXML
    private Tab saloonTab;

    @FXML
    private Button placeNextButton;

    @FXML
    private Tab placeTab;

    @FXML
    private TextField searchSaloonName;
    @FXML
    private ListView serviceListView;
    @FXML
    private TextField searchCity;

    @FXML
    Label showErr;
    @FXML
    Label showErr2;
    @FXML
    private Label labelSaloonItem;



    private SaloonBean saloonBean;
    private BookingController bookingController;
    private List<SaloonBean> saloonBeanList= new ArrayList<>();
    private SaloonBean saloonBeanName;
    private  BookingDateHourGraphicController bookingDateHourGraphicController;
    private Parent newCenterNode;


    @FXML
    public void onButtonSaloonCityClicked(ActionEvent event) throws Exception { //quando premo bottone della città
        String saloonCity = searchCity.getText();
        saloonBean = new SaloonBean(false,saloonCity);
        bookingController = new BookingController();
        saloonBeanList = bookingController.searchByCitySaloon(saloonBean); //ATT: POSSO COMUNICARE LL'ALTRO CONTROLLER GRAFICO IL SALOONBEANLIST? PER LA CITTÀ

        Button sourceButton = (Button) event.getSource();
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_TAKE_SALOONLIST_SCREEN_NAME));

        ClientListViewController clientListViewController = fxmlLoader.getController();
        clientListViewController.ListCityBean(saloonBeanList); //passo al controller ClientListViewController la saloonBeanList
        Parent newCenterNode = fxmlLoader.load();
        Scene myScene = sourceButton.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        borderPane.setCenter(newCenterNode);
    }
    @FXML
    public void onButtonSaloonClicked(ActionEvent actionEvent) throws Exception {
        newCenterNode = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_DATEHOUR));

        Button sourceButton = (Button) actionEvent.getSource();

        if(sourceButton == saloonNextButton){
            newCenterNode = fxmlLoader.load();
            String saloon = searchSaloonName.getText();

            saloonBean = new SaloonBean(saloon);
            System.out.println(saloon+2);

            bookingDateHourGraphicController = fxmlLoader.getController();
            bookingDateHourGraphicController.display(saloonBean);

            bookingController = new BookingController();
            saloonBeanName = bookingController.searchByNameSaloon(saloonBean);





        }
        if (sourceButton == bookedButton)
            newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_BOOKED_SCREEN_NAME))).load();

        if (sourceButton == saloonButton) { //nell'item recupero il nome del salone e tramite bean passo all'altro controllore le informazione del salone scelto
            String saloonName = labelSaloonItem.getText();

            saloonBean = new SaloonBean(true, saloonName);
            bookingDateHourGraphicController = fxmlLoader.getController();
            bookingDateHourGraphicController.display(saloonBean);
            bookingController = new BookingController();
            saloonBeanName = bookingController.searchByNameSaloon(saloonBean);

            newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_DATEHOUR))).load();
        }





    }
}
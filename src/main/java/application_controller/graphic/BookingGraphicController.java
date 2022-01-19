package application_controller.graphic;

import application_controller.BookingController;
import engineering.bean.SaloonBean;
import first_view.general.InternalBackController;
import first_view.list_cell_factories.SaloonListCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

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
    private ListView<SaloonBean> saloonListView = new ListView<SaloonBean>();
    @FXML
    private Button saloonButton;

    @FXML
    private Button saloonNextButton;

    @FXML
    private TextField searchSaloonName;

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
    private List<SaloonBean> saloonBeanList = new ArrayList<>();
    private SaloonBean saloonBeanName;
    private  BookingDateHourGraphicController bookingDateHourGraphicController;
    private Parent newCenterNode;
    private  String saloonCity;
    private Button sourceButton;
    private FXMLLoader fxmlLoader;


    @FXML
    public void onButtonSaloonCityClicked(ActionEvent event) throws Exception { //quando premo bottone della città


        sourceButton = (Button) event.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_TAKE_SALOONLIST_SCREEN_NAME));
        saloonCity = searchCity.getText();


        newCenterNode = fxmlLoader.load();
        Scene myScene = sourceButton.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        borderPane.setCenter(newCenterNode);
        saloonBean = new SaloonBean(false, saloonCity);
        bookingController = new BookingController();
        saloonBeanList = bookingController.searchByCitySaloon(saloonBean); //ATT: POSSO COMUNICARE LL'ALTRO CONTROLLER GRAFICO IL SALOONBEANLIST? PER LA CITTÀ
       ClientListViewController clientListViewController;
       clientListViewController = fxmlLoader.getController();
       clientListViewController.injectSaloonList(saloonBeanList);


    }

    @FXML
    public void onButtonSaloonClicked(ActionEvent actionEvent) throws Exception {
        newCenterNode = null;
        fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_DATEHOUR));
        sourceButton = (Button) actionEvent.getSource();
        Scene myScene = sourceButton.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        newCenterNode = fxmlLoader.load();
        borderPane.setCenter(newCenterNode);

        if(sourceButton == saloonNextButton){

            String saloon = searchSaloonName.getText();

            saloonBean = new SaloonBean(saloon);
            System.out.println(saloon+2);

            bookingController = new BookingController();
            saloonBeanName = bookingController.searchByNameSaloon(saloonBean);


        }

        if (sourceButton == bookedButton)
            newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_BOOKED_SCREEN_NAME))).load();

        if (sourceButton == saloonButton) { //nell'item recupero il nome del salone e tramite bean passo all'altro controllore le informazione del salone scelto
            String saloonName = labelSaloonItem.getText();

            saloonBean = new SaloonBean(true, saloonName);

            bookingController = new BookingController();
            saloonBeanName = bookingController.searchByNameSaloon(saloonBean); //ritona una saloonBean
            System.out.println("or"+saloonBean.getName());
            bookingDateHourGraphicController = fxmlLoader.getController();
            bookingDateHourGraphicController.display(saloonBeanName);
            bookingDateHourGraphicController.injectSaloonIntoDateHour();


        }





    }
}
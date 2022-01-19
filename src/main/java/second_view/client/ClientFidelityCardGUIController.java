package second_view.client;

import application_controller.ManageCouponController;
import engineering.bean.FidelityCardBean;
import engineering.bean.UserBean;
import engineering.bean.buy_product.CouponBean;
import engineering.exception.InvalidCouponException;
import engineering.exception.NotExistentUserException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import second_view.general.ScreenChanger;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientFidelityCardGUIController implements Initializable {

    @FXML private Label pointsSaleLabel ;
    @FXML private Label userEmailLabel ;
    @FXML private ListView<CouponBean> couponListView ;

    @FXML private TextField commandLine ;

    private final ManageCouponController manageCouponController ;

    public ClientFidelityCardGUIController() {
        manageCouponController = new ManageCouponController() ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserBean loggedUser = ScreenChanger.getInstance().getLoggedUser();
        try {
            viewFidelityCard(loggedUser) ;
        } catch (NotExistentUserException ignored) {
        }
    }


    @FXML
    public void onCommand(ActionEvent event) {
        String command = commandLine.getText() ;
        commandLine.setText("") ;
        commandLine.setStyle(null);

        if (command.compareTo("back") == 0) {
            ScreenChanger.getInstance().onBack(event);
            return ;
        }

        else if (command.matches("generate [0-9]{1,2} euro|generate [0-9]{1,2} perc")) {
            String commandInput = command.replace("generate ", "" );
            String[] commandInputArray = commandInput.split(" ") ;
            String stringCouponValue = commandInputArray[0] ;
            String stringCouponType = commandInputArray[1] ;

            getNewCoupon(stringCouponValue, stringCouponType) ;
            return ;
        }

        commandLine.setStyle("-fx-border-color: RED");
    }

    private void getNewCoupon(String stringCouponValue, String stringCouponType) {
        String couponType = (stringCouponType.compareTo("euro") == 0) ? "subtraction" : "percentage" ;
        Double couponValue = Double.parseDouble(stringCouponValue) ;

        CouponBean requestCouponBean = new CouponBean(couponValue, couponType) ;

        try {
            FidelityCardBean fidelityCardBean = manageCouponController.generateNewCoupon(requestCouponBean) ;
            updateView(fidelityCardBean);
        } catch (InvalidCouponException | NotExistentUserException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage()) ;
            alert.showAndWait() ;
        }
    }

    private void viewFidelityCard(UserBean loggedUser) throws NotExistentUserException {
        FidelityCardBean fidelityCardBean = manageCouponController.showFidelityCard(loggedUser) ;
        updateView(fidelityCardBean);
    }

    private void updateView(FidelityCardBean fidelityCardBean) {
        UserBean loggedUser = ScreenChanger.getInstance().getLoggedUser();
        pointsSaleLabel.setText("Totale Punti " + fidelityCardBean.getPointsSale());
        userEmailLabel.setText("eMail " + loggedUser.getUserEmail());
        couponListView.setItems(FXCollections.observableList(fidelityCardBean.getCouponBeans()));
    }
}

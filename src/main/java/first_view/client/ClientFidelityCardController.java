package first_view.client;

import application_controller.ManageCouponController;
import engineering.bean.FidelityCardBean;
import engineering.bean.UserBean;
import engineering.bean.buy_product.CouponBean;
import engineering.exception.InvalidCouponException;
import engineering.exception.NotExistentUserException;
import first_view.general.InternalBackController;
import first_view.list_cell_factories.CouponCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalTime;
import java.util.Properties;
import java.util.ResourceBundle;

public class ClientFidelityCardController implements Initializable {

    @FXML private ToggleGroup chooseGenerateGroup ;
    @FXML private Button generateCouponButton ;
    @FXML private RadioButton subtractionFiveRadio ;
    @FXML private RadioButton subtractionTenRadio ;
    @FXML private RadioButton subtractionTwentyRadio ;
    @FXML private RadioButton percentageFifteenRadio ;
    @FXML private RadioButton percentageThirtyFiveRadio ;
    @FXML private RadioButton percentageFiftyRadio ;

    @FXML private ListView<CouponBean> couponListView ;
    @FXML private Label pointsSaleLabel ;
    @FXML private Label userNameLabel ;

    private final ManageCouponController manageCouponController ;

    public ClientFidelityCardController() {
        manageCouponController = new ManageCouponController() ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        couponListView.setCellFactory(param -> new CouponCellFactory());

        UserBean userBean = InternalBackController.getInternalBackControllerInstance().getLoggedUser() ;
        if (userBean != null) {
            userNameLabel.setText("eMail " + userBean.getUserEmail());
            FidelityCardBean fidelityCardBean = null;
            try {
                fidelityCardBean = manageCouponController.showFidelityCard(userBean);
                updateView(fidelityCardBean) ;
            } catch (NotExistentUserException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage()) ;
                alert.showAndWait() ;
            }

        }
        generateCouponButton.setDisable(userBean == null);
    }

    private void updateView(FidelityCardBean fidelityCardBean) {
        couponListView.setItems(FXCollections.observableList(fidelityCardBean.getCouponBeans()));
        pointsSaleLabel.setText("Totale Punti " + fidelityCardBean.getPointsSale() );
    }

    @FXML
    public void onButtonClicked(ActionEvent event) {
        Node eventSource = (Node) event.getSource() ;

        UserBean loggedUser = InternalBackController.getInternalBackControllerInstance().getLoggedUser();
        if (loggedUser == null) {
            return ;
        }

        if (eventSource == generateCouponButton) {
            String couponType;
            Double couponValue;

            if (subtractionFiveRadio.isSelected() || subtractionTenRadio.isSelected() || subtractionTwentyRadio.isSelected()) {
                couponType = "subtraction" ;
                if (subtractionFiveRadio.isSelected()) couponValue = 5.0 ;
                else if (subtractionTenRadio.isSelected()) couponValue = 10.0 ;
                else couponValue = 20.0 ;
            }
            else {
                couponType = "percentage" ;
                if (percentageFifteenRadio.isSelected()) couponValue = 15.0 ;
                else if (percentageThirtyFiveRadio.isSelected()) couponValue = 35.0 ;
                else couponValue = 50.0 ;
            }
            getNewCoupon(couponType, couponValue);
        }

    }

    public void getNewCoupon(String couponType, Double couponValue) {
        try {
            CouponBean couponBean = new CouponBean(couponValue, couponType);
            FidelityCardBean fidelityCardBean = manageCouponController.generateNewCoupon(couponBean) ;
            updateView(fidelityCardBean);
        } catch (InvalidCouponException | NotExistentUserException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage()) ;
            alert.showAndWait() ;
        }
    }


}

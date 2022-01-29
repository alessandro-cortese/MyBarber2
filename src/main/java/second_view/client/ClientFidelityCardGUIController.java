package second_view.client;

import application_controller.ManageCouponController;
import engineering.bean.FidelityCardBean;
import engineering.bean.UserBean;
import engineering.bean.buy_product.CouponBean;
import engineering.exception.CardPointsException;
import engineering.exception.InvalidCouponException;
import engineering.exception.NotExistentUserException;
import first_view.list_cell_factories.CouponCellFactory;
import first_view.list_cell_factories.CouponCostListCellFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.buy_product.coupon.Coupon;
import second_view.general.ScreenChanger;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static first_view.list_cell_factories.BuyProductListCellFactory.EURO_SYMBOL;
import static first_view.list_cell_factories.BuyProductListCellFactory.SECOND_VIEW;

public class ClientFidelityCardGUIController implements Initializable {

    @FXML private Label pointsSaleLabel ;
    @FXML private Label userEmailLabel ;
    @FXML private ListView<CouponBean> couponListView ;
    @FXML private ListView<CouponBean> couponCostListView ;

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

        couponListView.setCellFactory(param -> new CouponCellFactory(SECOND_VIEW));

        couponCostListView.setCellFactory(param -> new CouponCostListCellFactory(SECOND_VIEW)) ;

        List<CouponBean> couponBeanList = manageCouponController.showCouponCosts() ;
        couponCostListView.setItems(FXCollections.observableList(couponBeanList));

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

        else if (command.matches("generate [0-9]+")) {
            String commandInput = command.replace("generate ", "" );
            Integer costIndex = Integer.parseInt(commandInput) ;
            getNewCoupon(costIndex) ;
            return ;
        }

        commandLine.setStyle("-fx-border-color: RED");
    }

    private void getNewCoupon(Integer couponCostIndex) {

        try {
            CouponBean couponCostBean = couponCostListView.getItems().get(couponCostIndex);
            FidelityCardBean fidelityCardBean = manageCouponController.generateNewCoupon(couponCostBean) ;
            updateView(fidelityCardBean);
        } catch (CardPointsException | NotExistentUserException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage()) ;
            alert.showAndWait() ;
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "INDICE INDICATO NON VALIDO") ;
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

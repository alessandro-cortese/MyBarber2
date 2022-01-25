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
import javafx.util.Callback;
import model.buy_product.coupon.Coupon;
import second_view.general.ScreenChanger;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static first_view.list_cell_factories.BuyProductListCellFactory.EURO_SYMBOL;

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

        couponListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<CouponBean> call(ListView<CouponBean> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(CouponBean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(String.format("SCONTO: -%1$4.2f\t\t%2$1s\t\tCODICE: %3$4s", item.getCouponDiscount(), (item.getExternalCouponType().compareTo("subtraction") == 0) ? EURO_SYMBOL : "%", item.getExternalCouponCode()));
                            setStyle("-fx-font-size: 14 ; -fx-alignment: center");
                        }
                        else setText(null);
                    }
                };
            }
        });

        couponCostListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<CouponBean> call(ListView<CouponBean> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(CouponBean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(String.format("INDEX: %1$4d\tSCONTO: -%2$4.2f\t\t%3$1s\t\tCOSTO IN PUNTI: %4$3d", this.getIndex() , item.getCouponDiscount(), (item.getExternalCouponType().compareTo("subtraction") == 0) ? EURO_SYMBOL : "%", item.getCouponPointsPrice()));
                            setStyle("-fx-font-size: 12 ; -fx-alignment: center");
                        } else setText(null);
                    }
                };
            }
        });

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
        } catch (InvalidCouponException | NotExistentUserException e) {
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

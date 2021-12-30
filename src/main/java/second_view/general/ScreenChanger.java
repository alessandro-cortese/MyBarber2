package second_view.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ScreenChanger {

    public static final String BARBER_HOME_SCREEN = "second_view/barber/barber_home.fxml";
    public static final String CLIENT_HOME_SCREEN = "second_view/client/client_home.fxml";
    public static final String LOGIN_SCREEN = "second_view/general/login_screen.fxml";
    public static final String SUBSCRIBE_SCREEN = "second_view/general/subscribe_screen.fxml";
    public static final String CLIENT_BUY_PRODUCT_SCREEN = "second_view/client/client_buy_product.fxml";
    public static final String CLIENT_SEE_PRODUCT_SCREEN = "second_view/client/client_see_product.fxml";
    public static final String CLIENT_CART_SCREEN = "second_view/client/client_cart.fxml";
    public static final String CLIENT_SEE_APPOINTMENT_SCREEN = "second_view/client/client_see_appointment.fxml";
    public static final String BARBER_USER_AREA_SCREEN = "second_view/barber/barber_userarea.fxml";
    public static final String BARBER_MANAGE_SERVICE = "second_view/barber/barber_manage_service.fxml";

    public static final String BARBER_ADD_SERVICE_SCREEN_NAME = "second_view/barber/barber_add_service.fxml";
    public static final String BARBER_VIEW_APPOINTMENTS_SCREEN_NAME = "second_view/barber/barber_view_appointments.fxml" ;

    public static final String CLIENT_SEARCH_SALOON_SCREEN = "second_view/client/client_search_saloon.fxml" ;
    public static final String CLIENT_BOOK_APPOINTMENT_SCREEN = "second_view/client/client_book_appointment.fxml" ;


    ArrayList<Scene> backSceneStack ;

    private static ScreenChanger backController ;

    private ScreenChanger() {
        backSceneStack = new ArrayList<>() ;
    }

    public static ScreenChanger getInstance() {
        if (backController == null) {
            backController = new ScreenChanger() ;
        }
        return backController ;
    }

    public void onBack(ActionEvent event) {
        Stage myStage = (Stage)((Node) event.getSource()).getScene().getWindow() ;
        myStage.setScene(backSceneStack.remove(backSceneStack.size() - 1));
    }

    private void setPrevScene(Scene prevScene) {
        backSceneStack.add(prevScene) ;
    }

    public void changeScreen(ActionEvent event, String scenePath) throws IOException {
        Stage myStage = (Stage)((Node) event.getSource()).getScene().getWindow() ;
        FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource(scenePath)) ;
        Scene myScene = new Scene(root.load()) ;
        ScreenChanger.getInstance().setPrevScene(((Node) event.getSource()).getScene());
        myStage.setScene(myScene);
    }


}

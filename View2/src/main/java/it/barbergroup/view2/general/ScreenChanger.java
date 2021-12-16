package it.barbergroup.view2.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ScreenChanger {

    public static final String BARBER_HOME_SCREEN = "it/barbergroup/view2/barber/barber_home.fxml";
    public static final String CLIENT_HOME_SCREEN = "it/barbergroup/view2/client/client_home.fxml";
    public static final String LOGIN_SCREEN = "it/barbergroup/view2/general/login_screen.fxml";
    public static final String SUBSCRIBE_SCREEN = "it/barbergroup/view2/general/subscribe_screen.fxml";
    public static final String CLIENT_BUY_PRODUCT_SCREEN = "it/barbergroup/view2/client/client_buy_product.fxml" ;
    public static final String CLIENT_SEE_PRODUCT_SCREEN = "it/barbergroup/view2/client/client_see_product.fxml" ;
    public static final String CLIENT_CART = "it/barbergroup/view2/client/client_cart.fxml" ;
    public static final String CLIENT_SEE_APPOINTMENT = "it/barbergroup/view2/client/client_see_appointment.fxml" ;

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

    public void setPrevScene(Scene prevScene) {
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

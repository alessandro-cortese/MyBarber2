package first_view.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static first_view.general.RegisterScreenController.CLIENT_MENU_SCREEN_NAME;

public class LoginScreenController {

    private FXMLLoader root;
    private Stage stage;
    private Scene scene;

    private final String THIS_SCENE_NAME = "first_view/general/login_screen.fxml";
    private final String REGISTER_SCREEN_NAME = "first_view/general/register_screen.fxml";

    @FXML Button loginButton ;
    @FXML Button registerButton ;


    @FXML
    public void onBackClicked(ActionEvent event) {
        BackController.getInstance().onBackClick((Node)event.getSource());
    }


    @FXML
    public void onButtonClicked(ActionEvent event) throws  IOException {
        Node eventSource = (Node)event.getSource() ;
        BackController.getInstance().pushPrevScene(eventSource.getScene());

        //Scelta Prossima Scena su base bottone cliccato
        String nextSceneName ;
        nextSceneName = switch (eventSource.getId()) {
            case "registerButton" ->  REGISTER_SCREEN_NAME;
            case "loginButton" -> CLIENT_MENU_SCREEN_NAME ;
            default -> THIS_SCENE_NAME ;
        } ;

        //Caricamento prossima Scena
        root = new FXMLLoader(getClass().getClassLoader().getResource(nextSceneName)) ;
        stage = (Stage)(eventSource).getScene().getWindow() ;
        scene = new Scene(root.load()) ;

        if (nextSceneName.equals(CLIENT_MENU_SCREEN_NAME)) {
            //Imposto la home della menu_screen
            (new EnterAsUserTypeController()).enterAsUser(EnterAsUserTypeController.CLIENT_TYPE, scene);
        }
        stage.setScene(scene) ;
    }

}

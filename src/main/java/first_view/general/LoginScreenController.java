package first_view.general;

import application_controller.LoginController;
import engineering.bean.AccessInfoBean;
import engineering.bean.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static first_view.general.RegisterScreenController.CLIENT_MENU_SCREEN_NAME;

public class LoginScreenController {

    private FXMLLoader root;
    private Stage stage;
    private Scene scene;

    private final String THIS_SCENE_NAME = "first_view/general/login_screen.fxml";
    private final String REGISTER_SCREEN_NAME = "first_view/general/register_screen.fxml";

    @FXML private Button loginButton ;
    @FXML private Button registerButton ;
    @FXML private Button continueWithoutAccessButton ;
    @FXML private TextField emailTextField ;
    @FXML private TextField passwordTextField ;


    @FXML
    public void onBackClicked(ActionEvent event) {
        BackController.getInstance().onBackClick((Node)event.getSource());
    }


    @FXML
    public void onButtonClicked(ActionEvent event) throws  IOException {
        Node eventSource = (Node)event.getSource() ;
        BackController.getInstance().pushPrevScene(eventSource.getScene());

        stage = (Stage)(eventSource).getScene().getWindow() ;

        if (eventSource == loginButton) {
            if (emailTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Completare tutti i campi prima di continuare") ;
                alert.showAndWait() ;
                return ;
            }
            UserBean userBean = login() ;
            if (userBean == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "L'utente indicato non esiste!!") ;
                alert.showAndWait() ;
                return ;
            }
            InternalBackController.getInternalBackControllerInstance().enterAsUser(userBean, stage);
            return ;
        }
        else if (eventSource == continueWithoutAccessButton) {
            InternalBackController.getInternalBackControllerInstance().enterAsUser(null, stage);
            return ;
        }
        else if (eventSource == registerButton) {
            BackController.getInstance().pushPrevScene(eventSource.getScene());
            FXMLLoader registerLoader = new FXMLLoader(getClass().getClassLoader().getResource(REGISTER_SCREEN_NAME)) ;
            stage.setScene(registerLoader.load());
        }

        //Scelta Prossima Scena su base bottone cliccato
        //InternalBackController.getInternalBackControllerInstance().enterAsUser();
    }




    private UserBean login() {
        String userEmail = emailTextField.getText() ;
        String password = passwordTextField.getText() ;

        AccessInfoBean accessInfoBean = new AccessInfoBean(userEmail, password) ;
        LoginController loginController = new LoginController() ;
        UserBean userBean = loginController.verifyUser(accessInfoBean) ;
        return userBean ;

    }

}

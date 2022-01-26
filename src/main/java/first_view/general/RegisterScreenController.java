package first_view.general;

import application_controller.RegisterController;
import engineering.bean.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

import static first_view.general.EnterAsUserTypeController.BARBER_TYPE;
import static first_view.general.EnterAsUserTypeController.CLIENT_TYPE;

public class RegisterScreenController {



    public static final String CLIENT_MENU_SCREEN_NAME = "first_view/client/client_menu.fxml";
    public static final String BARBER_MENU_SCREEN_NAME = "first_view/barber/barber_menu.fxml"; //con final indico la variabile è costante

    @FXML
    private Button registerButton;

    @FXML
    private ToggleGroup userTypeRadioGroup;

    @FXML
    private RadioButton clientTypeRadioButton;

    @FXML
    private RadioButton barberTypeRadioButton;
    @FXML
    private TextField nameText;
    @FXML
    private TextField surnameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField passText;
    @FXML
    private TextField confirmPassText;
    @FXML
    private Button googleButton;
    @FXML
    private Button facebookButton;
    private int userType;


    @FXML
    public void onBackClicked(ActionEvent event) {
        BackController.getInstance().onBackClick((Node)event.getSource());
    }


    @FXML
    public void onButtonClicked(ActionEvent event) throws  IOException {
        Node sourceNode = (Node)event.getSource() ;
        if (sourceNode.getId().equals("registerButton"))
            onRegisterButtonClicked(sourceNode) ;
        if(sourceNode.getId().equals(googleButton))
            return;
        if(sourceNode.getId().equals(facebookButton))
            return;

        UserBean userBean = new UserBean();
        userBean.setUserEmail(emailText.getText());
        userBean.setName(nameText.getText());
        userBean.setPass(passText.getText());
        userBean.setSurname(surnameText.getText());
        userBean.setUserType(userType);

        RegisterController registerController = new RegisterController();
        registerController.register(userBean);



    }

    private void onRegisterButtonClicked(Node sourceNode) throws IOException {
        BackController.getInstance().pushPrevScene(sourceNode.getScene());

        //Vedo tipo di utente selezionato in base al RadioButton
        userType = clientTypeRadioButton.isSelected() ? CLIENT_TYPE : BARBER_TYPE ;
        String nextSceneName = clientTypeRadioButton.isSelected() ? CLIENT_MENU_SCREEN_NAME : BARBER_MENU_SCREEN_NAME ;

        //Scelgo prossima scena e la imposto nello Stage
        FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource(nextSceneName));
        Stage stage = (Stage) (sourceNode).getScene().getWindow();
        Scene scene = new Scene(root.load());

        //Carico la HomeCorretta nella VBox del MenuScreen
        (new EnterAsUserTypeController()).enterAsUser(userType, scene);
        stage.setScene(scene) ;
    }

}

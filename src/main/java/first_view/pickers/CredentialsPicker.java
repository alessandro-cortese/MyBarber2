package first_view.pickers;

import engineering.bean.AccessInfoBean;
import engineering.bean.UserBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleAction;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;

public class CredentialsPicker extends Dialog<AccessInfoBean> {

    private static final String CREDENTIALS_PICKER_RES = "first_view/pickers/credentials_picker.fxml";
    private static final String EMAIL_FIELD_ID = "emailField" ;
    private static final String PASSWORD_FIELD_ID = "passwordField" ;


    public CredentialsPicker() throws IOException {
        DialogPane dialogPane = (new FXMLLoader(getClass().getClassLoader().getResource(CREDENTIALS_PICKER_RES))).load() ;
        this.setDialogPane(dialogPane);
        this.setTitle("Inserire Credenziali");

        TextField emailField = (TextField) dialogPane.lookup("#" + EMAIL_FIELD_ID);
        PasswordField passwordField = (PasswordField) dialogPane.lookup("#" + PASSWORD_FIELD_ID) ;

        this.resultConverterProperty().set(param -> {
            String email = "";
            String password = "";
            if (param == ButtonType.OK) {
                email = emailField.getText() ;
                password = passwordField.getText() ;

            }
            AccessInfoBean accessInfoBean = new AccessInfoBean(email, password) ;
            this.setResult(accessInfoBean);
            return accessInfoBean ;
        });
    }


    public AccessInfoBean getAccessInfo() {
        this.showAndWait() ;
        return this.getResult() ;
    }
}

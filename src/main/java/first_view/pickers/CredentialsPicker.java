package first_view.pickers;

import engineering.bean.AccessInfoBean;
import engineering.bean.UserBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleAction;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
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
        TextField passwordField = (TextField) dialogPane.lookup("#" + PASSWORD_FIELD_ID) ;

        this.resultConverterProperty().set(param -> {
            if (param == ButtonType.OK) {
                AccessInfoBean accessInfoBean = new AccessInfoBean(emailField.getText(), passwordField.getText()) ;
                this.setResult(accessInfoBean);
                return accessInfoBean ;
            }
            else {
                return null ;
            }
        });
    }


    public AccessInfoBean getAccessInfo() {
        this.showAndWait() ;
        return this.getResult() ;
    }
}

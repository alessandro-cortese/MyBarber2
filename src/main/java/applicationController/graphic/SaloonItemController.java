package applicationController.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class SaloonItemController {

    @FXML
    private Label labelSaloon;

    @FXML
    private Text nameSaloonItem;

    @FXML
    private Button saloonButton;

    public void setNameSaloonItem(String value) {

        nameSaloonItem.setText(value);
    }

    @FXML
    void onButtonClicked(ActionEvent event) {

    }

}

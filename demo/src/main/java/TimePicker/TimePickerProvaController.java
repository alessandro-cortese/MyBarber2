package TimePicker;

import TimePicker.TimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class TimePickerProvaController {

    @FXML
    public void onChangeTime(MouseEvent event) throws IOException {
        ((TextField) event.getSource()).setText((new TimePicker(0,23)).getTime());
    }
}

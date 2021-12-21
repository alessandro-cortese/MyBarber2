package first_view.barber;

import first_view.pickers.PricePicker;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class BarberAddProductController {

    @FXML
    private TextField priceTextField;

    @FXML
    public void onPriceClicked(MouseEvent event) throws IOException {
        PricePicker pricePicker = new PricePicker(0, 0.0);

        priceTextField.setText(pricePicker.getPrice());
    }

}

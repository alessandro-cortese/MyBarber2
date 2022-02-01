package first_view.list_cell_factories;

import javafx.scene.control.ListCell;

public class CouponCodeCellFactory extends ListCell<String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            setText("Codice Coupon: " + item);
            setStyle("-fx-alignment: center ; -fx-font-size: 16");
        }
        else setText(null);
    }
}

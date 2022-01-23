package first_view.barber;

import application_controller.AddProductController;
import engineering.bean.UserBean;
import engineering.bean.buy_product.ProductBean;
import first_view.general.InternalBackController;
import first_view.pickers.PricePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.Objects;

public class BarberAddProductController {

    @FXML private TextField nameAddProductTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField descriptionTextFiledAddProduct;
    @FXML private Button continueButtonAddProduct;
    @FXML private Label exceptionAddProductLabel;

    private boolean continueFlag;

    public BarberAddProductController () {

        continueFlag = true;

    }

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {

        exceptionAddProductLabel.setText("");
        Node sourceNodeAddProduct = (Node) event.getSource();
        AddProductController addProductController = new AddProductController();
        UserBean userBean = InternalBackController.getInternalBackControllerInstance().getLoggedUser();

        if(sourceNodeAddProduct == continueButtonAddProduct) {

            controlContinue();
            try {

                ProductBean productBean = new ProductBean(-1, nameAddProductTextField.getText(), descriptionTextFiledAddProduct.getText(), Double.parseDouble(priceTextField.getText()));

                if(continueFlag) {
                    InternalBackController.getInternalBackControllerInstance().backToHome(sourceNodeAddProduct);
                    addProductController.addProduct(productBean, userBean);
                }


            }catch (NumberFormatException e) {

                exceptionAddProductLabel.setText("Inserisci il valore nel campi!");
                continueFlag = false;

            }

        }

    }

    private void controlContinue() {
        continueFlag = (nameAddProductTextField.getText() != null && !Objects.equals(nameAddProductTextField.getText(), "")) &&
                (descriptionTextFiledAddProduct.getText() != null && !Objects.equals(descriptionTextFiledAddProduct.getText(), "")) &&
                (priceTextField.getText() != null && !Objects.equals(priceTextField.getText(), "0.00"));
    }

    @FXML
    public void onPricePicked(MouseEvent event) throws IOException {
        PricePicker pricePicker = new PricePicker(0, 0.0);
        priceTextField.setText(pricePicker.getPrice());
    }

}
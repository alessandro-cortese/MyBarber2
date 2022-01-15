package first_view.list_cell_factories;

import engineering.bean.SaloonBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class BarberSaloonListCellFactory extends ListCell<SaloonBean> {

    private Parent parentNode = null;

    private static final String BARBER_SALOON_LIST_ITEM = "first_view/list_item/center_list_item.fxml" ;
    private static final String SALOON_NAME_LABEL_ID = "barberSaloonNameLabel";
    private static final String SALOON_CITY_LABEL_ID = "barberSaloonCityLabel";
    private static final String SALOON_ADDRESS_LABEL_ID = "barberSaloonAddressLabel";

    @Override
    protected void updateItem(SaloonBean item, boolean empty) {

        super.updateItem(item, empty);

        Label nameSaloonLabel;
        Label citySaloonLabel;
        Label addressSaloonLabel;

        if(item != null) {

            try{

                if(parentNode == null) {

                    parentNode = new FXMLLoader(getClass().getClassLoader().getResource(BARBER_SALOON_LIST_ITEM)).load() ;

                }

                nameSaloonLabel = (Label) parentNode.lookup("#" + SALOON_NAME_LABEL_ID);
                citySaloonLabel = (Label) parentNode.lookup("#" + SALOON_CITY_LABEL_ID);
                addressSaloonLabel = (Label) parentNode.lookup("#" + SALOON_ADDRESS_LABEL_ID);

                nameSaloonLabel.setText(item.getName());
                citySaloonLabel.setText(item.getCity());
                addressSaloonLabel.setText(item.getAddress());

                setGraphic(parentNode);

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

}
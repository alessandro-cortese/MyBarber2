package first_view.listCellFactories;

import applicationController.AddServiceController;
import engineering.bean.ServiceBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import static first_view.listCellFactories.BuyProductListCellFactory.EURO_SYMBOL;
import java.io.IOException;
import java.util.ArrayList;

public class ServiceListCellFactory extends ListCell<ServiceBean> {

    private Parent parentNode = null ;

    private static final String BARBER_LIST_ITEM = "first_view/listitem/barber_service_list_item.fxml" ;
    private static final String SERVICE_NAME_LABEL_ID = "serviceNameLabel";
    private static final String SERVICE_PRICE_LABEL_ID = "servicePriceLabel";

    @Override
    protected void updateItem(ServiceBean item, boolean empty){

        super.updateItem(item, empty);

        if(item != null) {

            try{

                if(parentNode == null) {
                    parentNode = new FXMLLoader(getClass().getClassLoader().getResource(BARBER_LIST_ITEM)).load() ;
                }

                Label nameServiceLabel = (Label) parentNode.lookup("#" + SERVICE_NAME_LABEL_ID);
                Label priceServiceLabel = (Label) parentNode.lookup("#" + SERVICE_PRICE_LABEL_ID);
                nameServiceLabel.setText(item.getName());
                priceServiceLabel.setText(item.getPrice() + " " + EURO_SYMBOL);

                setGraphic(parentNode);

            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }


}

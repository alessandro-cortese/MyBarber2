package first_view.list_cell_factories;

import engineering.bean.BookingBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import java.io.IOException;



public class BarberAppointmentsListCellFactory extends ListCell<BookingBean> {

    private static final String BARBER_APPOINTMENTS_LIST_ITEM = "first_view/list_item/barber_appointments_item.fxml";
    private static final String NAME_LABEL = "nameLabel";
    private static final String SURNAME_LABEL = "surnameLabel";
    private static final String TO_TIME_LABEL = "toTimeLabel";
    private static final String FROM_TIME_LABEL = "fromTimeLabel";
    private static final String SLOT_INDEX ="indexLabel" ;
    private final boolean view;
    Parent parentNode = null;
    
    public BarberAppointmentsListCellFactory(boolean view){
        this.view = view;
    }

    @Override
    protected void updateItem(BookingBean item, boolean empty) {
        super.updateItem(item, empty);
    if(item !=null){
        try {
            if (parentNode == null) parentNode = new FXMLLoader(getClass().getClassLoader().getResource(BARBER_APPOINTMENTS_LIST_ITEM)).load() ;
            Label nameLabel = (Label) parentNode.lookup("#"+NAME_LABEL);
            Label surnameLabel = (Label) parentNode.lookup("#"+ SURNAME_LABEL);
            Label toTimeLabel = (Label) parentNode.lookup("#"+ TO_TIME_LABEL);
            Label fromTimeLabel =(Label) parentNode.lookup("#"+ FROM_TIME_LABEL);
            
            nameLabel.setText(item.getNameCustomer());
            surnameLabel.setText(item.getSurnameCustomer());
            toTimeLabel.setText(String.valueOf(item.getToTime()));
            fromTimeLabel.setText(String.valueOf(item.getFromTime()));
            
            if(view){
                Label index = (Label) parentNode.lookup("#"+ SLOT_INDEX);
                index.setText("slot index: "+ Integer.toString(this.getIndex()));
            }
            
            setGraphic(parentNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    }


}

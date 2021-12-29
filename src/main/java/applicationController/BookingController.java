package applicationController;

import java.util.*;
import engineering.bean.SaloonBean;
import engineering.dao.SaloonDAO;
import model.Saloon;

public class BookingController {
    private SaloonDAO saloonDAO;
    private List<Saloon> listSaloon;
    private  List<SaloonBean> saloonBeanList;
    private String saloonName;

    public List<SaloonBean> searchByNameSaloon(SaloonBean saloonBean) throws Exception {

        saloonBeanList = new ArrayList<>();

        saloonName = saloonBean.getName();
        saloonDAO = new SaloonDAO();

        listSaloon= saloonDAO.retreiveBySaloonName(saloonName);

        for (Saloon saloon: listSaloon){ //imposto il saloonBean
            saloonBean.setName(saloon.getName());
            saloonBean.setAddress(saloon.getAddress());
            saloonBean.setCity(saloon.getCity());
            saloonBean.setPhone(saloon.getPhone());
            saloonBean.setSeatNumber(saloon.getSeatNumber());
            saloonBean.setSlotTime(saloon.getSlotTime());
            saloonBeanList.add(saloonBean); //aggiungo al vettore saloonbeanList il saloonBean precedentemente impostato
        }
        return saloonBeanList;
    }

}

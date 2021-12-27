package applicationController.logic;

import java.io.IOException;
import java.util.*;

import engineering.bean.SaloonBean;
import engineering.dao.SaloonDAO;
import model.Saloon;

public class BookingController {
    private SaloonDAO saloonDAO;


    public List<Saloon> searchByNameSaloon(SaloonBean saloonBean) throws Exception {
        List<Saloon> listSaloon = new ArrayList<>();

        String saloonName = saloonBean.getName();
        String saloonCity = saloonBean.getCity();

        SaloonDAO saloonDAO = new SaloonDAO();

        listSaloon= saloonDAO.retreiveBySaloonName(saloonName);



        return listSaloon;
    }

}

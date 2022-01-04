package application_controller;

import java.util.*;

import engineering.bean.BookingBean;
import engineering.bean.SaloonBean;
import engineering.dao.SaloonDAO;
import model.Saloon;

public class BookingController {
    private SaloonDAO saloonDAO;
    private List<Saloon> listSaloon;
    private  List<SaloonBean> saloonBeanList;
    private String saloonCity;
    private String saloonName;
    private Saloon saloonByName;

    public List<SaloonBean> searchByCitySaloon(SaloonBean saloonBean) throws Exception {

        saloonBeanList = new ArrayList<>();

        saloonCity = saloonBean.getCity();
        saloonDAO = new SaloonDAO();

        listSaloon= saloonDAO.retreiveByCityName(saloonCity); //chiamo la dao del salone per recuperare dal DB I valori della ricerca

        for (Saloon saloon: listSaloon){ //imposto il saloonBean prendendo i valori dal model
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

    public BookingController VerifyBooking(BookingBean bookingBean){


        return null;
    }

    public SaloonBean searchByNameSaloon(SaloonBean saloonBean) throws Exception {

        //saloonName = saloonBean.getName();
        //SaloonDAO saloonDAO = new SaloonDAO();

        saloonByName = saloonDAO.retreiveByNameSaloon(saloonName);

        saloonBean.setName(saloonByName.getName());
        saloonBean.setCity(saloonByName.getCity());
        saloonBean.setAddress(saloonByName.getAddress());
        saloonBean.setPhone(saloonByName.getPhone());
        saloonBean.setSeatNumber(saloonByName.getSeatNumber());
        saloonBean.setSlotTime(saloonByName.getSlotTime());

        return  saloonBean;

    }

}

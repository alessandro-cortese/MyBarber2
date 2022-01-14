package application_controller;

import java.sql.SQLException;
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
    private Saloon saloon;

    public List<SaloonBean> searchByCitySaloon(SaloonBean saloonBean) throws Exception {

        saloonBeanList = new ArrayList<>();

        saloonCity = saloonBean.getCity();
        saloonDAO = new SaloonDAO();

        listSaloon = saloonDAO.retrieveByCityName(saloonCity); //chiamo la dao del salone per recuperare dal DB I valori della ricerca

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

    public BookingController verifyBooking(BookingBean bookingBean){


        return null;
    }

    public SaloonBean searchByNameSaloon(SaloonBean saloonBean) throws Exception {

        //saloonName = saloonBean.getName();
        //SaloonDAO saloonDAO = new SaloonDAO();
        System.out.println(saloonBean.getName());
        saloonByName = saloonDAO.retrieveByNameSaloon(saloonBean.getName());

        saloonBean.setName(saloonByName.getName());
        saloonBean.setCity(saloonByName.getCity());
        saloonBean.setAddress(saloonByName.getAddress());
        saloonBean.setPhone(saloonByName.getPhone());
        saloonBean.setSeatNumber(saloonByName.getSeatNumber());
        saloonBean.setSlotTime(saloonByName.getSlotTime());

        return  saloonBean;

    }

    public SaloonBean searchTimeSlots(SaloonBean saloonBean) {
        SaloonBean saloonBeanTimeSlots;
        SaloonDAO saloonDAO = new SaloonDAO();
        saloonName = saloonBean.getName();

        try {
            saloon = saloonDAO.retreiveTimeSlots(saloonName);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        saloonBeanTimeSlots = new SaloonBean();
        saloonBeanTimeSlots.setOpeningMorningTimeInfo(saloon.getOpeningMorningTime());
        saloonBeanTimeSlots.setOpeningAfternoonTimeInfo(saloon.getOpeningAfternoonTime());
        saloonBeanTimeSlots.setCloseMorningTimeInfo(saloon.getCloseMorningTime());
        saloonBeanTimeSlots.setCloseAfternoonTimeInfo(saloon.getCloseAfternoonTime());
        saloonBeanTimeSlots.setSlotTime(saloon.getSlotTime());
        saloonBeanTimeSlots.setSeatNumber(saloon.getSeatNumber());
        saloonBeanTimeSlots.setNumberOfMorningSlotsInfo(saloon.getNumberOfMorningSlots());
        saloonBeanTimeSlots.setNumberOfAfternoonSlotsInfo(saloon.getNumberOfAfternoonSlots());

        return saloonBeanTimeSlots;
    }
}

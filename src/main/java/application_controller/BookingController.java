package application_controller;

import java.util.*;

import engineering.bean.SaloonBean;
import engineering.bean.ServiceBean;
import engineering.dao.SaloonDAO;
import engineering.dao.ServiceDAO;
import engineering.exception.InsertNegativePriceException;
import model.Saloon;
import model.Service;

public class BookingController {
    private SaloonDAO saloonDAO;
    private List<Saloon> listSaloon;
    private  List<SaloonBean> saloonBeanList;
    private String saloonCity;
    private String saloonName;
    private Saloon saloonByName;
    private Saloon saloon;
    private String saloonNameG;
    private List<Service> ServicesList;
    private List<ServiceBean> servicesBeanList;

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


    public SaloonBean searchByNameSaloon(SaloonBean saloonBean) throws Exception {

        SaloonDAO saloonDAO = new SaloonDAO();
        System.out.println(saloonBean.getName());
        saloonByName = saloonDAO.retrieveByNameOfSaloon(saloonBean.getName());

        saloonBean.setName(saloonByName.getName());
        saloonBean.setCity(saloonByName.getCity());
        saloonBean.setAddress(saloonByName.getAddress());
        saloonBean.setPhone(saloonByName.getPhone());


        return  saloonBean;

    }

    public SaloonBean searchTimeSlots(SaloonBean saloonBean) {
        SaloonBean saloonBeanTimeSlots;
        SaloonDAO saloonDAO = new SaloonDAO();
        saloonName = saloonBean.getName();

        try {

            saloon = saloonDAO.retrieveTimeSlots(saloonName);

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


    public void setSaloonName(String saloonCity) {
        saloonNameG = saloonCity;
    }

    public List<ServiceBean> SearchServices(SaloonBean saloonBean) {
        ServiceDAO serviceDAO = new ServiceDAO();
        servicesBeanList = new ArrayList<>();
        ServicesList = serviceDAO.retrieveService(saloonBean.getName());
        for (Service service: ServicesList){
            try {
                ServiceBean serviceBean = new ServiceBean();
                serviceBean.setNameInfo(service.getServiceName());
                serviceBean.setDescriptionInfo(service.getServiceDescription());
                serviceBean.setPriceInfo(service.getServicePrice());
                servicesBeanList.add(serviceBean);
            } catch (InsertNegativePriceException e) { //RICORDARSI DI FARE LA MIA di eccezzione(CIOÈ DI ROBERTO)
                e.printStackTrace();
            }

        }

        return servicesBeanList;
    }
}

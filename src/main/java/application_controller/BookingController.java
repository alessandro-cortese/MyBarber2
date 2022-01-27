package application_controller;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

import engineering.bean.*;
import engineering.dao.BookingDAO;
import engineering.dao.CustomerDAO;
import engineering.dao.SaloonDAO;
import engineering.dao.ServiceDAO;
import engineering.exception.SaloonNotFoundException;
import engineering.exception.ServiceNotFoundException;
import engineering.time.ScheduleTime;
import engineering.time.TimeSlot;
import javafx.scene.control.Alert;
import model.Customer;
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
    private List<TimeSlotBean> saloonBeanTimeSlots;
    private List<TimeSlot> saloonTimeSlots;
    private Date date;

    public List<SaloonBean> searchByCitySaloon(SaloonBean saloonBean) throws Exception {

        saloonBeanList = new ArrayList<>();
        saloonCity = saloonBean.getCity();
        saloonDAO = new SaloonDAO();

        listSaloon = saloonDAO.retrieveByCityName(saloonCity);

        for (Saloon saloon: listSaloon){
            saloonBean.setName(saloon.getName());
            saloonBean.setAddress(saloon.getAddress());
            saloonBean.setCity(saloon.getCity());
            saloonBean.setPhone(saloon.getPhone());
            saloonBean.setSeatNumber(saloon.getSeatNumber());
            saloonBean.setSlotTime(saloon.getSlotTime());
            saloonBeanList.add(saloonBean);
        }
        return saloonBeanList;
    }


    public SaloonBean searchByNameSaloon(SaloonBean saloonBean){

        SaloonDAO saloonDAO = new SaloonDAO();
        System.out.println(saloonBean.getName());
        saloonByName = saloonDAO.retrieveByNameOfSaloon(saloonBean.getName());

        saloonBean.setName(saloonByName.getName());
        saloonBean.setCity(saloonByName.getCity());
        saloonBean.setAddress(saloonByName.getAddress());
        saloonBean.setPhone(saloonByName.getPhone());


        return  saloonBean;

    }

    public List<TimeSlotBean> searchTimeSlots(SaloonBean saloonBean) {
        saloonBeanTimeSlots= new ArrayList<>();
        SaloonDAO saloonDAO = new SaloonDAO();
        saloonName = saloonBean.getName();

        try {

            saloon = saloonDAO.retrieveTimeSlots(saloonName);

        } catch (Exception e) {
            e.printStackTrace();

        }
        saloonTimeSlots= new ScheduleTime(saloon).CreateSlotTime();
        saloonBeanTimeSlots = new ArrayList<>();
        for (TimeSlot timeSlot : saloonTimeSlots){
            TimeSlotBean timeSlotBean = new TimeSlotBean();
            timeSlotBean.setFromTime(timeSlot.getFromTime());
            timeSlotBean.setToTime(timeSlot.getToTime());
            timeSlotBean.setSeatAvailable(timeSlot.getSeatAvailable());
            saloonBeanTimeSlots.add(timeSlotBean);
        }

        return saloonBeanTimeSlots;
    }

    public List<ServiceBean> SearchServices(SaloonBean saloonBean) throws ServiceNotFoundException {
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
            } catch (Exception e) {
                ServiceNotFoundException e1 = new ServiceNotFoundException(e.getMessage());
                throw e1;
            }

        }

        return servicesBeanList;
    }

    public boolean saveBooking(List<ServiceBean> serviceListSelected, SaloonBean saloonInfo, UserBean userBean, TimeSlot timeSlot, Date date) {
        String userEmail = userBean.getUserEmail();
        String[] services = new String[serviceListSelected.size()];
        Time fromTime = timeSlot.getFromTime();
        Time toTime= timeSlot.getToTime();
        this.date=date ;
        
        for (int i=0; i< serviceListSelected.size(); i++){
            services[i] = serviceListSelected.get(i).getNameInfo();
        }
        CustomerDAO customerDAO = new CustomerDAO();
        BookingDAO bookingDAO = new BookingDAO();
        Customer customer = customerDAO.retrieveInfoCustomer(userEmail);
        //Booking booking = new Booking(customer,saloonByName,,) MANCA LA SELEZIONE DEL SALONE E LO SLOT SELEZ
        boolean insertBooking = bookingDAO.insertBooking(userEmail, saloonInfo.getName(), services, fromTime, toTime, date);

        return false;
    }

    public boolean checkDateHour(BookingBean bookingBean) throws  SaloonNotFoundException {
        SaloonDAO saloonDAO = new SaloonDAO();
        boolean result = saloonDAO.checkDateSaloon(bookingBean.getSaloonName() ,bookingBean.getDate());
        if (result == true){
            String mess="data non disponibile, il Salone è chiuso!";
                throw new SaloonNotFoundException(mess);
        }
        else{
            System.out.println("Data confermata");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"conferma effettuta");
            alert.showAndWait();
        }

        return result;
    }

}

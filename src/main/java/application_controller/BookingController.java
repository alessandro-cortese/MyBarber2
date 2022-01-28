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

    private Saloon saloon;

    private Date date;

    public List<SaloonBean> searchByCitySaloon(SaloonBean saloonBean) throws Exception {

        List<SaloonBean> saloonBeanList = new ArrayList<>();
        String saloonCity = saloonBean.getCity();
        SaloonDAO saloonDAO = new SaloonDAO();

        List<Saloon> listSaloon = saloonDAO.retrieveByCityName(saloonCity);

        for (Saloon saloonItem: listSaloon){
            saloonBean.setName(saloonItem.getName());
            saloonBean.setAddress(saloonItem.getAddress());
            saloonBean.setCity(saloonItem.getCity());
            saloonBean.setPhone(saloonItem.getPhone());
            saloonBean.setSeatNumber(saloonItem.getSeatNumber());
            saloonBean.setSlotTime(saloonItem.getSlotTime());
            saloonBeanList.add(saloonBean);
        }
        return saloonBeanList;
    }


    public SaloonBean searchByNameSaloon(SaloonBean saloonBean){

        SaloonDAO saloonDAO = new SaloonDAO();
        Saloon saloonByName = saloonDAO.retrieveByNameOfSaloon(saloonBean.getName());

        saloonBean.setName(saloonByName.getName());
        saloonBean.setCity(saloonByName.getCity());
        saloonBean.setAddress(saloonByName.getAddress());
        saloonBean.setPhone(saloonByName.getPhone());


        return  saloonBean;

    }

    public List<TimeSlotBean> searchTimeSlots(SaloonBean saloonBean) {
        List<TimeSlotBean> saloonBeanTimeSlots= new ArrayList<>();
        SaloonDAO saloonDAO = new SaloonDAO();
        String saloonName = saloonBean.getName();

        try {

            saloon = saloonDAO.retrieveTimeSlots(saloonName);

        } catch (Exception e) {
            e.printStackTrace();

        }
        List<TimeSlot> saloonTimeSlots= new ScheduleTime(saloon).CreateSlotTime();
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
        List<ServiceBean> servicesBeanList = new ArrayList<>();
        List<Service> ServicesList = serviceDAO.retrieveService(saloonBean.getName());
        for (Service service: ServicesList){
            try {
                ServiceBean serviceBean = new ServiceBean();
                serviceBean.setNameInfo(service.getServiceName());
                serviceBean.setDescriptionInfo(service.getServiceDescription());
                serviceBean.setPriceInfo(service.getServicePrice());
                servicesBeanList.add(serviceBean);
            } catch (Exception e) {
                throw new ServiceNotFoundException(e.getMessage());
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
            String message="conferma effettuta";
            Alert alert = new Alert(Alert.AlertType.INFORMATION,message);
            alert.showAndWait();


        return result;
    }

}

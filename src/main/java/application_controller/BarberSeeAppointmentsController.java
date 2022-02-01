package application_controller;

import engineering.bean.BookingBean;
import engineering.dao.BookingDAO;
import model.Booking;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BarberSeeAppointmentsController {

    public List<BookingBean> retrieveAppointment(BookingBean bookingBean) {
        String saloonName = bookingBean.getSaloonName();
        Date date = Date.valueOf(bookingBean.getDate());
        List<BookingBean> bookingBeanList = new ArrayList<>();
        BookingDAO bookingDAO = new BookingDAO();

        List<Booking> booking = bookingDAO.retrieveBookingList(saloonName, date);

        for (Booking booking1 : booking){
            BookingBean bookingBean1 = new BookingBean();
            bookingBean1.setNameCustomer(booking1.getCustomer().getName());
            bookingBean1.setSurnameCustomer(booking1.getCustomer().getSurname());
            bookingBean1.setFromTime(booking1.getTimeSlot().getFromTime());
            bookingBean1.setToTime(booking1.getTimeSlot().getToTime());
            bookingBeanList.add(bookingBean1);
        }

        return bookingBeanList;
    }
}

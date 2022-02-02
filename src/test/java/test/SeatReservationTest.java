package test;

import application_controller.BarberSeeAppointmentsController;
import engineering.bean.BookingBean;
import engineering.exception.BookingNotFoundExcption;
import engineering.exception.SaloonNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeatReservationTest {

    @Test
    public void checkBooking() throws SaloonNotFoundException, BookingNotFoundExcption {

        String dateBooking = "2020-11-11";
        BookingBean bookingBean = new BookingBean();
        bookingBean.setDate(dateBooking);
        BarberSeeAppointmentsController barberSeeAppointmentsController = new BarberSeeAppointmentsController();
        List<BookingBean> bookingBeanList = barberSeeAppointmentsController.retrieveAppointment(bookingBean);
        String saloon = bookingBeanList.get(0).getSaloonName();
        assertEquals("TagliaX", saloon);
    }
}

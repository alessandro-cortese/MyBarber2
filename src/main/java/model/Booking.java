package model;

import engineering.container.ServiceCatalogue;
import engineering.time.TimeSlot;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Booking{
    private Date dateBooking;
    private TimeSlot timeSlot;
    private List<Service> services;
    private Customer customer;
    private Saloon saloon;

    public Booking(){
        this.services= null;
        this.timeSlot=null;
        this.customer=null;
        this.saloon=null;
    }
    public Booking(Customer customer,Saloon saloon, List<Service> services, TimeSlot timeSlot){ //compongo customer
        this.customer =customer;
        this.saloon = saloon;
        this.services =services;
        this.timeSlot = timeSlot;

    }

    public Date getDateBooking() {
        return dateBooking;
    }
    public  void setDateBooking(Date dateBooking){
        this.dateBooking=dateBooking;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Service> getServices() {
        return services;
    }

    public Saloon getSaloon() {
        return saloon;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setSaloon(Saloon saloon) {
        this.saloon = saloon;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}

package model;

import engineering.container.ServiceCatalogue;
import engineering.time.TimeSlot;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Booking{
    private Date dateBooking;
    private TimeSlot timeSlot;
    private double priceBooking;
    private List<Service> services;
    private Customer customer;
    private Saloon saloon;

    public Booking(){
        this.services= null;
    }
    public Booking(Customer customer){ //compongo customer
        //this.customer = new Customer(customer.getEmail(), customer.getPass(), customer.getName(), customer.getSurname(),customer.getCreditCard());

    }
    public Booking(List<Service> services){ //aggrego services
        this.services = services;
    }
    public Booking(Saloon saloon){
       // this.saloon = new Saloon(saloon.getName(), ); DA FINIRE
    }


    public Date getDateBooking() {
        return dateBooking;
    }
    public  void setDateBooking(Date dateBooking){
        this.dateBooking=dateBooking;
    }

    public double getPriceBooking() {
        return priceBooking;
    }
    public void setPriceBooking(double priceBooking) {
        this.priceBooking = priceBooking;
    }
}

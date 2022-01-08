package model;

import engineering.container.ServiceCatalogue;

import java.sql.Time;
import java.util.Date;

public class Booking {
    private Date dateBooking = new Date();
    private Time hourBooking;
    private double priceBooking;
    private ServiceCatalogue services;
    private Customer customer;

    public Booking(){
        services = new ServiceCatalogue();
    }
    public Booking(Customer customer){
        this.customer=customer;
    }
    public Booking(String email, String pass, String name, String surname,String creditCard, String phone ){
        customer = new Customer(email, pass, name, surname, creditCard, phone);
    }

    public Date getDateBooking() {
        return dateBooking;
    }
    public  void setDateBooking(Date dateBooking){
        this.dateBooking=dateBooking;
    }
    public Time getHourBooking(){
        return hourBooking;
    }
    public void setHourBooking(Time hourBooking) {
        this.hourBooking = hourBooking;
    }

    public double getPriceBooking() {
        return priceBooking;
    }
    public void setPriceBooking(double priceBooking) {
        this.priceBooking = priceBooking;
    }
}

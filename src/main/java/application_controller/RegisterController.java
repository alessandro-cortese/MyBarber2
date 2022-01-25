package application_controller;

import engineering.pattern.factory.Factory;
import model.Barber;
import model.Customer;
import model.User;

public class RegisterController {
    private User customer;
    private User barber;

    public RegisterController(){
        Factory factory = new Factory();
        this.customer = factory.createCustomer();
        this.barber = factory.createBarber();
    }
}

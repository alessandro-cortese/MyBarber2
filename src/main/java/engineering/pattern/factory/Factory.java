package engineering.pattern.factory;

import model.Barber;
import model.Customer;
import model.User;

public class Factory {
    public User createBarber() {
        return new Barber();
    }


    public User createCustomer() {
        return new Customer();
    }
}

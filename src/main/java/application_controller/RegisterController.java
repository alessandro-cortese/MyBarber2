package application_controller;

import engineering.bean.UserBean;
import engineering.dao.BarberDAO;
import engineering.dao.CustomerDAO;
import engineering.dao.UserDAO;
import engineering.pattern.factory.Factory;
import model.Barber;
import model.Customer;
import model.User;

public class RegisterController {
    private User customer;
    private User barber;
    Factory factory;

    public RegisterController(){
        factory = new Factory();

    }

    public void register(UserBean userBean) {
        int type = userBean.getUserType();
        String name = userBean.getName();
        String surname = userBean.getSurname();
        String email = userBean.getUserEmail();
        String pass = userBean.getPass();
        if(type == 1) {
            this.barber = factory.createBarber(name, surname, email, pass);
            BarberDAO barberDAO = new BarberDAO();
            barberDAO.insertBarber(barber);

        }
        else {
            this.customer = factory.createCustomer(name, surname, email, pass);
            CustomerDAO customerDAO = new CustomerDAO();
            customerDAO.insertCustomer(customer);
        }


    }
}

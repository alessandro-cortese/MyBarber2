package application_controller;

import engineering.bean.UserBean;
import engineering.dao.BarberDAO;
import engineering.dao.CustomerDAO;
import engineering.dao.UserDAO;
import engineering.pattern.factory.Factory;
import first_view.general.InternalBackController;
import model.Barber;
import model.Customer;
import model.User;

public class RegisterController {

    Factory factory;

    public RegisterController(){
        factory = new Factory();

    }

    public void register(UserBean userBean) {
        User customer;
        User barber ;
        int type = userBean.getUserType();
        String name = userBean.getName();
        String surname = userBean.getSurname();
        String email = userBean.getUserEmail();
        String pass = userBean.getPass();
        if(type == 1) {
            barber = factory.createBarber(name, surname, email, pass);
            BarberDAO barberDAO = new BarberDAO();
            barberDAO.insertBarber(barber);

        }
        else {
            customer = factory.createCustomer(name, surname, email, pass);
            CustomerDAO customerDAO = new CustomerDAO();
            customerDAO.insertCustomer(customer);
        }

        InternalBackController.getInternalBackControllerInstance().setLoggedUser(userBean);
    }
}

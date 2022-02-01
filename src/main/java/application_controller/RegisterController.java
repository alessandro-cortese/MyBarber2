package application_controller;

import engineering.bean.UserBean;
import engineering.dao.BarberDAO;
import engineering.dao.CustomerDAO;
import engineering.exception.InvalidCredentialsException;
import engineering.pattern.factory.Factory;
import first_view.general.InternalBackController;
import javafx.scene.control.Alert;
import model.User;

public class RegisterController {

    Factory factory;

    public RegisterController(){
        factory = new Factory();

    }

    public void register(UserBean userBean) throws InvalidCredentialsException {
        User customer;
        User barber ;
        int type = userBean.getUserType();
        String name = userBean.getName();
        String surname = userBean.getSurname();
        String email = userBean.getUserEmail();
        boolean flag = isValidEmailAddress(email);
        if(!flag){
            throw new InvalidCredentialsException("email non idonea");
        }
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

    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


}

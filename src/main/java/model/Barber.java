package model;

import engineering.bean.UserBean;

public class Barber extends User {

    public Barber(String email, String pass, String name, String surname) {

        super(email, pass, name, surname);

    }

    public Barber(UserBean userBean) {
        super(userBean.getUserEmail(), userBean.getPass(), userBean.getName(), userBean.getSurname());
    }
}

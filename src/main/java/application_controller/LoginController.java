package application_controller;

import engineering.bean.AccessInfoBean;
import engineering.bean.UserBean;
import engineering.dao.UserDAO;
import model.User;

import javax.annotation.Nullable;

public class LoginController {

    @Nullable
    public UserBean verifyUser(AccessInfoBean accessInfoBean) {
        UserDAO userDAO = new UserDAO() ;
        Integer userType = userDAO.loadUserByCredentials(accessInfoBean.getUserEmail(), accessInfoBean.getUserPassword());
        UserBean userBean = null ;
        if (userType != -1) {
            userBean = new UserBean(accessInfoBean.getUserEmail(), userType) ;
        }
        return userBean ;
    }
}

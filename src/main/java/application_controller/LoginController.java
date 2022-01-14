package application_controller;

import engineering.bean.AccessInfoBean;
import engineering.bean.UserBean;
import engineering.dao.UserDAO;
import engineering.exception.NotExistentUserException;
import model.User;

import javax.annotation.Nullable;

public class LoginController {

    public UserBean verifyUser(AccessInfoBean accessInfoBean) throws NotExistentUserException {
        UserDAO userDAO = new UserDAO() ;
        Integer userType = userDAO.loadUserByCredentials(accessInfoBean.getUserEmail(), accessInfoBean.getUserPassword());
        UserBean userBean = new UserBean(accessInfoBean.getUserEmail(), userType) ;
        return userBean ;
    }
}

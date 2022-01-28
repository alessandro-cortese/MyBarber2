package boundary;

import engineering.bean.ServiceBean;
import engineering.bean.UserBean;
import engineering.pattern.observer.Observer;

import java.io.*;
import java.util.List;

public class AddServiceBoundarySendEmail implements Observer {

    private ServiceBean serviceBean;
    private List<UserBean> userBeans;

    public AddServiceBoundarySendEmail(ServiceBean serviceBean){

        this.serviceBean = serviceBean;

    }

    @Override
    public void update()  {

        try(PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter( "EmailAddressFile.txt")))){

            printWriter.println(serviceBean.getNameInfo());

            for(UserBean userBean : userBeans) {

                printWriter.println(userBean.getUserEmail());

            }


        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setUserBeans(List<UserBean> userBeans) {

        this.userBeans = userBeans;

    }

    public List<UserBean> getUserBeans() {

        return userBeans;

    }

    public ServiceBean getServiceBean() {

        return serviceBean;

    }

    public void setServiceBean(ServiceBean serviceBean) {

        this.serviceBean = serviceBean;

    }

}
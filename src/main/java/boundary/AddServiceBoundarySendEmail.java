package boundary;

import engineering.bean.ServiceBean;
import engineering.bean.UserBean;
import engineering.pattern.observer.Observer;

import java.util.List;

public class AddServiceBoundarySendEmail implements Observer {

    private ServiceBean serviceBean;
    private List<UserBean> userBeans;

    public AddServiceBoundarySendEmail(ServiceBean serviceBean){
        this.serviceBean = serviceBean;
    }

    @Override
    public void update() {

    }

    public void setUserBeans(List<UserBean> userBeans) {
        this.userBeans = userBeans;
    }
}

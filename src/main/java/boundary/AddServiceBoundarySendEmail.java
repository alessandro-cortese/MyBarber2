package boundary;

import engineering.bean.ServiceBean;
import engineering.pattern.observer.Observer;

public class AddServiceBoundarySendEmail implements Observer {

    private ServiceBean serviceBean;

    public AddServiceBoundarySendEmail(ServiceBean serviceBean){
        this.serviceBean = serviceBean;
    }

    @Override
    public void update() {
        System.out.println("Send email");
        System.out.println(serviceBean.getName());
    }

}

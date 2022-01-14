package application_controller;

import engineering.bean.ServiceBean;
import engineering.bean.UserBean;
import engineering.container.ServiceCatalogue;
import engineering.dao.ServiceDAO;
import engineering.exception.InsertNegativePriceException;
import engineering.exception.NegativePriceException;
import model.Service;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ManageServiceController {

    private UserBean userBean;


    public List<ServiceBean> getAllService() throws InsertNegativePriceException {

        ServiceDAO serviceDAO = new ServiceDAO(userBean.getUserEmail());
        ServiceCatalogue catalogueService = serviceDAO.loadServices();
        List<Service> services = catalogueService.getServices();
        String nameOfUsedProduct;
        ArrayList<ServiceBean> serviceBeanList = new ArrayList<>();

        for (Service service : services){

            if(service.getServiceUsedProduct() == null){
                nameOfUsedProduct = "";
            }
            else {
                nameOfUsedProduct = service.getServiceUsedProduct().getName();
            }

            serviceBeanList.add(new ServiceBean(service.getServiceName(), service.getServiceDescription(), nameOfUsedProduct, service.getServicePrice()));
        }

        return serviceBeanList;

    }

    public void setUserBean(UserBean userBean) {

        this.userBean = userBean;

    }

}

package application_controller;

import engineering.bean.ServiceBean;
import engineering.container.ServiceCatalogue;
import engineering.dao.ServiceDAO;
import engineering.exception.NegativePriceException;
import model.Service;

import java.util.ArrayList;
import java.util.List;

public class ManageServiceController {

    public List<ServiceBean> getAllService() throws NegativePriceException {

        ServiceDAO serviceDAO = new ServiceDAO();
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

}

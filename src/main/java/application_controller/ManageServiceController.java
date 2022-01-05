package application_controller;

import engineering.bean.ServiceBean;
import engineering.container.CatalogueService;
import engineering.dao.ServiceDAO;
import engineering.exception.NegativePriceException;
import model.Service;

import java.util.ArrayList;
import java.util.List;

public class ManageServiceController {

    public List<ServiceBean> getAllService() throws NegativePriceException {

        ServiceDAO serviceDAO = new ServiceDAO();
        CatalogueService catalogueService = serviceDAO.loadAllService();
        List<Service> services = catalogueService.getServices();
        String nameOfUsedProduct;
        ArrayList<ServiceBean> serviceBeanList = new ArrayList<>();

        for (Service service : services){

            if(service.getUsedProduct() == null){
                nameOfUsedProduct = "";
            }
            else {
                nameOfUsedProduct = service.getUsedProduct().getName();
            }

            serviceBeanList.add(new ServiceBean(service.getName(), service.getDescription(), nameOfUsedProduct, service.getPrice()));
        }

        return serviceBeanList;

    }

}

package applicationController;

import engineering.bean.ServiceBean;
import engineering.container.CatalogueService;
import model.Service;
import model.buyProduct.Product;

import java.util.ArrayList;

public class AddServiceController {

    public AddServiceController(){
        ;
    }

    public void addService(ServiceBean serviceBean) {

        Service service;
        Product localProduct = new Product();
        localProduct.setName(serviceBean.getNameOfUsedProduct());
        service = new Service(serviceBean.getName(), serviceBean.getDescription(), serviceBean.getPrice(), localProduct);
        CatalogueService catalogueService = new CatalogueService();
        catalogueService.addService(service);
        System.out.println("Added");

    }


}

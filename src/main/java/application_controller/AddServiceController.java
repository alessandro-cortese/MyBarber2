package application_controller;

import engineering.bean.ServiceBean;
import engineering.container.CatalogueService;
import model.Service;
import model.buy_product.Product;

public class AddServiceController {

    public void addService(ServiceBean serviceBean) {

        Service service;
        Product localProduct = new Product();
        localProduct.setName(serviceBean.getNameOfUsedProduct());

        service = new Service(serviceBean.getName(), serviceBean.getDescription(), serviceBean.getPrice(), localProduct);
        CatalogueService catalogueService = new CatalogueService();
        catalogueService.addService(service);

    }

}

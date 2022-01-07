package application_controller;

import boundary.AddServiceBoundarySendEmail;
import engineering.bean.ServiceBean;
import engineering.container.CatalogueService;
import engineering.dao.ProductDAO;
import engineering.exception.ProductNotFoundException;
import model.Service;
import model.buy_product.Product;

public class AddServiceController {

    public void addService(ServiceBean serviceBean) {

        ProductDAO productDAO = new ProductDAO();
        Product localProduct;
        Service service;
        AddServiceBoundarySendEmail addServiceBoundarySendEmail = new AddServiceBoundarySendEmail(serviceBean);


        try{
            localProduct = productDAO.loadProductByName(serviceBean.getName());
        }catch (ProductNotFoundException e){
            localProduct = null;
        }

        serviceBean.attach(addServiceBoundarySendEmail);

        service = new Service(serviceBean.getName(), serviceBean.getDescription(), serviceBean.getPrice(), localProduct);
        CatalogueService catalogueService = new CatalogueService();
        catalogueService.addService(service);

        serviceBean.notifyChanges();

    }

}

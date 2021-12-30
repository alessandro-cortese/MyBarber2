package applicationController;

import engineering.bean.ServiceBean;
import engineering.container.CatalogueService;
import model.Service;
import model.buyProduct.Product;

public class AddServiceController {

    private Service service;
    private CatalogueService catalogueService;

    public AddServiceController(ServiceBean serviceBean) {

        Product localProduct = new Product();
        localProduct.setName(serviceBean.getNameOfUsedProduct());
        service = new Service(serviceBean.getName(), serviceBean.getDescription(), serviceBean.getPrice(), localProduct);

    }

    @Override
    public String toString() {
        return service.getName() + service.getDescription();
    }

}

package engineering.container;

import model.Service;
import model.buyProduct.Product;
import javax.annotation.Nullable;
import java.util.List;

public class CatalogueService {
    private List<Service> service;

    public CatalogueService(List<Service> service){
        this.service=service;
    }

    public CatalogueService(){
        this.service=null;
    }

    public Service createService(String name, String description, double price, Product usedProduct) {
         return new Service(name, description, price, usedProduct);
    }

    public void addService(Service newService) {
        service.add(newService);
    }

    public void addService(String name, String description, double price, Product usedProduct) {
        Service localService = new Service(name, description, price, usedProduct);
        service.add(localService);
    }

    @Nullable
    public Service getServiceByName (String serviceName) {
        for(Service service : service) {
            if(service.getName().compareTo(serviceName) == 0) {
                return service;
            }
        }
        return null;
    }

    public boolean removeService (String serviceName) {
        for (Service localService : service) {
            if(localService.getName().compareTo(serviceName) == 0) {
                service.remove(localService);
                return true;
            }
        }
        return false;
    }


    public boolean modifyService(String nameOfServiceToModify, String newName, String newDescription, double newPrice, Product newUsedProduct) {
        boolean flag = false;
        Service localService = getServiceByName(nameOfServiceToModify);
        if(localService != null) {
            localService.setName(newName);
            localService.setDescription(newDescription);
            localService.setPrice(newPrice);
            localService.setUsedProduct(newUsedProduct);
            flag = true;
        }
        return flag;
    }
}
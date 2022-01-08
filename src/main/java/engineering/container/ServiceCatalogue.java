package engineering.container;

import model.Service;
import model.buy_product.Product;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ServiceCatalogue {
    private List<Service> serviceArrayList;

    public ServiceCatalogue(List<Service> service){

        this.serviceArrayList = service;

    }

    public ServiceCatalogue(){

        this.serviceArrayList = new ArrayList<>();

    }

    public List<Service> getServices(){

        return this.serviceArrayList;

    }

    public Service createService(String name, String description, double price, Product usedProduct) {

         return new Service(name, description, price, usedProduct);

    }

    public void addService(Service newService) {

        serviceArrayList.add(newService);

    }

    public void addService(String name, String description, double price, Product usedProduct) {

        Service localService = new Service(name, description, price, usedProduct);
        serviceArrayList.add(localService);

    }

    @Nullable
    public Service getServiceByName (String serviceName) {

        for(Service service : serviceArrayList) {
            if(service.getName().compareTo(serviceName) == 0) {
                return service;
            }
        }
        return null;

    }

    public boolean removeService (String serviceName) {

        for (Service localService : serviceArrayList) {
            if(localService.getName().compareTo(serviceName) == 0) {
                serviceArrayList.remove(localService);
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
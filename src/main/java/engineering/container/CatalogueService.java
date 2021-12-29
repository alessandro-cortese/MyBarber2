package engineering.container;

import model.Service;

import java.util.List;

public class CatalogueService {
    private List<Service> service;

    public CatalogueService(List<Service> service){
        this.service=service;
    }
    public CatalogueService(){
        this.service=null;
    }

}

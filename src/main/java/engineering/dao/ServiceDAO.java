package engineering.dao;

import engineering.container.CatalogueService;

public class ServiceDAO {

    public CatalogueService loadAllService() {
        CatalogueService catalogueService = new CatalogueService();
        catalogueService.addService("Taglio", "Taglio dei capelli", 10.0D, null);
        catalogueService.addService("Taglio della barba", "Taglio della barba", 7.00D, null);

        return catalogueService;
    }

}

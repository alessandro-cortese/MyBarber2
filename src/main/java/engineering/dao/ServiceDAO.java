package engineering.dao;

import engineering.container.CatalogueService;

public class ServiceDAO {

    public CatalogueService loadAllService() {
        CatalogueService catalogueService = new CatalogueService();
        catalogueService.addService("Taglio", "Taglio dei capelli", 10.0D, null);

        return catalogueService;
    }

}

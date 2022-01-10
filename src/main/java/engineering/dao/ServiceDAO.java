package engineering.dao;

import engineering.container.ServiceCatalogue;
import engineering.dao.queries.Queries;
import engineering.pattern.Connector;
import model.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceDAO {

    private static final String SERVICE_NAME_COL_NAME = "name";
    private static final String SERVICE_DESCRIPTION_COL_NAME = "description";
    private static final String SERVICE_PRICE_COL_NAME = "price";
    private static final String SERVICE_BARBER_COL_NAME = "barber";

    public ServiceCatalogue loadAllService() {

        ServiceCatalogue serviceCatalogue = new ServiceCatalogue();
        serviceCatalogue.addService("Taglio", "Taglio dei capelli", 10.0D, null);
        serviceCatalogue.addService("Taglio della barba", "Taglio della barba", 7.00D, null);

        return serviceCatalogue;

    }




}

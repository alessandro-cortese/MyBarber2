package engineering.dao;

import engineering.container.CatalogueService;
import engineering.dao.queries.Queries;
import engineering.exception.ServiceNotFoundException;
import engineering.pattern.Connector;
import model.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceDAO {

    private static Connection connection;
    private static final String NAME_COL_LABEL = "name";
    private static final String DESCRIPTION_COL_NAME = "description";
    private static final String PRICE_COL_NAME = "price";


    public ServiceDAO(){
        connection = Connector.getConnectorInstance().getConnection();
    }

    public CatalogueService loadAllService() {
        CatalogueService catalogueService = new CatalogueService();
        catalogueService.addService("Taglio", "Taglio dei capelli", 10.0D, null);
        catalogueService.addService("Taglio della barba", "Taglio della barba", 7.00D, null);

        return catalogueService;
    }


    /*public Service loadServiceByName(String serviceName) throws ServiceNotFoundException{
            Service service = null;
            try(Statement statement = connection.createStatement();
                ResultSet resultSet = Queries.loadServiceByName(statement, serviceName))

            {
                if(resultSet.isFirst()) {
                    service = createService(resultSet);
                }
                else {
                    throw new ServiceNotFoundException(String.format("Servizio con nome '%s' non trovato!", serviceName));
                }


            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
    }

    private Service createService(ResultSet resultSet) {

    }
    */

}

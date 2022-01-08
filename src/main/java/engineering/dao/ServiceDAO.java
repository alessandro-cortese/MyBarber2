package engineering.dao;

import engineering.container.ServiceCatalogue;
import engineering.pattern.Connector;

import java.sql.Connection;

public class ServiceDAO {

    private static final String NAME_COL_LABEL = "name";
    private static final String DESCRIPTION_COL_NAME = "description";
    private static final String PRICE_COL_NAME = "price";


    public ServiceDAO(){

    }

    public ServiceCatalogue loadAllService() {

        Connection connection = Connector.getConnectorInstance().getConnection();
        ServiceCatalogue serviceCatalogue = new ServiceCatalogue();
        serviceCatalogue.addService("Taglio", "Taglio dei capelli", 10.0D, null);
        serviceCatalogue.addService("Taglio della barba", "Taglio della barba", 7.00D, null);

        return serviceCatalogue;

    }


    /*

    public Service saveService(Service service) throws ServiceNotFoundException{

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

    */


    /*
    private Service createService(ResultSet resultSet) {

    }


     */

}

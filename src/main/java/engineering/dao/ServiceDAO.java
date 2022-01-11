package engineering.dao;

import engineering.container.ServiceCatalogue;
import engineering.dao.queries.Queries;
import engineering.pattern.Connector;
import model.Service;
import model.buy_product.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {

    private static final String SERVICE_NAME_COL_NAME = "name";
    private static final String SERVICE_DESCRIPTION_COL_NAME = "description";
    private static final String SERVICE_PRICE_COL_NAME = "price";
    //private static final String SERVICE_BARBER_COL_NAME = "barber";

    public ServiceCatalogue loadServices() {

        ServiceCatalogue serviceCatalogue = new ServiceCatalogue();
        serviceCatalogue.addService("Taglio", "Taglio dei capelli", 10.0D, null);
        serviceCatalogue.addService("Taglio della barba", "Taglio della barba", 7.00D, null);

        return serviceCatalogue;

    }

    public boolean insertService(Service service, String barberEmail){

        boolean flag = false;
        Connection connection = Connector.getConnectorInstance().getConnection();

        try(Statement statement = connection.createStatement()) {

            int result = Queries.saveService(statement, barberEmail, service.getName(), service.getDescription(), service.getPrice());

            flag = result > 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return flag;

    }


    public ServiceCatalogue loadAllService(String barberEmail) {

        ServiceCatalogue serviceCatalogue = new ServiceCatalogue();
        List<Service> services = new ArrayList<>();

        Connection connection = Connector.getConnectorInstance().getConnection();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = Queries.loadServicesByBarberEmail(statement, barberEmail)) {

            while(resultSet.next()) {

                //Service service = createService(resultSet);
                //services.add(service);

            }



        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }

        return serviceCatalogue;

    }

    /*private Service createService(ResultSet resultSet) throws SQLException {

        String serviceName = resultSet.getString(SERVICE_NAME_COL_NAME);
        String serviceDescription = resultSet.getString(SERVICE_DESCRIPTION_COL_NAME);
        Double servicePrice = resultSet.getDouble(SERVICE_PRICE_COL_NAME);



    }


    private String name;
    private String description;
    private double price;
    private Product usedProduct;
 */


}

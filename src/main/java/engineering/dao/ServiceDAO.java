package engineering.dao;

import engineering.container.ServiceCatalogue;
import engineering.dao.queries.Queries;
import engineering.pattern.Connector;
import model.Service;
import model.buy_product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {

    private static final String SERVICE_NAME_COL_NAME = "name";
    private static final String SERVICE_DESCRIPTION_COL_NAME = "description";
    private static final String SERVICE_PRICE_COL_NAME = "price";

    private String barberEmail;
    private List<Service> serviceList;


    public ServiceDAO() {
    }

    public void setBarberEmail(String barberEmail){

        this.barberEmail = barberEmail;

    }

    public String getBarberEmail(){

        return this.barberEmail;

    }

    public ServiceCatalogue loadServices() {

        ServiceCatalogue serviceCatalogue = new ServiceCatalogue();
        serviceCatalogue.addService("Taglio", "Taglio dei capelli", 10.0D, null);
        serviceCatalogue.addService("Taglio della barba", "Taglio della barba", 7.00D, null);

        return serviceCatalogue;

    }

    public int insertService(Service service, String barberEmail){

        int newKeys = -1;
        Connection connection = Connector.getConnectorInstance().getConnection();

        try(PreparedStatement statement = connection.prepareStatement("INSERT Service(name, description, price, barber) VALUES(?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, service.getServiceName());
            statement.setString(2, service.getServiceDescription());
            statement.setDouble(3, service.getServicePrice());
            statement.setString(4, barberEmail);

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next()) {
                newKeys = resultSet.getInt(1);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return newKeys;

    }

    public void insertServiceProduct(Integer serviceId, Integer productId) {

        Connection connection = Connector.getConnectorInstance().getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT SserviceProduct(idService, idProduct) VALUES(?, ?)")) {

            preparedStatement.setInt(1, serviceId);
            preparedStatement.setInt(2, productId);

            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }


    public ServiceCatalogue loadAllService(String barberEmail) {

        ServiceCatalogue serviceCatalogue = new ServiceCatalogue();
        List<Service> services = new ArrayList<>();

        Connection connection = Connector.getConnectorInstance().getConnection();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = Queries.loadServicesByBarberEmail(statement, barberEmail)) {

            while(resultSet.next()) {

                Service service = createService(resultSet);
                services.add(service);

            }


        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }

        serviceCatalogue.setServiceArrayList(services);

        return serviceCatalogue;

    }

    private Service createService(ResultSet resultSet) throws SQLException {

        ProductDAO productDAO = new ProductDAO();
        int serviceId = resultSet.getInt(1);
        int productId;
        Product product;
        String serviceName = resultSet.getString(SERVICE_NAME_COL_NAME);
        String serviceDescription = resultSet.getString(SERVICE_DESCRIPTION_COL_NAME);
        double servicePrice = resultSet.getDouble(SERVICE_PRICE_COL_NAME);
        productId = productDAO.loadIsbnOfUsedProduct(serviceId);

        if(productId != -1){

            product = productDAO.loadProductByIsbn(productId, barberEmail);
            return new Service(serviceName, serviceDescription, servicePrice, product);

        }

        return new Service(serviceName, serviceDescription, servicePrice);

    }

    public List<Service> retreiveService(String saloonName) {
        Connection connection = Connector.getConnectorInstance().getConnection();

        try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = Queries.selectServices(statement, saloonName)) {
            serviceList = new ArrayList<>();
            if(resultSet.first()) {

                while(resultSet.next()){
                    Service service = createServiceCustomer(resultSet);
                    serviceList.add(service);
                }

            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return serviceList;
    }

    private Service createServiceCustomer(ResultSet resultSet) throws SQLException {

        String serviceName = resultSet.getString(SERVICE_NAME_COL_NAME);
        String serviceDescription = resultSet.getString(SERVICE_DESCRIPTION_COL_NAME);
        double servicePrice = resultSet.getDouble(SERVICE_PRICE_COL_NAME);

        return new Service(serviceName, serviceDescription, servicePrice);
    }
}
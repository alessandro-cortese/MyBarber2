package application_controller;

import boundary.ManageServiceBoundarySendEmail;
import engineering.bean.ServiceBean;
import engineering.bean.UserBean;
import engineering.container.ServiceCatalogue;
import engineering.dao.CustomerDAO;
import engineering.dao.ProductDAO;
import engineering.dao.SaloonDAO;
import engineering.dao.ServiceDAO;
import engineering.exception.DuplicatedServiceException;
import engineering.exception.InsertNegativePriceException;
import engineering.exception.ProductNotFoundException;
import model.Barber;
import model.Customer;
import model.Service;
import model.buy_product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManageServiceController {

    private UserBean userBean;


    public List<ServiceBean> getAllService() throws InsertNegativePriceException {

        ServiceDAO serviceDAO = new ServiceDAO();
        ServiceCatalogue catalogueService = serviceDAO.loadAllService(userBean.getUserEmail());
        List<Service> services = catalogueService.getServices();
        String nameOfUsedProduct;
        ArrayList<ServiceBean> serviceBeanList = new ArrayList<>();

        for (Service service : services){

            if(service.getServiceUsedProduct() == null){
                nameOfUsedProduct = "";
            }
            else {
                nameOfUsedProduct = service.getServiceUsedProduct().getName();
            }

            serviceBeanList.add(new ServiceBean(service.getServiceName(), service.getServiceDescription(), nameOfUsedProduct, service.getServicePrice()));
        }

        return serviceBeanList;

    }

    public void deleteService(ServiceBean serviceBeanToDelete) {

        ServiceDAO serviceDAO = new ServiceDAO();
        ProductDAO productDAO;
        int serviceKey = 0;
        Product product = null;

        if(!serviceBeanToDelete.getNameOfUsedProductInfo().equals("")) {

            productDAO = new ProductDAO();
            try {

                product = productDAO.loadProductByName(serviceBeanToDelete.getNameOfUsedProductInfo(), userBean.getUserEmail());

            } catch (ProductNotFoundException e) {

                product = null;

            }

        }

        serviceKey = serviceDAO.loadServiceId(serviceBeanToDelete.getNameInfo(), userBean.getUserEmail());

        if(product != null) {

            serviceDAO.deleteServiceProduct(serviceKey, product.getIsbn());

        }

        serviceDAO.deleteService(serviceKey, userBean.getUserEmail());

    }


    public void addService(ServiceBean serviceBean, UserBean userBean) throws DuplicatedServiceException {


        Product localProduct;
        Service newService;
        Barber barber = new Barber(userBean.getUserEmail(), userBean.getPass(), userBean.getName(), userBean.getSurname());

        int serviceKey;

        ManageServiceBoundarySendEmail addServiceBoundarySendEmail = new ManageServiceBoundarySendEmail(serviceBean);
        ServiceCatalogue serviceCatalogue;

        SaloonDAO saloonDAO = new SaloonDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        ProductDAO productDAO = new ProductDAO();
        ServiceDAO serviceDAO = new ServiceDAO();

        Integer saloonId = saloonDAO.loadIdOfSaloon(userBean.getUserEmail());

        List<UserBean> userBeans = new ArrayList<>();
        List<Customer> customers;

        customers = customerDAO.loadCustomerFromFavoriteSaloon(saloonId);
        serviceCatalogue = serviceDAO.loadAllService(userBean.getUserEmail());

        try{

            localProduct = productDAO.loadProductByName(serviceBean.getNameInfo(), userBean.getUserEmail());

        }catch (ProductNotFoundException e){

            localProduct = null;
            serviceBean.setNameOfUsedProductInfo("");

        }

        for(Customer customer : customers) {

            userBeans.add(new UserBean(customer.getEmail()));

        }

        serviceBean.attach(addServiceBoundarySendEmail);

        newService = new Service(serviceBean.getNameInfo(), serviceBean.getDescriptionInfo(), serviceBean.getPriceInfo(), localProduct);
        newService.setBarber(barber);

        for(Service service : serviceCatalogue.getServices()){

            if(controlDuplicatedService(service, newService)){

                throw new DuplicatedServiceException("Servizio già esistente!");

            }

        }

        serviceKey = serviceDAO.insertService(newService, userBean.getUserEmail()) ;

        if(!(serviceBean.getNameOfUsedProductInfo().equals("")) && localProduct != null) {

            serviceDAO.insertServiceProduct(serviceKey, localProduct.getIsbn());

        }

        if(serviceKey != -1){

            serviceCatalogue.addService(newService);

            addServiceBoundarySendEmail.setUserBeans(userBeans);
            serviceBean.notifyChanges();

        }

    }

    private boolean controlDuplicatedService(Service localService, Service service) {
        return Objects.equals(localService.getServiceName(), service.getServiceName()) && Objects.equals(localService.getServiceDescription(), service.getServiceDescription()) &&
                Objects.equals(localService.getServicePrice(), service.getServicePrice());
    }


    public void setUserBean(UserBean userBean) {

        this.userBean = userBean;

    }

}

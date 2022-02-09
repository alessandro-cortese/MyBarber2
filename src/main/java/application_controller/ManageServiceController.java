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
import engineering.exception.IncorrectFormatException;
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


    public List<ServiceBean> getAllService(UserBean userBean) throws IncorrectFormatException {

        ServiceDAO serviceDAO = new ServiceDAO();
        ServiceCatalogue catalogueService = serviceDAO.loadAllServiceByBarber(userBean.getUserEmail());
        List<Service> services = catalogueService.getServices();
        String nameOfUsedProduct;
        ArrayList<ServiceBean> serviceBeanList = new ArrayList<>();

        for (Service service : services){

            if (service.getServiceUsedProduct() != null) {
                nameOfUsedProduct = service.getServiceUsedProduct().getName();
            } else {
                nameOfUsedProduct = "";
            }

            serviceBeanList.add(new ServiceBean(service.getServiceName(), service.getServiceDescription(), nameOfUsedProduct, String.valueOf(service.getServicePrice())));
        }

        return serviceBeanList;

    }

    public void deleteService(ServiceBean serviceBeanToDelete, UserBean userBean) {

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

    public void addService(ServiceBean serviceBean, UserBean userBean) throws DuplicatedServiceException, InsertNegativePriceException {

        if(serviceBean.getPriceInfo() <= 0.0) {
            throw new InsertNegativePriceException();
        }

        Product localProduct;
        Service newService;
        Barber barber = new Barber(userBean.getUserEmail(), userBean.getPass(), userBean.getName(), userBean.getSurname());

        int serviceKey;

        ManageServiceBoundarySendEmail addServiceBoundarySendEmail = new ManageServiceBoundarySendEmail(serviceBean, userBean.getUserEmail());
        ServiceCatalogue serviceCatalogue;

        SaloonDAO saloonDAO = new SaloonDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        ProductDAO productDAO = new ProductDAO();
        ServiceDAO serviceDAO = new ServiceDAO();

        Integer saloonId = saloonDAO.loadIdOfSaloon(userBean.getUserEmail());

        List<UserBean> userBeans = new ArrayList<>();
        List<Customer> customers;

        customers = customerDAO.loadCustomerFromFavoriteSaloon(saloonId);
        serviceCatalogue = serviceDAO.loadAllServiceByBarber(userBean.getUserEmail());

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

    public void modifyService(ServiceBean oldService, ServiceBean updateService, UserBean userBean) throws InsertNegativePriceException {

        if(updateService.getPriceInfo() <= 0.0) {
            throw new InsertNegativePriceException();
        }

        ServiceDAO serviceDAO = new ServiceDAO();
        ProductDAO productDAO = new ProductDAO();
        Product product;
        int serviceKey;
        boolean modify;

        serviceKey = serviceDAO.loadServiceId(oldService.getNameInfo(), userBean.getUserEmail());

        modify = !this.controlDuplicatedService(oldService, updateService);

        if(modify) {

            serviceDAO.updateService(serviceKey, updateService.getNameInfo(), updateService.getDescriptionInfo(), updateService.getPriceInfo());

        }

        if(oldService.getNameOfUsedProductInfo().compareTo("") != 0 && updateService.getNameOfUsedProductInfo().compareTo("") == 0 && modify) {
            try {

                product = productDAO.loadProductByName(oldService.getNameOfUsedProductInfo(), userBean.getUserEmail());
                serviceDAO.deleteServiceProduct(serviceKey, product.getIsbn());

            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            }

        }
        else if(oldService.getNameOfUsedProductInfo().compareTo("") == 0 && updateService.getNameOfUsedProductInfo().compareTo("") != 0 && modify) {

            try {

                product = productDAO.loadProductByName(updateService.getNameOfUsedProductInfo(), userBean.getUserEmail());
                serviceDAO.insertServiceProduct(serviceKey, product.getIsbn());

            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(oldService.getNameOfUsedProductInfo().compareTo(updateService.getNameOfUsedProductInfo()) != 0) {

            try {

                product = productDAO.loadProductByName(updateService.getNameOfUsedProductInfo(), userBean.getUserEmail());
                serviceDAO.insertServiceProduct(serviceKey, product.getIsbn());

            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    public boolean controlDuplicatedService(Service localService, Service service) {
        return Objects.equals(localService.getServiceName(), service.getServiceName()) && Objects.equals(localService.getServiceDescription(), service.getServiceDescription()) &&
                Objects.equals(localService.getServicePrice(), service.getServicePrice()) && Objects.equals(localService.getServiceUsedProduct(), service.getServiceUsedProduct());
    }

    public boolean controlDuplicatedService(ServiceBean localService, ServiceBean service) {
        return Objects.equals(localService.getNameInfo(), service.getNameInfo()) && Objects.equals(localService.getDescriptionInfo(), service.getDescriptionInfo()) &&
                Objects.equals(localService.getPriceInfo(), service.getPriceInfo()) && Objects.equals(localService.getNameOfUsedProductInfo(), service.getNameOfUsedProductInfo());
    }

}

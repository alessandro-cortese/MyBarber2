package application_controller;

import boundary.AddServiceBoundarySendEmail;
import engineering.bean.ServiceBean;
import engineering.bean.UserBean;
import engineering.container.ServiceCatalogue;
import engineering.dao.CustomerDAO;
import engineering.dao.ProductDAO;
import engineering.dao.SaloonDAO;
import engineering.dao.ServiceDAO;
import engineering.exception.DuplicatedServiceException;
import engineering.exception.ProductNotFoundException;
import model.Customer;
import model.Service;
import model.buy_product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddServiceController {

    public void addService(ServiceBean serviceBean, UserBean userBean) throws DuplicatedServiceException {


        Product localProduct;
        Service newService;

        int serviceKey;

        AddServiceBoundarySendEmail addServiceBoundarySendEmail = new AddServiceBoundarySendEmail(serviceBean);
        ServiceCatalogue serviceCatalogue;

        SaloonDAO saloonDAO = new SaloonDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        ProductDAO productDAO = new ProductDAO();
        ServiceDAO serviceDAO = new ServiceDAO();
        serviceDAO.setBarberEmail(userBean.getUserEmail());

        Integer saloonId = saloonDAO.loadIdOfSaloon(userBean.getUserEmail());

        List<UserBean> userBeans = new ArrayList<>();
        List<Customer> customers;

        customers = customerDAO.loadCustomerFromFavoriteSaloon(saloonId);
        serviceCatalogue = serviceDAO.loadServices();

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

        for(Service service : serviceCatalogue.getServices()){

            if(Objects.equals(service.getServiceName(), newService.getServiceName()) && Objects.equals(service.getServiceDescription(), newService.getServiceDescription()) &&
                    Objects.equals(service.getServicePrice(), newService.getServicePrice())){

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

}

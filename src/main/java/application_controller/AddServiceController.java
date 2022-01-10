package application_controller;

import boundary.AddServiceBoundarySendEmail;
import engineering.bean.ServiceBean;
import engineering.bean.UserBean;
import engineering.container.ServiceCatalogue;
import engineering.dao.CustomerDAO;
import engineering.dao.ProductDAO;
import engineering.dao.SaloonDAO;
import engineering.dao.ServiceDAO;
import engineering.exception.ProductNotFoundException;
import model.Customer;
import model.Service;
import model.User;
import model.buy_product.Product;

import java.util.ArrayList;
import java.util.List;

public class AddServiceController {

    public void addService(ServiceBean serviceBean, UserBean userBean) {


        Product localProduct;
        Service service;

        AddServiceBoundarySendEmail addServiceBoundarySendEmail = new AddServiceBoundarySendEmail(serviceBean);

        SaloonDAO saloonDAO = new SaloonDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        ProductDAO productDAO = new ProductDAO();
        ServiceDAO serviceDAO = new ServiceDAO();

        Integer saloonId = saloonDAO.loadIdOfSaloon(userBean.getUserEmail());

        List<UserBean> userBeans = new ArrayList<>();
        List<Customer> customers;

        customers = customerDAO.loadCustomerFromFavoriteSaloon(saloonId);

        try{

            localProduct = productDAO.loadProductByName(serviceBean.getName());

        }catch (ProductNotFoundException e){

            localProduct = null;
            serviceBean.setNameOfUsedProduct("");

        }

        for(Customer customer : customers) {

            userBeans.add(new UserBean(customer.getEmail()));

        }

        serviceBean.attach(addServiceBoundarySendEmail);

        service = new Service(serviceBean.getName(), serviceBean.getDescription(), serviceBean.getPrice(), localProduct);

        ServiceCatalogue serviceCatalogue = new ServiceCatalogue();
        serviceCatalogue.addService(service);

        addServiceBoundarySendEmail.setUserBeans(userBeans);
        serviceBean.notifyChanges();

    }

}

package test;

import application_controller.ManageServiceController;
import engineering.bean.ServiceBean;
import engineering.bean.UserBean;
import engineering.container.ServiceCatalogue;
import engineering.exception.DuplicatedServiceException;
import engineering.exception.InsertNegativePriceException;
import model.Service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestManageService {
    /*@author Testing:  Alessandro Cortese
                        Matricola 0267355
    */

    private static final String SERVICE_TEST_NAME = "Taglio";

    private ServiceBean firstServiceBean;
    private ServiceBean secondServiceBean;

    @Test
    public void duplicatedServiceTest() {

        boolean flag;
        boolean serviceBeanFlag = true;
        ManageServiceController manageServiceController = new ManageServiceController();
        try {
            this.createServiceBean();
        } catch (InsertNegativePriceException e) {
            serviceBeanFlag = false;
        }

        flag = manageServiceController.controlDuplicatedService(firstServiceBean, secondServiceBean);

        assertTrue(flag);
        assertTrue(serviceBeanFlag);
    }

    @Test
    public void addServiceTest(){

        UserBean barber = new UserBean("barber");
        boolean flag = true;
        ManageServiceController manageServiceController = new ManageServiceController();
        ServiceCatalogue serviceCatalogue = new ServiceCatalogue();
        Service newService = new Service(SERVICE_TEST_NAME, "Taglio della barba", 7.0);
        List<Service> serviceList;

        try {
            firstServiceBean = new ServiceBean(SERVICE_TEST_NAME, "Taglio della barba", "", 7.0);
        } catch (InsertNegativePriceException e) {
            flag = false;
        }

        try {
            manageServiceController.addService(firstServiceBean, barber);
        } catch (DuplicatedServiceException e) {
            flag = false;
        }

        serviceList = serviceCatalogue.getServices();

        for(Service service : serviceList) {

            if(service.getServiceName().compareTo(newService.getServiceName()) == 0 && service.getServiceDescription().compareTo(newService.getServiceDescription()) == 0 && Objects.equals(service.getServicePrice(), newService.getServicePrice())) {
                flag = true;
            }

        }

        assertTrue(flag);

    }

    @Test
    public void modifyServiceTest() {

        UserBean barber = new UserBean("barber");
        boolean flag = true;
        boolean modifiedServiceFlag = true;
        ServiceCatalogue serviceCatalogue = new ServiceCatalogue();
        List<Service> services;
        try {
            this.createServiceBean();
        } catch (InsertNegativePriceException e) {
            flag = false;
        }
        Service modifiedService = new Service(SERVICE_TEST_NAME, "Taglio della barba dopo il taglio dei capelli", 9.0);
        ManageServiceController manageServiceController = new ManageServiceController();


        try {
            secondServiceBean.setPriceInfo(9.0);
            secondServiceBean.setDescriptionInfo("Taglio della barba dopo il taglio dei capelli");
        } catch (InsertNegativePriceException e) {
            flag = false;
        }

        manageServiceController.modifyService(firstServiceBean, secondServiceBean, barber.getUserEmail());

        services = serviceCatalogue.getServices();

        for(Service service : services) {

            if(service.getServiceName().compareTo(modifiedService.getServiceName()) != 0 && service.getServiceDescription().compareTo(modifiedService.getServiceDescription()) != 0 && !Objects.equals(service.getServicePrice(), modifiedService.getServicePrice())) {

                modifiedServiceFlag = false;

            }

        }

        assertTrue(flag);
        assertTrue(modifiedServiceFlag);
    }

    @Test
    public void deleteServiceTest(){

        UserBean userBean = new UserBean("barber");
        boolean flag = true;
        ManageServiceController manageServiceController = new ManageServiceController();
        ServiceCatalogue serviceCatalogue = new ServiceCatalogue();

        try{
            this.createServiceBean();
        }catch(InsertNegativePriceException exception){
            flag = false;
        }

        List<Service> services;
        Service deletedService = new Service(SERVICE_TEST_NAME, "Taglio dei capelli", 10.0);

        manageServiceController.deleteService(secondServiceBean, userBean);

        services = serviceCatalogue.getServices();

        for(Service service : services) {

            if(service.getServiceName().compareTo(deletedService.getServiceName()) == 0 && service.getServiceDescription().compareTo(deletedService.getServiceDescription()) == 0 && Objects.equals(service.getServicePrice(), deletedService.getServicePrice())) {
                flag = false;
            }

        }

        assertTrue(flag);
    }


    private void createServiceBean() throws InsertNegativePriceException {

        firstServiceBean = new ServiceBean(SERVICE_TEST_NAME, "Taglio dei capelli", "", 10.0);
        secondServiceBean = new ServiceBean(SERVICE_TEST_NAME, "Taglio dei capelli", "", 10.0);

    }

}

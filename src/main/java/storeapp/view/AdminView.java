package storeapp.view;

import storeapp.domain.Admin;
import storeapp.domain.Customer;
import storeapp.services.input.AdminService;
import storeapp.services.input.CustomerAdminService;

import java.util.List;

public class AdminView {

    private final AdminService adminService;
    private final CustomerAdminService  custumerAdminService;
    private final Admin admin;


    public AdminView(AdminService adminService , Admin admin , CustomerAdminService custumerAdminService) {
        this.adminService = adminService;
        this.admin = admin;
        this.custumerAdminService = custumerAdminService;
    }

    public void createAdmin(){
        adminService.createAdmin(admin);
    }


    public void getAllCustomers(){

        List<Customer> customers = custumerAdminService.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer.getName() + " " + customer.getLastName());
        }
    }

    public void deleteCustomerById(int  id){
        custumerAdminService.deleteCustomer(id);
    }

}

package storeapp.services.outputport;

import storeapp.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerPersistencePort {


    Customer saveCustomer(Customer customer);
    List<Customer> findAllCustomers();
    Optional<Customer> findCustomerById(int id);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(int id);


}

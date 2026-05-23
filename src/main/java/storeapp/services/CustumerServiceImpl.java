package storeapp.services;

import storeapp.domain.Customer;
import storeapp.services.input.CustumerService;
import storeapp.services.outputport.CustomerPersistencePort;
import storeapp.utils.FormValidation;

import java.util.Optional;
import java.util.Scanner;

public class CustumerServiceImpl implements CustumerService {


    //Ahora vamos a comunicar las clases , para eso vamos a crear una instancia de la capa inmediatamente anterior
    private final CustomerPersistencePort customerRepository;


    public CustumerServiceImpl(  CustomerPersistencePort customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(int id, String name, String lastName, String email, String password, boolean status, double quote, String customerType) {

        Customer customer = new Customer(id, name, lastName, email, password, status, quote, customerType);

        return customerRepository.saveCustomer(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findCustomerById(id);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {


        return Optional.empty();
    }


    @Override
    public Customer updateCustomer(int id, String name, String lastName, String email, String password, boolean status, double quote, String customerType) {

        Optional<Customer> customerOpt = customerRepository.findCustomerById(id);

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();

            System.out.println("Actualizar 1. id 2. Nombre 3 Apellido 4.Correo 5. Contraseña");
            int option = FormValidation.validateInt("Opcion");

            switch (option) {
                case 1:
                    customer.setId(FormValidation.validateInt("Actualizar id"));
                    break;
                case 2:
                    customer.setName(FormValidation.validateString("Actualizar nombre"));
                    break;
                case 3:
                    customer.setLastName(FormValidation.validateString("Actualizar Apellido"));
                    break;
                case 4:
                    customer.setEmail(FormValidation.validateString("Actualizar Email"));
                    break;
                case 5:
                    customer.setPassword(FormValidation.validateString("Actualizar contraseña"));
                    break;
                default:
                    System.out.println("Seleccione una opcion valida");
                    break;
            }

            // Since the customer object is a reference to the one in the repository list,
            // updating it here updates it in the list. No need to call repository update.
            return customer;
        } else {
            System.out.println("Cliente no encontrado");
            return null;
        }
    }


    @Override
    public void deleteCustomer(int id) {

    }
}

package storeapp.config;

import storeapp.domain.Admin;
import storeapp.persistence.database.DataBaseConnectionMySql;
import storeapp.persistence.mapper.*;
import storeapp.persistence.repository.*;
import storeapp.services.*;
import storeapp.services.input.*;
import storeapp.services.outputport.CategoryPersistencePort;
import storeapp.services.outputport.CustomerPersistencePort;
import storeapp.services.outputport.OrderPersistencePort;
import storeapp.services.outputport.ProductPersistencePort;
import storeapp.userinterface.MenuApp;
import storeapp.view.*;

import java.sql.Connection;

public class Config {

    public static MenuApp createMenuApp(){

        // esto es un patron Simple Factory, se encarga de crear los objetos necesarios para la aplicacion, y devolver un objeto MenuApp con todos los objetos necesarios para la aplicacion,
        // esto es para evitar tener que crear los objetos en el main, y tener
        // todo el codigo de creacion de objetos en un solo lugar, y asi poder cambiar la implementacion de los objetos sin tener que cambiar el codigo del main,
        //  por ejemplo si queremos cambiar la implementacion de CustomerServiceImpl por otra implementacion, solo tenemos que cambiar el codigo de esta clase,
        //  y no tenemos que cambiar el codigo del main, esto es una buena practica de programacion, ya que nos permite tener un codigo mas limpio y mantenible.

        Admin admin = new Admin();

        Connection connection = DataBaseConnectionMySql.getInstance().getConnection();

        CustomerRowMapper rowMapperCustomer = new CustomerRowMapper();
        CategoryRowMapper rowMapperCategory = new CategoryRowMapper();
        ProductRowMapper rowMapperProduct = new ProductRowMapper();
        OrderRowMapper rowMapperOrder = new OrderRowMapper();

        CategoryPersistencePort categoryRepositoryDB = new CategoryRepositoryAdapterMySql(connection, rowMapperCategory);
        CategoryService categoryService = new CategoryServiceImpl(categoryRepositoryDB);
        CategoryView categoryView = new CategoryView(categoryService);


        ProductPersistencePort productPersistencePort = new ProductRepositoryAdapterMySql(connection, rowMapperProduct);
        ProductService productService = new ProductServiceImpl(productPersistencePort, categoryRepositoryDB);
        ProductView productView = new ProductView(productService);


        CustomerPersistencePort customerRepository = new CustomerRepositoryArray();
        CustomerPersistencePort customerRepositoryDB = new CustomerRepositoryAdapterMySql(connection, rowMapperCustomer);
        CustumerService customerService = new CustumerServiceImpl(customerRepositoryDB);
        CustomerView customerView = new CustomerView( customerService);

        CustomerAdminService custumerAdminService = new AdminServiceImpl(admin, customerRepositoryDB);
        AdminService adminServiceImpl = new AdminServiceImpl(admin,customerRepositoryDB);
        AdminView adminView = new AdminView(adminServiceImpl, admin,custumerAdminService);

        OrderPersistencePort orderRepositoryDB = new OrderRepositoryAdapterMySql(connection, rowMapperOrder);
        OrderService orderService = new OrderServiceImpl(orderRepositoryDB,customerRepositoryDB, productPersistencePort);
        OrderView orderView = new OrderView(orderService);


        return new MenuApp(customerView, adminView,categoryView, productView, orderView);

    }










}

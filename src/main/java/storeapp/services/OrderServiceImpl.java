package storeapp.services;

import storeapp.domain.Customer;
import storeapp.domain.Order;
import storeapp.domain.Product;
import storeapp.services.input.OrderService;
import storeapp.services.outputport.CustomerPersistencePort;
import storeapp.services.outputport.OrderPersistencePort;
import storeapp.services.outputport.ProductPersistencePort;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private final OrderPersistencePort orderPersistencePort;
    private final CustomerPersistencePort customerPersistencePort;
    private final ProductPersistencePort productPersistencePort;

    public OrderServiceImpl(OrderPersistencePort orderPersistencePort, CustomerPersistencePort customerPersistencePort, ProductPersistencePort productPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.customerPersistencePort = customerPersistencePort;
        this.productPersistencePort = productPersistencePort;
    }


    @Override
    public Order createOrder(LocalDate localDate, int customerId, int productId, int quantity, String paidMethod, String orderStatus) {

        Customer customer = customerPersistencePort
                .findCustomerById(customerId)
                .orElseThrow();

        customer.setId(customerId);

        Product product= productPersistencePort
                .findProductById(productId)
                .orElseThrow();

        product.setIdProduct(productId);

        double totalPrice = product.getPrice() * quantity;


        Order order = new Order(localDate, customer, product, quantity, totalPrice, paidMethod, orderStatus);
        return orderPersistencePort.saveOrder(order);
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Order> getAllOrders() {

        List<Order> orders = OrderPersistencePort.


        return List.of();
    }

    @Override
    public Order updateOrder(int id, Date date, int customer, int product, int quantity, String paidMethod, String orderStatus) {
        return null;
    }

    @Override
    public void deleteOrder(int id) {

    }
}

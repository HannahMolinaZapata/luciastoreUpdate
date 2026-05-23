package storeapp.services.outputport;

import storeapp.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderPersistencePort {

    Order saveOrder(Order order);
    Optional<Order> findOrderById(int id);
    List<Order> findAllOrders();
    Order updateOrder(Order order);
    void deleteOrder(int id);

}

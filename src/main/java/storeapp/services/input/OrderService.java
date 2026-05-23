package storeapp.services.input;

import storeapp.domain.Order;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order createOrder(LocalDate date, int customerId , int productId, int quantity, String paidMethod, String orderStatus);
    Optional<Order> getOrderById(int id);
    List<Order> getAllOrders();
    Order updateOrder(int id, Date date, int customer , int product, int quantity, String paidMethod, String orderStatus);
    void deleteOrder(int id);
}

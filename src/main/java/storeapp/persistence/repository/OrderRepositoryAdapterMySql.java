package storeapp.persistence.repository;

import storeapp.domain.Order;
import storeapp.persistence.mapper.OrderRowMapper;
import storeapp.services.outputport.OrderPersistencePort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryAdapterMySql implements OrderPersistencePort {

    private final Connection connection;
    private final OrderRowMapper orderRowMapper;

    public OrderRepositoryAdapterMySql(Connection connection, OrderRowMapper orderRowMapper) {
        this.connection = connection;
        this.orderRowMapper = orderRowMapper;
    }



    @Override
    public Order saveOrder(Order order) {

        String sql = "INSERT INTO orders (date_order , customer, product, quantity,total, paid_method ,order_state) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){

            setOrderParams(ps, order);
            ps.executeUpdate();

            var keys = ps.getGeneratedKeys();
            if(keys.next()){
                order.setOrderId(keys.getInt(1));
            }

        }catch (SQLException e){
            throw new RuntimeException("Error al guardar orden: " + e.getMessage(), e);

        }

        return order;
    }

    @Override
    public Optional<Order> findOrderById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAllOrders() {
        return List.of();
    }

    @Override
    public Order updateOrder(Order order) {
        return null;
    }

    @Override
    public void deleteOrder(int id) {

    }


    private void setOrderParams(PreparedStatement ps, Order order) throws SQLException {
        ps.setDate(1, order.getOrderDate());
        ps.setInt(2, order.getCustomer().getId());
        ps.setInt(3, order.getProduct().getIdProduct());
        ps.setInt(4, order.getQuantity());
        ps.setDouble(5, order.getTotalPrice());
        ps.setString(6, order.getPaidMethod());
        ps.setString(7, order.getOrderStatus());
    }




}

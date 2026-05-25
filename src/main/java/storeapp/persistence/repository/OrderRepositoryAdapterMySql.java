package storeapp.persistence.repository;

import storeapp.domain.Order;
import storeapp.persistence.mapper.OrderRowMapper;
import storeapp.services.outputport.OrderPersistencePort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

        String sql = "INSERT INTO purchase_order(date_order , customer, product, quantity,total, paid_method ,order_state) VALUES (?, ?, ?, ?, ?, ?, ?)";

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

        String sql = """
    SELECT 
        o.id_order,
        o.date_order,
        o.quantity,
        o.total,
        o.paid_method,
        o.order_state,
        c.id_customer            AS customer_id,
        c.name          AS customer_name,
        c.last_name     AS customer_last_name,
        c.email         AS customer_email,
        p.id_product    AS product_id,
        p.description   AS product_description,
        p.price         AS product_price,
        p.stock         AS product_stock
    FROM purchase_order o
    JOIN customer c ON o.customer = c.id_customer
    JOIN product  p ON o.product  = p.id_product
    WHERE o.id_order = ?
    """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(orderRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar producto por id: " + e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> findAllOrders() {

        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM purchase_order";

        try(PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs= ps.executeQuery()){

            while (rs.next()){
                orders.add(orderRowMapper.mapRow(rs));
            }

        }catch (SQLException e){
            throw new RuntimeException("Error al obtener las ordenes: " + e.getMessage(), e);
        }

        return orders;
    }

    @Override
    public Order updateOrder(Order order) {
        return null;
    }

    @Override
    public void deleteOrder(int id) {

    }


    private void setOrderParams(PreparedStatement ps, Order order) throws SQLException {
        ps.setDate(1, java.sql.Date.valueOf(order.getOrderDate()));
        ps.setInt(2, order.getCustomer().getId());
        ps.setInt(3, order.getProduct().getIdProduct());
        ps.setInt(4, order.getQuantity());
        ps.setDouble(5, order.getTotalPrice());
        ps.setString(6, order.getPaidMethod());
        ps.setString(7, order.getOrderStatus());
    }
}

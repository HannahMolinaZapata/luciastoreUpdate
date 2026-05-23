package storeapp.persistence.mapper;

import storeapp.domain.Customer;
import storeapp.domain.Order;
import storeapp.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper {

    @Override
    public Order mapRow(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("id_order"));
        order.setOrderDate(rs.getDate("date_order").toLocalDate());

        Customer customer = new Customer();
        customer.setId(rs.getInt("id_customer"));
        order.setCustomer(customer);

        Product product = new Product();
        product.setIdProduct(rs.getInt("id_product"));
        order.setProduct(product);

        order.setQuantity(rs.getInt("quantity"));
        order.setTotalPrice(rs.getDouble("total_price"));
        order.setPaidMethod(rs.getString("paid_method"));
        order.setOrderStatus(rs.getString("order_status"));


        return order;
    }
}

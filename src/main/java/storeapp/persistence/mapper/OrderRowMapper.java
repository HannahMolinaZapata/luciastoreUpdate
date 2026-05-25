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
        customer.setId(rs.getInt("customer_id"));
        customer.setName(rs.getString("customer_name"));
        customer.setLastName(rs.getString("customer_last_name"));
        customer.setEmail(rs.getString("customer_email"));
        order.setCustomer(customer);

        Product product = new Product();
        product.setIdProduct(rs.getInt("product_id"));
        product.setDescription(rs.getString("product_description"));
        product.setPrice(rs.getDouble("product_price"));
        product.setStock(rs.getInt("product_stock"));
        order.setProduct(product);

        order.setQuantity(rs.getInt("quantity"));
        order.setTotalPrice(rs.getDouble("total"));
        order.setPaidMethod(rs.getString("paid_method"));
        order.setOrderStatus(rs.getString("order_state"));

        return order;
    }
}

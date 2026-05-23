package storeapp.persistence.mapper;

import storeapp.domain.Category;
import storeapp.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper{
    @Override
    public Product mapRow(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setIdProduct(rs.getInt("id_product"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        product.setStock(rs.getInt("stock"));
        product.setState(rs.getBoolean("state"));

        Category category = new Category();
        category.setIdCategory(rs.getInt("id_category"));
        product.setCategory(category);

        return product;
    }

}

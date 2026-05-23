package storeapp.persistence.mapper;

import storeapp.domain.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper{
    @Override
    public Category mapRow(ResultSet rs) throws SQLException {

        Category category = new Category();
        category.setIdCategory(rs.getInt("id_category"));
        category.setDescription(rs.getString("cat_name"));
        category.setState(rs.getString("cat_status"));
        return category;
    }

}

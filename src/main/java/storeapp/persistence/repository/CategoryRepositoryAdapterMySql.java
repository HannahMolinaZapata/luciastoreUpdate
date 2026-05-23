package storeapp.persistence.repository;

import storeapp.domain.Category;
import storeapp.persistence.mapper.CategoryRowMapper;
import storeapp.services.outputport.CategoryPersistencePort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryAdapterMySql implements CategoryPersistencePort {

    private final Connection connection;
    private final CategoryRowMapper rowMapper;


    public CategoryRepositoryAdapterMySql(Connection connection, CategoryRowMapper rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    @Override
    public Category saveCategory(Category category) {

        String sql = "INSERT INTO category (id_category, cat_name, cat_status) VALUES (?, ?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){

            setCategoryParams(ps, category);
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Error al guardar categoría: " + e.getMessage(), e);
        }

        return category;
    }

    @Override
    public Optional<Category> findCategoryById(int id) {

        String sql = "SELECT * FROM category WHERE id_category = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return Optional.of(rowMapper.mapRow(rs));
            }

        }catch(SQLException e){
            throw new RuntimeException("Error al obtener la categoria" + e.getMessage() , e);
        }

        return Optional.empty();
    }

    @Override
    public List<Category> findAllCategories() {

        List<Category> categories = new ArrayList<>();

        String sql = "SELECT * FROM category";

        try(PreparedStatement ps = connection.prepareStatement(sql);ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                categories.add(rowMapper.mapRow(rs));
            }

        }catch (SQLException e){
            throw new RuntimeException("Error al obtener las categorias" + e.getMessage() , e);
        }

        return categories;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public void deleteCategory(int id) {

        String sql = "DELETE FROM category WHERE id_category = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Error al eliminar la categoria" + e.getMessage() , e);
        }

    }


    private void setCategoryParams(PreparedStatement ps, Category category) throws SQLException {
        ps.setInt(1, category.getIdCategory());
        ps.setString(2, category.getDescription());
        ps.setString(3, category.getState());
    }




}

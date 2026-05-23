package storeapp.persistence.repository;

import storeapp.domain.Category;
import storeapp.domain.Product;
import storeapp.persistence.database.DataBaseConnectionMySql;
import storeapp.persistence.mapper.ProductRowMapper;
import storeapp.services.outputport.ProductPersistencePort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryAdapterMySql implements ProductPersistencePort {

    private final Connection connection;
    private final ProductRowMapper mapper;

    public ProductRepositoryAdapterMySql(Connection connection, ProductRowMapper mapper) {
        this.connection = DataBaseConnectionMySql.getInstance().getConnection();
        this.mapper     = mapper;
    }

    @Override
    public Product saveProduct(Product product) {
        String sql = """
                INSERT INTO product (description, price, stock, state, category)
                VALUES (?, ?, ?, ?, ?)
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setProductParams(ps, product);
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                product.setIdProduct(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar producto: " + e.getMessage(), e);
        }
        return product;
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                products.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los productos: " + e.getMessage(), e);
        }
        return products;
    }

    @Override
    public Optional<Product> findProductById(int id) {
        String sql = "SELECT * FROM product WHERE id_product = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar producto por id: " + e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public Product updateProduct(Product product, Category category) {
        String sql = """
                UPDATE product
                SET description=?, price=?, stock=?, state=?, category=?
                WHERE id_product=?
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setProductParams(ps, product);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar producto: " + e.getMessage(), e);
        }
        return product;
    }

    @Override
    public void deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE id_product = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar producto: " + e.getMessage(), e);
        }
    }

    // ── helper privado para no repetir los setters en save y update ──
    private void setProductParams(PreparedStatement ps, Product product) throws SQLException {
        ps.setString(1, product.getDescription());
        ps.setDouble(2, product.getPrice());
        ps.setInt(3, product.getStock());
        ps.setBoolean(4, product.isState());
        ps.setInt(5, product.getCategory().getIdCategory());
    }
}

package storeapp.persistence.repository;

import storeapp.domain.Customer;
import storeapp.persistence.mapper.CustomerRowMapper;
import storeapp.persistence.mapper.RowMapper;
import storeapp.services.outputport.CustomerPersistencePort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepositoryAdapterMySql implements CustomerPersistencePort {


    private final Connection dbConnection;
    private final CustomerRowMapper rowMapper;

    public CustomerRepositoryDB(Connection dbConnection, CustomerRowMapper rowMapper) {
        this.dbConnection = dbConnection;
        this.rowMapper = rowMapper;
    }


    @Override
    public Customer saveCustomer(Customer customer) {

        String sql = "INSERT INTO customer (id_customer,name, last_name, email, password, status, quote, customer_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement ps = dbConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){

            setCustomerParams(ps, customer);
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next()){
                customer.setId(keys.getInt(1));
            }

        }catch (SQLException e){
            throw new RuntimeException("Error al guardar cliente: " + e.getMessage(), e);
        }
        return customer;
    }

    @Override
    public List<Customer> findAllCustomers() {

        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try(PreparedStatement ps = dbConnection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                customers.add(rowMapper.mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public Optional<Customer> findCustomerById(int id) {

        String sql = "SELECT * FROM customer WHERE id_customer = ?";

        try(PreparedStatement ps = dbConnection.prepareStatement(sql)){

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return Optional.of(rowMapper.mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cliente por id: " + e.getMessage(),e);
        }

        return Optional.empty();
    }

    @Override
    public Customer updateCustomer(int id , Customer customer) {
        String sql = """
                UPDATE customer
                SET name=?, last_name=?, email=?, password=?, status=?, quote=?, customer_type=?
                WHERE id_customer = ?
                """;
        try (PreparedStatement ps = dbConnection.prepareStatement(sql)) {
            setCustomerParams(ps, customer);
            ps.setInt(8, customer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar cliente: " + e.getMessage(), e);
        }
        return customer;

    }

    @Override
    public void deleteCustomer(int id) {

        String sql = "DELETE FROM customer WHERE id_customer = ?";

        try (PreparedStatement ps = dbConnection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            //return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar cliente: " + e.getMessage(), e);
        }

    }

    private void setCustomerParams(PreparedStatement ps, Customer customer) throws SQLException {
        ps.setInt(1, customer.getId());
        ps.setString(2, customer.getName());
        ps.setString(3, customer.getLastName());
        ps.setString(4, customer.getEmail());
        ps.setString(5, customer.getPassword());
        ps.setBoolean(6, customer.isStatus());
        ps.setDouble(7, customer.getQuote());
        ps.setString(8, customer.getCustomerType());
    }
}

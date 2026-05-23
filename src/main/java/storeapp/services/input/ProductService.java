package storeapp.services.input;

import storeapp.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product createProduct(String description, double price, int stock, boolean state , int categoryId);
    Optional<Product> getProductById(int id);
    List<Product> getAllProducts();
    Product updateProduct(int id, String name, String description, double price, int stock,int categoryId);
    void deleteProduct(int id);

}

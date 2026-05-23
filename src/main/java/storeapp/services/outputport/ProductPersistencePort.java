package storeapp.services.outputport;

import storeapp.domain.Category;
import storeapp.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductPersistencePort {

    Product saveProduct(Product product);
    Optional<Product> findProductById(int id);
    List<Product> findAllProducts();
    Product updateProduct(Product product, Category category);
    void  deleteProduct(int id);

}

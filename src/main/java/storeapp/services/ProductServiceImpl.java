package storeapp.services;

import storeapp.domain.Category;
import storeapp.domain.Product;
import storeapp.services.input.ProductService;
import storeapp.services.outputport.CategoryPersistencePort;
import storeapp.services.outputport.ProductPersistencePort;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductPersistencePort productPersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;

    public ProductServiceImpl(ProductPersistencePort productPersistencePort, CategoryPersistencePort categoryPersistencePort) {
        this.productPersistencePort = productPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Product createProduct(String description, double price, int stock, boolean state, int categoryId) {

        Category category = categoryPersistencePort
                .findCategoryById(categoryId)
                .orElseThrow();

        category.setIdCategory(categoryId);

        Product product = new Product(description, price, stock, state, category);

        return productPersistencePort.saveProduct(product);
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product updateProduct(int id, String name, String description, double price, int stock, int categoryId) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {

    }

}

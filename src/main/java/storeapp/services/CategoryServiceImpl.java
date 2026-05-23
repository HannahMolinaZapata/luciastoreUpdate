package storeapp.services;

import storeapp.domain.Category;
import storeapp.services.input.CategoryService;
import storeapp.services.outputport.CategoryPersistencePort;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryServiceImpl(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }


    @Override
    public Category createCategory(String name , String status) {

        Category category = new Category(name , status);

        categoryPersistencePort.saveCategory(category);

        return category;
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public Category updateCategory(String name, String status) {
        return null;
    }

    @Override
    public void deleteCategory(int id) {

    }
}

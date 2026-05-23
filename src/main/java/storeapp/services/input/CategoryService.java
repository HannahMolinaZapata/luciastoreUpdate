package storeapp.services.input;

import storeapp.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category createCategory(String name , String status);
    Optional<Category> getCategoryById(int id);
    List<Category> getAllCategories();
    Category updateCategory(String name , String status);
    void deleteCategory(int id);
}

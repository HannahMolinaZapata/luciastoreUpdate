package storeapp.view;

import storeapp.services.CategoryStateSelector;
import storeapp.services.input.CategoryService;
import storeapp.utils.FormValidator;

public class CategoryView {

    private final CategoryService categoryUseCase;

    public CategoryView(CategoryService categoryUseCase){
        this.categoryUseCase = categoryUseCase;
    }

    public void createCategory(){

        String description = FormValidator.validateString("Ingrese la descripción de la categoría: ");
        String status = CategoryStateSelector.CategoryState();

        categoryUseCase.createCategory(description, status);


    }


}

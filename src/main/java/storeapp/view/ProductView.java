package storeapp.view;

import storeapp.services.ProductStateSelector;
import storeapp.services.input.ProductService;
import storeapp.utils.FormValidator;

public class ProductView {

    private final ProductService productUseCase;

    public ProductView(ProductService productUseCase){
        this.productUseCase = productUseCase;
    }

    public void createProduct(){
        System.out.println("Creating product...");

        String description = FormValidator.validateString("Ingrese la descripción del producto: ");
        double price = FormValidator.validateDouble("Ingrese el precio del producto: ");
        int stock = FormValidator.validateInt("Ingrese la cantidad del producto: ");
        boolean status = ProductStateSelector.ProductState();
        int categoryId = FormValidator.validateInt("Ingrese el id de la categoria del producto: ");

        productUseCase.createProduct(description, price, stock, status, categoryId);
    }


}

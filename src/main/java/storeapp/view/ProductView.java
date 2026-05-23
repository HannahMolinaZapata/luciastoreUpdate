package storeapp.view;

import storeapp.services.ProductStateSelector;
import storeapp.services.input.ProductService;
import storeapp.utils.FormValidation;

public class ProductView {

    private final ProductService productUseCase;

    public ProductView(ProductService productUseCase){
        this.productUseCase = productUseCase;
    }

    public void createProduct(){
        System.out.println("Creating product...");

        String description = FormValidation.validateString("Ingrese la descripción del producto: ");
        double price = FormValidation.validateDouble("Ingrese el precio del producto: ");
        int stock = FormValidation.validateInt("Ingrese la cantidad del producto: ");
        boolean status = ProductStateSelector.ProductState();
        int categoryId = FormValidation.validateInt("Ingrese el id de la categoria del producto: ");

        productUseCase.createProduct(description, price, stock, status, categoryId);
    }


}

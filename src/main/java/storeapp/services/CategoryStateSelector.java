package storeapp.services;

import storeapp.domain.enums.CategoryStateEnum;
import storeapp.utils.FormValidator;

public class CategoryStateSelector {

    public static String CategoryState(){

        System.out.println("Selecione el estado de ls categoria: ");
        System.out.println("1. Disponible 2. No disponible 3. Próximamente");
        String value = "";
        int option = FormValidator.validateInt("Seleccione una opcion: ");
        switch (option){
            case 1:
                value = CategoryStateEnum.DISPONIBLE.getDescription();
                break;
            case 2:
                value = CategoryStateEnum.NO_DISPONIBLE.getDescription();
                break;
            case 3:
                value = CategoryStateEnum.PROXIMAMENTE.getDescription();
                break;
            default:
                System.out.println("Opción no válida");

        }

        return value;
    }
}

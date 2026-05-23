package storeapp.services;

import storeapp.domain.enums.PaidMethodEnum;
import storeapp.utils.FormValidation;

public class OrderPaidMethodSelector {


    public static String PaidMethodSelector(){

        System.out.println("Selecione el metodo de pago: ");
        System.out.println("1. Efectivo 2. Transferencia 3. Credito Personal");
        String value = "";
        int option = FormValidation.validateInt("Seleccione una opcion: ");
        switch (option){
            case 1:
                value = PaidMethodEnum.CASH.getDescription();
                break;
            case 2:
                value = PaidMethodEnum.DEBIT_CARD.getDescription();
                break;
            case 3:
                value = PaidMethodEnum.PERSONAL_CREDIT.getDescription();
                break;
            default:
                System.out.println("Opción no válida");

        }

        return value;
    }
}

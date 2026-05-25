package storeapp.services;

import storeapp.domain.enums.OrderStatusEnum;
import storeapp.utils.FormValidator;

public class OrderStateSelector {


    public static String selectOrderState(){

        System.out.println("Selecione el estado de la Orden: ");
        System.out.println("1. Abierta 2. En progreso 3. Cerrada 4.Cancelada");
        String value = "";
        int option = FormValidator.validateInt("Seleccione una opcion: ");
        switch (option){
            case 1:
                value = OrderStatusEnum.OPEN.getDescription();
                break;
            case 2:
                value = OrderStatusEnum.IN_PROGRESS.getDescription();
                break;
            case 3:
                value = OrderStatusEnum.CLOSED.getDescription();
                break;
            case 4:
                value = OrderStatusEnum.CANCELLED.getDescription();
            default:
                System.out.println("Opción no válida");

        }

        return value;
    }

}

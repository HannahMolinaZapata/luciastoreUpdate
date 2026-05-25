package storeapp.view;

import storeapp.domain.Order;
import storeapp.domain.validations.ValidationRules;
import storeapp.services.OrderPaidMethodSelector;
import storeapp.services.OrderStateSelector;
import storeapp.services.input.OrderService;
import storeapp.utils.FormRuleValidator;
import storeapp.utils.FormValidator;
import storeapp.utils.date.DateValidator;

import java.time.LocalDate;

public class OrderView {

    private final OrderService orderUseCase;



    public OrderView(OrderService orderUseCase){
        this.orderUseCase = orderUseCase;
    }

    public void createOrder(){

        LocalDate orderDate = DateValidator.readDate("Ingrese la fecha de la orden (dd/MM/yyyy): ");
        int customerId = FormValidator.validateInt("Ingrese el id del cliente");
        int productId= FormValidator.validateInt("Ingrese el id del producto");
        int quantity = FormRuleValidator.readInt("Ingrese la cantidad de productos", ValidationRules.POSITIVE_NUMBER , "Debe ingresar un cantidad positiva");
        String paidMethod = OrderPaidMethodSelector.PaidMethodSelector();
        String orderState = OrderStateSelector.selectOrderState();
        orderUseCase.createOrder(orderDate, customerId, productId, quantity, paidMethod, orderState);

    }


    public void getOrderById() {

        System.out.println("Buscar orden por id");
        int id_order = FormValidator.validateInt("Ingrese el Id a Buscar");
        Order order = orderUseCase.getOrderById(id_order).orElseThrow();

        System.out.println("Orden encontrada: " + order.getOrderId() + "\n" +
                "Nombre" + order.getCustomer().getName() + "\n" +
                "Apellido" + order.getCustomer().getLastName());
    }


}

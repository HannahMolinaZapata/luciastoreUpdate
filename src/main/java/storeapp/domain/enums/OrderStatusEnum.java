package storeapp.domain.enums;

public enum OrderStatusEnum {
    OPEN("Abierta"),
    IN_PROGRESS("En progreso"),
    CLOSED ("Cerrada"),
    CANCELLED ("Cancelada");

    private final String description;

    OrderStatusEnum(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}

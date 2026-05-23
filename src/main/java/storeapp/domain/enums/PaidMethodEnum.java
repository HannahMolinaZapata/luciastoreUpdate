package storeapp.domain.enums;

public enum PaidMethodEnum {

    CASH("Efectivo"),
    DEBIT_CARD("Tarjeta Debito"),
    CREDIT_CARD("Tarjeta Credito"),
    PERSONAL_CREDIT("Credito directo");

    private final String description;

    PaidMethodEnum(String description){
        this.description = description;

    }

    public String getDescription(){
        return description;
    }



}

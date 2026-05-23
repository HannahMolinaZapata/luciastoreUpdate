package storeapp.domain.enums;

public enum CategoryStateEnum {

    DISPONIBLE("Disponible"),
    NO_DISPONIBLE("No Disponible"),
    PROXIMAMENTE("Próximamente");


    private final String  description;

    CategoryStateEnum(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}

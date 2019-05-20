package model;

import java.util.Objects;

public final class Vehicle implements Cloneable {
    private final String registrationNumber;
    private final String maker;
    private final String model;
    private final VehicleTypes type;
    public static final Vehicle NO_VEHICLE = new Vehicle();

    private Vehicle() {
        this.registrationNumber = "";
        this.maker = "";
        this.model = "";
        this.type = VehicleTypes.NONE;
    }

    public Vehicle(VehicleTypes type) throws IlleagalRegistrationNumberFormat {
        this("", "", "", type);
    }

    public Vehicle(String registrationNumber, String maker, String model, VehicleTypes type) throws IlleagalRegistrationNumberFormat {
        // Исключения isNull
        Objects.requireNonNull(registrationNumber, "registrationNumber - null");
        Objects.requireNonNull(maker, "maker - null");
        Objects.requireNonNull(model, "model - null");
        Objects.requireNonNull(type, "type - null");

        // Исключение illegalRegNumber
        PatternCheck.check(registrationNumber);

        // Конструктор
        this.registrationNumber = registrationNumber;
        this.maker = maker;
        this.model = model;
        this.type = type;
    }

    public String toString() {
        if (type.equals(Vehicle.NO_VEHICLE)) return "NONE";
        else return String.format("%s %s %s regNumber: %s",maker, model, type, registrationNumber);
    }

    public int hashCode() {
        return (maker.hashCode() * model.hashCode() * type.hashCode() * registrationNumber.hashCode());
    }

    public boolean equals(Object object) {
        //todo красавчик, строки сравнивать на ==. - done
        if (this == object) return true;
        return (object instanceof Vehicle &&
                ((Vehicle) object).maker.equals(maker) &&
                ((Vehicle) object).model.equals(model) &&
                ((Vehicle) object).type.equals(type) &&
                ((Vehicle) object).registrationNumber.equals(registrationNumber));
    }

    public Object clone() throws CloneNotSupportedException{
        Vehicle clone = (Vehicle)super.clone();

        return clone;
    }

    public String getRegistrationNumber () {
        return (registrationNumber);
    }

    public String getMaker () {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public VehicleTypes getType() {
        return type;
    }
}

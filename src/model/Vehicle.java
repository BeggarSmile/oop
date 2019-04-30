package model;

public final class Vehicle implements Cloneable {
    private final String registrationNumber;
    private final String maker;
    private final String model;
    private final VehicleTypes type;
    public static final Vehicle NO_VEHICLE = new Vehicle();

    public Vehicle () {
        this("", "", "", VehicleTypes.NONE);
    }

    public Vehicle (String registrationNumber, String maker, String model, VehicleTypes type) {
        this.registrationNumber = registrationNumber;
        this.maker = maker;
        this.model = model;
        this.type = type;
    }

    public String toString() {
        if (type.equals(Vehicle.NO_VEHICLE)) return "NONE";
        else return String.format(maker + " " + model + " (" + type + ") " + "regNumber: " + registrationNumber);
    }

    public int hashCode() {
        return (maker.hashCode() * model.hashCode() * type.hashCode() * registrationNumber.hashCode());
    }

    public boolean equals(Object object) {
        return (object instanceof Vehicle && ((Vehicle) object).maker == maker && ((Vehicle) object).model == model && ((Vehicle) object).type == type && ((Vehicle) object).registrationNumber == registrationNumber);
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

package model;

public final class Vehicle {
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

    public String getRegistrationNumber () {
        return (registrationNumber);
    }

    /*
    public void setRegistrationNumberNumber (String countryNumber) {
        this.registrationNumber = countryNumber;
    }
    */

    public String getMaker () {
        return maker;
    }

    /*
    public void setMaker (String maker) {
        this.maker = maker;
    }
    */

    public String getModel() {
        return model;
    }

    /*
    public void setModel(String model) {
        this.model = model;
    }
    */

    public VehicleTypes getType() {
        return type;
    }
}

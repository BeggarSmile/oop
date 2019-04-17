package model;

public class Vehicle {
    private String registrationNumber;
    private String maker;
    private String model;

    public Vehicle () {
        this("", "", "");
    }

    public Vehicle (String registrationNumber, String maker, String model) {
        this.registrationNumber = registrationNumber;
        this.maker = maker;
        this.model = model;
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
        return (maker);
    }

    /*
    public void setMaker (String maker) {
        this.maker = maker;
    }
    */

    public String getModel() {
        return (model);
    }

    /*
    public void setModel(String model) {
        this.model = model;
    }
    */
}

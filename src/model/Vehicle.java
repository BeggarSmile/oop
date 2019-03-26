package model;
public class Vehicle {
    private String registrationNumber;
    private String maker;
    private String pattern;

    public Vehicle () {
        registrationNumber = "";
        maker = "";
        pattern = "";
    }

    public Vehicle (String registrationNumber, String maker, String pattern) {
        this.registrationNumber = registrationNumber;
        this.maker = maker;
        this.pattern = pattern; //todo стремное имя
    }

    public String getRegistrationNumber () {
        return (registrationNumber);
    }

    public void setRegistrationNumberNumber (String countryNumber) {
        this.registrationNumber = countryNumber;
    }

    public String getMaker () {
        return (maker);
    }

    public void setMaker (String maker) {
        this.maker = maker;
    }

    public String getPattern () {
        return (pattern);
    }

    public void setPattern (String pattern) {
        this.pattern = pattern;
    }
}

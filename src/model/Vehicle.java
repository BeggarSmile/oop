package model;

public class Vehicle {
    String RegistrationNumber;
    String Maker;
    String Pattern;

    public Vehicle () {

    }

    public Vehicle (String RegistrationNumber, String Maker, String Pattern) {
        this.RegistrationNumber = RegistrationNumber;
        this.Maker = Maker;
        this.Pattern = Pattern;
    }

    public String getRegistrationNumber () {
        return (RegistrationNumber);
    }

    public void setCountryNumber (String CountryNumber) {
        this.RegistrationNumber = CountryNumber;
    }

    public String getMaker () {
        return (Maker);
    }

    public void setMaker (String Maker) {
        this.Maker = Maker;
    }

    public String getPattern () {
        return (Pattern);
    }

    public void setPattern (String Pattern) {
        this.Pattern = Pattern;
    }
}

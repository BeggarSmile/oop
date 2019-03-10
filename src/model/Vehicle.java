package model;

public class Vehicle {
    String CountryNumber;
    String Maker;
    String Pattern;

    public Vehicle () {

    }

    public Vehicle (String CountryNumber, String Maker, String Pattern) {
        this.CountryNumber = CountryNumber;
        this.Maker = Maker;
        this.Pattern = Pattern;
    }

    public String getCountryNumber () {
        return (CountryNumber);
    }

    public void setCountryNumber (String CountryNumber) {
        this.CountryNumber = CountryNumber;
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

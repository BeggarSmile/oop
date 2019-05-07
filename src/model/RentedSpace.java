package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class RentedSpace extends AbstractSpace implements Cloneable {

    private LocalDate rentEndsDate;

    public RentedSpace() {
        this(Person.UNKNOWN_PERSON, Vehicle.NO_VEHICLE, LocalDate.now(), LocalDate.now().plusDays(1));
}

    public RentedSpace(Person person, LocalDate sinceDate, LocalDate rentEndsDate) {
        this(person, Vehicle.NO_VEHICLE, sinceDate, rentEndsDate);
    }

    public RentedSpace(Person person, Vehicle vehicle, LocalDate sinceDate, LocalDate rentEndsDate) {
        super(person, vehicle, sinceDate);
        Objects.requireNonNull(rentEndsDate, "rentEndsDate - null");
        this.rentEndsDate = rentEndsDate;
    }

    //todo а где equals?

    public String toString() {
        return String.format("Tenant: " + getPerson().toString() + " TC: " + getVehicle().toString()); //todo это херня, а не формат
    }

    public int hashCode() {
        return (53 * super.hashCode());
    }

    public Object clone() throws CloneNotSupportedException{
        RentedSpace clone = (RentedSpace)super.clone();
        return clone;
    }

    public LocalDate getRentEndsDate() {
        return rentEndsDate;
    }

    public void setRentEndsDate(LocalDate rentEndsDate) {
        Objects.requireNonNull(rentEndsDate, "rentEndsDate - null");
        this.rentEndsDate = rentEndsDate;
    }

    public Period getPeriod() {
        return Period.between(LocalDate.now(), rentEndsDate);
    }


}

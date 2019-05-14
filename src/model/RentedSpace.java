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
        setRentEndsDate(rentEndsDate);
    }

    //todo а где equals? - done

    public boolean equals(Object object) {
        return (object instanceof RentedSpace &&
                ((RentedSpace) object).getPerson().equals(super.getPerson()) &&
                ((RentedSpace) object).getVehicle().equals(super.getVehicle()) &&
                ((RentedSpace) object).getSinceDate().equals(super.getSinceDate()) &&
                ((RentedSpace) object).getPeriod().equals(super.getPeriod()) &&
                ((RentedSpace) object).getRentEndsDate().equals(rentEndsDate));
    }

    public String toString() {
        return String.format("%s %s", super.toString(), rentEndsDate); //todo это херня, а не формат - done
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

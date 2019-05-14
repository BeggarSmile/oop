package model;

import java.time.LocalDate;

public class OwnedSpace extends AbstractSpace implements Cloneable {
    public OwnedSpace() {
        this(Person.UNKNOWN_PERSON, Vehicle.NO_VEHICLE, LocalDate.now());
    }

    public OwnedSpace(Person person) {
        this(person, Vehicle.NO_VEHICLE, LocalDate.now());
    }

    public OwnedSpace(Person person, Vehicle vehicle, LocalDate sinceDate) {
        super(person, vehicle, sinceDate);
    }

    public String toString() {
        return String.format(super.toString());
    }

    public int hashCode() {
        return (71 * super.hashCode());
    }

}

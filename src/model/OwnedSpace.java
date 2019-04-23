package model;

public class OwnedSpace extends AbstractSpace {
    public OwnedSpace() {
        this(Person.UNKNOWN_PERSON, Vehicle.NO_VEHICLE);
    }

    public OwnedSpace(Person person) {
        this(person, Vehicle.NO_VEHICLE);
    }

    public OwnedSpace(Person person, Vehicle vehicle) {
        super(person, vehicle);
    }
}

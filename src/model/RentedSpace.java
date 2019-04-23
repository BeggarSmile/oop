package model;
public class RentedSpace extends AbstractSpace {

    public RentedSpace() {
        this(Person.UNKNOWN_PERSON, Vehicle.NO_VEHICLE);
    }

    public RentedSpace(Person person) {
        super(person, Vehicle.NO_VEHICLE);
    }

    public RentedSpace(Person person, Vehicle vehicle) {
        super(person, vehicle);
    }
}

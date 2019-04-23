package model;

public abstract class AbstractSpace implements Space {
    private Person person;
    private Vehicle vehicle;

    protected AbstractSpace() {
        this(Person.UNKNOWN_PERSON, Vehicle.NO_VEHICLE);
    }

    protected AbstractSpace(Person person) {
        this(person, Vehicle.NO_VEHICLE);
    }

    protected AbstractSpace(Person person, Vehicle vehicle) {
        this.person = person;
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isEmpty() {
        return (this.vehicle == Vehicle.NO_VEHICLE || this.vehicle.getType() == VehicleTypes.NONE);
    }
}

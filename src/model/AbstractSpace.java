package model;

public abstract class AbstractSpace implements Space, Cloneable {
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

    public String toString() {
        return String.format(person.toString() + " " + vehicle.toString());
    }

    public int hashCode() {
        return (person.hashCode() * vehicle.hashCode());
    }

    public boolean equals(Object object) {
        return (object instanceof AbstractSpace && ((AbstractSpace) object).person == person && ((AbstractSpace) object).vehicle == vehicle);
    }

    protected Object clone() throws CloneNotSupportedException{
        AbstractSpace clone = (AbstractSpace)super.clone();
        clone.vehicle = (Vehicle)vehicle.clone();
        clone.person = (Person)person.clone();

        return clone;
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

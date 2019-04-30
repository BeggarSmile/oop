package model;
public class RentedSpace extends AbstractSpace implements Cloneable {

    public RentedSpace() {
        this(Person.UNKNOWN_PERSON, Vehicle.NO_VEHICLE);
    }

    public RentedSpace(Person person) {
        super(person, Vehicle.NO_VEHICLE);
    }

    public RentedSpace(Person person, Vehicle vehicle) {
        super(person, vehicle);
    }

    public String toString() {
        return String.format("Tenant: " + getPerson().toString() + " TC: " + getVehicle().toString());
    }

    public int hashCode() {
        return (53 * super.hashCode());
    }

    public Object clone() throws CloneNotSupportedException{
        RentedSpace clone = (RentedSpace)super.clone();

        return clone;
    }
}

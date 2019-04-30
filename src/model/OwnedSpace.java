package model;

public class OwnedSpace extends AbstractSpace implements Cloneable {
    public OwnedSpace() {
        this(Person.UNKNOWN_PERSON, Vehicle.NO_VEHICLE);
    }

    public OwnedSpace(Person person) {
        this(person, Vehicle.NO_VEHICLE);
    }

    public OwnedSpace(Person person, Vehicle vehicle) {
        super(person, vehicle);
    }

    public String toString() {
        return String.format("Owner: " + getPerson().toString() + " TC: " + getVehicle().toString());
    }

    public int hashCode() {
        return (71 * super.hashCode());
    }

    public Object clone() throws CloneNotSupportedException{
        OwnedSpace clone = (OwnedSpace)super.clone();

        return clone;
    }
}

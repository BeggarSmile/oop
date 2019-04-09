package model;
public class RentedSpace implements Space {
    private Person person;
    private Vehicle vehicle;

    public RentedSpace() {
        this(Person.UNKNOWN_PERSON, null);
    }

    public RentedSpace(Person person, Vehicle vehicle) {
        this.person = person;
        this.vehicle = vehicle;
    }

    public Person getPerson () {
        return person;
    }

    public void setPerson (Person person) {
        this.person = person;
    }

    public Vehicle getVehicle () {
        return vehicle;
    }

    public void setVehicle (Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isEmpty () {
        return (this.vehicle == null || this.vehicle.getRegistrationNumber().isEmpty());
    }

}

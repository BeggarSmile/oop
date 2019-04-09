package model;

public class OwnedSpace implements Space {
    private Person person;
    private Vehicle vehicle;

    public OwnedSpace(Person person, Vehicle vehicle) {
        this.person = person;
        this.vehicle = vehicle;
    }

    public Person getPerson() {
        return person;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isEmpty() {
        return (person == null || vehicle.getRegistrationNumber().equals(""));
    }

}

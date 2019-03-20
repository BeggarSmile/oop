package model;

public class Space {
    private Person person;
    private Vehicle vehicle;

    public Space () {
        this.person = Person.UNKNOWN_PERSON;
        this.vehicle = new Vehicle();
    }

    public Space (Person person, Vehicle vehicle) {
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

}

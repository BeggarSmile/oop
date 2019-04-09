package model;
public class RentedSpace implements Space {
    private Person person;
    private Vehicle vehicle;

    public RentedSpace() {
        //todo вызови второй конструктор this() - done
        this(Person.UNKNOWN_PERSON, new Vehicle());
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
        return (this.vehicle == null || this.vehicle.getRegistrationNumber().equals(""));
    }

}

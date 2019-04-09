package model;

public interface Space {

    Vehicle getVehicle();

    Person getPerson();

    void setVehicle(Vehicle vehicle);

    void setPerson(Person person);

    boolean isEmpty();

}

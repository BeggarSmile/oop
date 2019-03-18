package model;

public class Space {
    Person Person;
    Vehicle Vehicle;

    public Space () {
        Person = new Person();
        Vehicle = new Vehicle();
    }

    public Space (Person Person, Vehicle Vehicle) {
        this.Person = Person;
        this.Vehicle = Vehicle;
    }

    public Person getPerson () {
        return Person;
    }

    public void setPerson (Person Person) {
        this.Person = Person;
    }

    public Vehicle getVehicle () {
        return Vehicle;
    }

    public void setVehicle (Vehicle Vehicle) {
        this.Vehicle = Vehicle;
    }


}

package model;

public class Space {
    Object Person;
    Object Vehicle;

    public Space () {
        Person = new Person();
        Vehicle = new Vehicle();
    }

    public Space (Object Person, Object Vehicle) {
        this.Person = Person;
        this.Vehicle = Vehicle;
    }

    public Object getPerson () {
        return (Person);
    }

    public void setPerson (Object Person) {
        this.Person = Person;
    }

    public Object getVehicle () {
        return (Vehicle);
    }

    public void setVehicle (Object Vehicle) {
        this.Vehicle = Vehicle;
    }


}

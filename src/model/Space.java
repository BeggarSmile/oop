package model;
//todo имена переменных, методов, атрибутов - с маленькой буквы
public class Space {
    //todo private
    private Person person;
    private Vehicle vehicle;

    public Space () {
        //todo this()
        this.person = Person.UNKNOWN_PERSON; //todo безымянная персона
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

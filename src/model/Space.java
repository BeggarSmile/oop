package model;

//todo имена переменных, методов, атрибутов - с маленькой буквы
public class Space {
    //todo private
    Person person;
    Vehicle Vehicle;

    public Space () {
        //todo this()
        person = Person.UNKNOWN_PERSON; //todo безымянная персона
        Vehicle = new Vehicle();
    }

    public Space (Person Person, Vehicle Vehicle) {
        this.person = Person;
        this.Vehicle = Vehicle;
    }

    public Person getPerson () {
        return person;
    }

    public void setPerson (Person Person) {
        this.person = Person;
    }

    public Vehicle getVehicle () {
        return Vehicle;
    }

    public void setVehicle (Vehicle Vehicle) {
        this.Vehicle = Vehicle;
    }


}

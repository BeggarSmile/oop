package model;

public class Person {
    String Name;
    String LastName;

    public Person (String Name, String LastName) {
        this.Name = Name;
        this.LastName = LastName;
    }

    public Person () {

    }

    public static final Person nullPerson = new Person("","");

    public String getName () {
        return (Name);
    }

    public String getLastName () {
        return (LastName);
    }
}

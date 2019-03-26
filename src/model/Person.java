package model;
public class Person {
    private String name; //todo firstName
    private String lastName;

    public Person (String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Person () {

    }

    public static final Person UNKNOWN_PERSON = new Person("","");

    public String getName () {
        return (name);
    }

    public String getLastName () {
        return (lastName);
    }
}

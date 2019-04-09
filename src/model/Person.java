package model;
public class Person {
    //todo firstName - done
    private String firstName;
    private String lastName;

    public Person (String name, String lastName) {
        this.firstName = name;
        this.lastName = lastName;
    }

    public Person () {
        this.firstName = UNKNOWN_PERSON.firstName;
        this.lastName = UNKNOWN_PERSON.lastName;
    }

    public static final Person UNKNOWN_PERSON = new Person("","");

    public String getFirstName() {
        return (firstName);
    }

    public String getLastName () {
        return (lastName);
    }
}

package model;
public final class Person {
    private final String firstName;
    private final String lastName;

    public Person (String name, String lastName) {
        this.firstName = name;
        this.lastName = lastName;
    }

    public static final Person UNKNOWN_PERSON = new Person("","");

    public String getFirstName() {
        return (firstName);
    }

    public String getLastName () {
        return (lastName);
    }
}

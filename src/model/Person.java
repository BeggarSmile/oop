package model;

import java.util.Objects;

public final class Person implements Cloneable{
    private final String firstName;
    private final String lastName;

    public Person (String name, String lastName) throws NullPointerException {
        // Исключения isNull
        Objects.requireNonNull(name, "name - null");
        Objects.requireNonNull(lastName, "lastName - null");

        // Конструктор
        this.firstName = name;
        this.lastName = lastName;
    }

    public static final Person UNKNOWN_PERSON = new Person("","");

    public String toString() {
        return String.format(lastName + " " + firstName);
    }

    public int hashCode() {
        return (lastName.hashCode() * firstName.hashCode());
    }

    public boolean equals(Object object) {
        return (object instanceof Person && ((Person) object).lastName == lastName && ((Person) object).firstName == firstName);
    }

    public Object clone() throws CloneNotSupportedException{
        Person clone = (Person)super.clone();

        return clone;
    }

    public String getFirstName() {
        return (firstName);
    }

    public String getLastName () {
        return (lastName);
    }
}

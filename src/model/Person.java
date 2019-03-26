package model;
//todo имена переменных, методов, атрибутов - с маленькой буквы
public class Person {
    //todo private
    private String name;
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

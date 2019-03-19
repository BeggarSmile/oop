package model;

//todo имена переменных, методов, атрибутов - с маленькой буквы
public class Person {
    //todo private
    String Name;
    String LastName;

    public Person (String Name, String LastName) {
        this.Name = Name;
        this.LastName = LastName;
    }

    public static final Person UNKNOWN_PERSON = new Person("","");

    public String getName () {
        return Name;
    }

    public String getLastName () {
        return LastName;
    }
}

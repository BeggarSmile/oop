import model.*;

public class SecondTest {
    public static void main (String[] args) {
        System.out.println("Я сделяль!");
        lab1tests();
    }

    public static void lab1tests() {
        //Tests class Person
        Person Mike = new Person("Mike","Viazovskiy");
        System.out.println(Mike.getName() + " " + Mike.getLastName());
        Person Lola = new Person("Lola","Hover");

        //Tests class Vehicle
        Vehicle Volga = new Vehicle("A228AA","USSR","Gaz21");
        System.out.println(Volga.getCountryNumber() + " " + Volga.getMaker());
        Volga.setCountryNumber("B228BB");
        System.out.println(Volga.getCountryNumber());
        Vehicle nullAuto = new Vehicle();
        System.out.println(nullAuto.getCountryNumber() + " " + nullAuto.getPattern());

        //Tests Space class
        Space First = new Space(Mike,Volga);
        System.out.println(First.getPerson() + " " + First.getPerson());
        Space Second = new Space();
        System.out.println(Second.getPerson());
        Second.setPerson(Lola);
        System.out.println(Second.getPerson());
    }
}

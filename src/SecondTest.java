import model.*;

import java.sql.SQLOutput;

public class SecondTest {
    public static void main (String[] args) {
        System.out.println("Я сделяль!");
        lab1tests();
    }

    public static void lab1tests() {
        //Tests class Person
        Person Person_1 = new Person ("Name1", "LastName1");
        Person Person_2 = new Person ("Name2", "LastName2");
        Person Person_3 = new Person ("Name3", "LastName3");
        Person Person_4 = new Person ("Name4", "LastName4");
        Person Person_5 = new Person ("Name5", "LastName5");

        //Tests class Vehicle
        Vehicle Vehicle_1 = new Vehicle("RegNumber1", "Maker1", "Pattern1");
        Vehicle Vehicle_2 = new Vehicle("RegNumber2", "Maker2", "Pattern2");
        Vehicle Vehicle_3 = new Vehicle("RegNumber3", "Maker3", "Pattern3");
        Vehicle Vehicle_4 = new Vehicle("RegNumber4", "Maker4", "Pattern4");
        Vehicle Vehicle_5 = new Vehicle("RegNumber5", "Maker5", "Pattern5");

        //Tests class Space
        Space Space_1 = new Space(Person_1, Vehicle_1);
        Space Space_2 = new Space(Person_2, Vehicle_2);
        Space Space_3 = new Space(Person_3, Vehicle_3);
        Space Space_4 = new Space(Person_4, Vehicle_4);
        Space Space_5 = new Space(Person_5, Vehicle_5);

        //Tests class OwnersFloor
        Space[] Spaces_1 = {Space_1, Space_2, Space_3, Space_4, Space_5};
        OwnersFloor OwnersFloor_1 = new OwnersFloor(Spaces_1);
        Space[] Spaces_2 = {Space_1, Space_2, Space_3, Space_4, Space_5};
        OwnersFloor OwnersFloor_2 = new OwnersFloor(Spaces_2);
        Space[] Spaces_3 = {Space_1, Space_2, Space_3, Space_4, Space_5};
        OwnersFloor OwnersFloor_3 = new OwnersFloor(Spaces_3);
        Space[] Spaces_4 = {Space_1, Space_2, Space_3, Space_4, Space_5};
        OwnersFloor OwnersFloor_4 = new OwnersFloor(Spaces_4);
        Space[] Spaces_5 = {Space_1, Space_2, Space_3, Space_4, Space_5};
        OwnersFloor OwnersFloor_5 = new OwnersFloor(Spaces_5);

        //Tests class Parking
        OwnersFloor[] Floors = {OwnersFloor_1, OwnersFloor_2, OwnersFloor_3, OwnersFloor_4, OwnersFloor_5};
        Parking Park = new Parking(Floors);

    }
}

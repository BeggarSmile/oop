import model.*;

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

        //Tests class RentedSpace
        Space rentedSpace_1 = new RentedSpace(Person_1, Vehicle_1);
        Space rentedSpace_2 = new RentedSpace(Person_2, Vehicle_2);
        Space rentedSpace_3 = new RentedSpace(Person_3, Vehicle_3);
        Space rentedSpace_4 = new RentedSpace(Person_4, Vehicle_4);
        Space rentedSpace_5 = new RentedSpace(Person_5, Vehicle_5);

        //Tests class OwnersFloor
        Space[] spaces_1 = {rentedSpace_1, rentedSpace_2, rentedSpace_3, rentedSpace_4, rentedSpace_5};
        RentedSpacesFloor RentedSpacesFloor_1 = new RentedSpacesFloor(spaces_1);
        Space[] spaces_2 = {rentedSpace_1, rentedSpace_2, rentedSpace_3, rentedSpace_4, rentedSpace_5};
        OwnersFloor OwnersFloor_2 = new OwnersFloor(spaces_2);
        Space[] spaces_3 = {rentedSpace_1, rentedSpace_2, rentedSpace_3, rentedSpace_4, rentedSpace_5};
        OwnersFloor OwnersFloor_3 = new OwnersFloor(spaces_3);
        Space[] spaces_4 = {rentedSpace_1, rentedSpace_2, rentedSpace_3, rentedSpace_4, rentedSpace_5};
        OwnersFloor OwnersFloor_4 = new OwnersFloor(spaces_4);
        Space[] spaces_5 = {rentedSpace_1, rentedSpace_2, rentedSpace_3, rentedSpace_4, rentedSpace_5};
        OwnersFloor OwnersFloor_5 = new OwnersFloor(spaces_5);

        //Tests class Parking
        Floor[] Floors = {RentedSpacesFloor_1, OwnersFloor_2, OwnersFloor_3, OwnersFloor_4, OwnersFloor_5};
        Parking Park = new Parking(Floors);
        System.out.println(Park.size());
        System.out.println(Park.getFloors()[0].get(0).getPerson().getFirstName());
        System.out.println(Park.getFloors()[0].remove(0).getPerson().getFirstName());
        System.out.println(Park.getFloors()[0].get(0).getPerson().getFirstName());

    }
}

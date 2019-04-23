import model.*;

import java.sql.SQLOutput;

public class SecondTest {
    public static void main (String[] args) {
        System.out.println("Я сделяль!");
        lab1tests();
    }

    public static void lab1tests() {
        //Tests class Person
        Person person_1 = new Person ("Name1", "LastName1");
        Person person_2 = new Person ("Name2", "LastName2");
        Person person_3 = new Person ("Name3", "LastName3");
        Person person_4 = new Person ("Name4", "LastName4");
        Person person_5 = new Person ("Name5", "LastName5");

        //Tests class Vehicle
        Vehicle vehicle_1 = new Vehicle("RegNumber1", "Maker1", "Pattern1", VehicleTypes.CAR);
        Vehicle vehicle_2 = new Vehicle("RegNumber2", "Maker2", "Pattern2", VehicleTypes.CROSSOVER);
        Vehicle vehicle_3 = new Vehicle("RegNumber3", "Maker3", "Pattern3", VehicleTypes.MOTOR_BIKE);
        Vehicle vehicle_4 = new Vehicle("RegNumber4", "Maker4", "Pattern4", VehicleTypes.SUV);
        Vehicle vehicle_5 = new Vehicle("RegNumber5", "Maker5", "Pattern5", VehicleTypes.TRUCK);

        //Tests class RentedSpace
        Space rentedSpace_1 = new RentedSpace(person_1, vehicle_1);
        Space rentedSpace_2 = new RentedSpace(person_2, vehicle_2);
        Space rentedSpace_3 = new RentedSpace(person_3, vehicle_3);
        Space rentedSpace_4 = new RentedSpace(person_4, vehicle_4);
        Space rentedSpace_5 = new RentedSpace(person_5, vehicle_5);

        //Tests class OwnersFloor
        Space[] spaces_1 = {rentedSpace_1, rentedSpace_2, rentedSpace_3, rentedSpace_4, rentedSpace_5};
        RentedSpacesFloor rentedSpacesFloor_1 = new RentedSpacesFloor(spaces_1);
        Space[] spaces_2 = {rentedSpace_1, rentedSpace_2, rentedSpace_3, rentedSpace_4, rentedSpace_5};
        OwnersFloor ownersFloor_2 = new OwnersFloor(spaces_2);
        Space[] spaces_3 = {rentedSpace_1, rentedSpace_2, rentedSpace_3, rentedSpace_4, rentedSpace_5};
        OwnersFloor ownersFloor_3 = new OwnersFloor(spaces_3);
        Space[] spaces_4 = {rentedSpace_1, rentedSpace_2, rentedSpace_3, rentedSpace_4, rentedSpace_5};
        OwnersFloor ownersFloor_4 = new OwnersFloor(spaces_4);
        Space[] spaces_5 = {rentedSpace_1, rentedSpace_2, rentedSpace_3, rentedSpace_4, rentedSpace_5};
        OwnersFloor ownersFloor_5 = new OwnersFloor(spaces_5);

        //Tests class Parking
        Floor[] Floors = {rentedSpacesFloor_1, ownersFloor_2, ownersFloor_3, ownersFloor_4, ownersFloor_5};
        Parking Park = new Parking(Floors);
        System.out.println(Park.size());
        System.out.println(Park.getFloors()[0].get(0).getPerson().getFirstName());
        System.out.println(Park.getFloors()[0].get(0).getPerson().getFirstName());
        System.out.println(Park.getFloors()[0].remove(0));
        System.out.println(Park.getFloors()[0].get(0).getPerson().getFirstName());
    }
}

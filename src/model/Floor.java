package model;

public interface Floor {
    boolean add(RentedSpace space);

    boolean add(int index, RentedSpace space);

    RentedSpace get(int index);

    RentedSpace get(String registrationNumber);

    boolean hasSpace(String registrationNumber);

    RentedSpace set(int index, RentedSpace space);

    RentedSpace remove(int index);

    RentedSpace remove(String registrationNumber);

    int size();

    RentedSpace[] getSpaces();

    Vehicle[] getVehicles();
}

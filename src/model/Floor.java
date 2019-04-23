package model;

public interface Floor {
    boolean add(Space space);

    boolean add(int index, Space space);

    Space get(int index);

    Space get(String registrationNumber);

    boolean hasSpace(String registrationNumber);

    Space set(int index, Space space);

    Space remove(int index);

    Space remove(String registrationNumber);

    int size();

    Space[] getSpaces();

    Vehicle[] getVehicles();

    int vehiclesQuantity();

    int vehiclesQuantity(VehicleTypes type);

    int indexOf(String registrationNumber);

    Space[] getSpaces(VehicleTypes type);

    Space[] getEmptySpaces();
}

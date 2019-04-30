package model;

public interface Floor {
    String toString();

    int hashCode();

    boolean equals(Object object);

    Object clone() throws CloneNotSupportedException;

    boolean add(Space space);

    boolean add(int index, Space space);

    Space get(int index);

    Space get(String registrationNumber);

    int getIndex(Space space);

    boolean hasSpace(String registrationNumber);

    Space set(int index, Space space);

    Space remove(int index);

    Space remove(String registrationNumber);

    boolean remove(Space space);

    int size();

    Space[] getSpaces();

    int getSpaces(Person person);

    Vehicle[] getVehicles();

    int vehiclesQuantity();

    int vehiclesQuantity(VehicleTypes type);

    int indexOf(String registrationNumber);

    Space[] getSpaces(VehicleTypes type);

    Space[] getEmptySpaces();
}

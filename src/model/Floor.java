package model;

import java.time.LocalDate;

public interface Floor extends java.lang.Comparable<Floor>{

    String toString();

    int hashCode();

    boolean equals(Object object);

    Object clone() throws CloneNotSupportedException;

    LocalDate nearestRentEndsDate();

    Space spaceWithNearestRentEndsDate();

    boolean add(Space space);

    boolean add(int index, Space space);

    Space get(int index);

    Space get(String registrationNumber) throws IlleagalRegistrationNumberFormat;

    int getIndex(Space space) throws IlleagalRegistrationNumberFormat;

    boolean hasSpace(String registrationNumber) throws IlleagalRegistrationNumberFormat;

    Space set(int index, Space space);

    Space remove(int index);

    Space remove(String registrationNumber) throws IlleagalRegistrationNumberFormat;

    boolean remove(Space space) throws IlleagalRegistrationNumberFormat;

    int size();

    Space[] getSpaces();

    int getSpaces(Person person);

    Vehicle[] getVehicles();

    int vehiclesQuantity();

    int vehiclesQuantity(VehicleTypes type);

    int indexOf(String registrationNumber) throws IlleagalRegistrationNumberFormat;

    Space[] getSpaces(VehicleTypes type);

    Space[] getEmptySpaces();
}

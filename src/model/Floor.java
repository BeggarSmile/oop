package model;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public interface Floor extends Comparable<Floor>, Iterable<Space>{

    String toString();

    int hashCode();

    boolean equals(Object object);

    Object clone() throws CloneNotSupportedException;

    default LocalDate nearestRentEndsDate() {
        LocalDate minDate = null;
        for (Space space : this) {
            if (space instanceof RentedSpace) {
                if (minDate == null) minDate = space.getSinceDate();
                else if (minDate.isBefore(space.getSinceDate()))
                    minDate = space.getSinceDate();
            }
        }
        if (minDate == null) throw new NoSuchElementException();
        return minDate;
    }

    default Space spaceWithNearestRentEndsDate() {
        LocalDate minDate = nearestRentEndsDate();

        for (Space space : this) {
            if (space instanceof RentedSpace)
                if (space.getSinceDate().isEqual(minDate))
                    return space;
        }

        throw new NoSuchElementException();
    }

    boolean add(Space space);

    boolean add(int index, Space space);

    Space get(int index);

    default Space get(String registrationNumber) throws IlleagalRegistrationNumberFormat{
        PatternCheck.check(registrationNumber);
        for (Space space : this) {
            if (space.getVehicle().getRegistrationNumber().equals(registrationNumber))
                return space;
        }
        throw new NoSuchElementException();
    }

    int getIndex(Space space) throws IlleagalRegistrationNumberFormat;

    default boolean hasSpace(String registrationNumber) throws IlleagalRegistrationNumberFormat {
        PatternCheck.check(registrationNumber);
        for (Space space : this) {
            if (space.getVehicle().getRegistrationNumber().equals(registrationNumber))
                return true;
        }
        return false;
    }

    Space set(int index, Space space);

    Space remove(int index);

    default Space remove(String registrationNumber) throws IlleagalRegistrationNumberFormat {
        int index = indexOf(registrationNumber);
        if (index != -1) return remove(index);
        throw new NoSuchElementException();
    }

    boolean remove(Space space) throws IlleagalRegistrationNumberFormat;

    int size();

    Space[] getSpaces();

    default int getSpaces(Person person) {
        Objects.requireNonNull(person, "person - null");
        int count = 0;
        for (Space space : this) {
            if (space.getPerson().equals(person)) count++;
        }
        return count;
    }

    default Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[vehiclesQuantity()];
        int index = 0;

        for (Space space : this) {
            if (!space.isEmpty()) {
                vehicles[index] = space.getVehicle();
                index++;
            }
        }
        return vehicles;
    }

    int vehiclesQuantity();

    int vehiclesQuantity(VehicleTypes type);

    default int indexOf(String registrationNumber) throws IlleagalRegistrationNumberFormat {
        PatternCheck.check(registrationNumber);
        int index = 0;
        for (Space space : this) {
            if (space.getVehicle().getRegistrationNumber().equals(registrationNumber))
                return index;
            index++;
        }
        return -1;
    }

    default Space[] getSpaces(VehicleTypes type) {
        Space[] spaces = new Space[vehiclesQuantity(type)];
        int index = 0;
        for (Space space : this) {
            if (space.getVehicle().getType().equals(type)) {
                spaces[index] = space;
                index++;
            }
        }
        return spaces;
    }

    default Space[] getEmptySpaces() {
        Space[] spaces = new Space[size() - vehiclesQuantity()];
        int index = 0;
        for (Space space : this) {
            if (space.isEmpty()) {
                spaces[index] = space;
                index++;
            }
        }
        return spaces;
    }
}

package model;

import java.time.LocalDate;
import java.util.*;

public interface Floor extends Comparable<Floor>, Iterable<Space>, Collection<Space> {

    default boolean isEmpty() {
        return size() == 0;
    }

    default boolean addAll(Collection<? extends Space> collection) {
        for (Space space : collection) {
            add(space);
        }
        return true;
    }

    default boolean removeAll(Collection<?> collection) {
        boolean flags = false;
        for (Object o : collection) {
            if (remove(o))
                flags = true;
        }
        return flags;
    }

    boolean retainAll(Collection<?> collection)
    default void clear() {
        for (int i = 0; i < size(); i++) {
            remove(0);
        }
    }

    String toString();

    int hashCode();

    boolean equals(Object object);

    Object clone() throws CloneNotSupportedException;

    default LocalDate nearestRentEndsDate() {
        return spaceWithNearestRentEndsDate().getSinceDate();
    }

    default Space spaceWithNearestRentEndsDate() {
        Space minDate = null;
        for (Space space : this) {
            if (space instanceof RentedSpace) {
                if (minDate == null) minDate = space;
                else if (minDate.getSinceDate().isBefore((space).getSinceDate()))
                    minDate = space;
            }
        }
        if (minDate == null) throw new NoSuchElementException();
        return minDate;
    }

    boolean add(Space space);

    boolean add(int index, Space space);

    Space get(int index);

    default Space get(String registrationNumber) throws IlleagalRegistrationNumberFormat{
        PatternCheck.check(registrationNumber);
        for (Object space : this) {
            if (((Space)space).getVehicle().getRegistrationNumber().equals(registrationNumber))
                return ((Space)space);
        }
        throw new NoSuchElementException();
    }

    int getIndex(Space space) throws IlleagalRegistrationNumberFormat;

    default boolean hasSpace(String registrationNumber) throws IlleagalRegistrationNumberFormat {
        PatternCheck.check(registrationNumber);
        for (Object space : this) {
            if (((Space)space).getVehicle().getRegistrationNumber().equals(registrationNumber))
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

    boolean remove(Object object);

    int size();

    default int getSpaces(Person person) {
        Objects.requireNonNull(person, "person - null");
        int count = 0;
        for (Object space : this) {
            if (((Space)space).getPerson().equals(person)) count++;
        }
        return count;
    }

    default Collection<Vehicle> getVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        for (Object space : this) {
            if (!((Space)space).isEmpty()) {
                vehicles.add(((Space)space).getVehicle());
            }
        }
        return vehicles;
    }

    int vehiclesQuantity();

    int vehiclesQuantity(VehicleTypes type);

    default int indexOf(String registrationNumber) throws IlleagalRegistrationNumberFormat {
        PatternCheck.check(registrationNumber);
        int index = 0;
        for (Object space : this) {
            if (((Space)space).getVehicle().getRegistrationNumber().equals(registrationNumber))
                return index;
            index++;
        }
        return -1;
    }

    default List<Space> getSpaces(VehicleTypes type) {
        ArrayList<Space> spaces = new ArrayList<>();
        for (Object space : this) {
            if (((Space)space).getVehicle().getType().equals(type)) {
                spaces.add((Space)space);
            }
        }
        return spaces;
    }

    default Deque<Space> getEmptySpaces() {
        LinkedList<Space> spaces = new LinkedList<>();
        for (Object space : this) {
            if (((Space)space).isEmpty()) {
                spaces.add((Space)space);
            }
        }
        return spaces;
    }

    default int emptySpacesQuantity() {
        return size() - vehiclesQuantity();
    }
}

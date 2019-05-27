package model;

import java.time.LocalDate;
import java.util.*;

public interface Floor extends Comparable<Floor>, Iterable<Space>, Collection<Space> {

    default boolean isEmpty() {
        return size() == 0;
    }

    default boolean addAll(Collection<? extends Space> collection) {
        Object[] spaces = collection.toArray();
        for (int i = 0; i < collection.size(); i++) {
            add((Space) spaces[i]);
        }
        return true;
    }

    default boolean removeAll(Collection<?> collection) {
        boolean flags = false;
        Object[] spaces = collection.toArray();
        for (int i = 0; i < collection.size(); i++) {
            if (contains(spaces[i])) {
                remove(spaces[i]);
                flags = true;
            }
        }
        return flags;
    }

    default boolean retainAll(Collection<?> collection) {
        boolean flags = false;
        Object[] spaces = collection.toArray();
        for (int i = 0; i < collection.size(); i++) {
            if (!contains(spaces[i])) {
                remove(spaces[i]);
                flags = true;
            }
        }
        return flags;
    }

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
        LocalDate minDate = null;
        for (Object space : this.toArray()) {
            if (space instanceof RentedSpace) {
                if (minDate == null) minDate = ((Space)space).getSinceDate();
                else if (minDate.isBefore(((Space)space).getSinceDate()))
                    minDate = ((Space)space).getSinceDate();
            }
        }
        if (minDate == null) throw new NoSuchElementException();
        return minDate;
    }

    default Space spaceWithNearestRentEndsDate() {
        LocalDate minDate = nearestRentEndsDate();

        for (Object space : this.toArray()) {
            if (space instanceof RentedSpace)
                if (((Space)space).getSinceDate().isEqual(minDate))
                    return ((Space)space);
        }

        throw new NoSuchElementException();
    }

    boolean add(Space space);

    boolean add(int index, Space space);

    Space get(int index);

    default Space get(String registrationNumber) throws IlleagalRegistrationNumberFormat{
        PatternCheck.check(registrationNumber);
        for (Object space : this.toArray()) {
            if (((Space)space).getVehicle().getRegistrationNumber().equals(registrationNumber))
                return ((Space)space);
        }
        throw new NoSuchElementException();
    }

    int getIndex(Space space) throws IlleagalRegistrationNumberFormat;

    default boolean hasSpace(String registrationNumber) throws IlleagalRegistrationNumberFormat {
        PatternCheck.check(registrationNumber);
        for (Object space : this.toArray()) {
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

    Object[] toArray();

    default int getSpaces(Person person) {
        Objects.requireNonNull(person, "person - null");
        int count = 0;
        for (Object space : this.toArray()) {
            if (((Space)space).getPerson().equals(person)) count++;
        }
        return count;
    }

    default Collection<Vehicle> getVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        for (Object space : this.toArray()) {
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
        for (Object space : this.toArray()) {
            if (((Space)space).getVehicle().getRegistrationNumber().equals(registrationNumber))
                return index;
            index++;
        }
        return -1;
    }

    default List<Space> getSpaces(VehicleTypes type) {
        ArrayList<Space> spaces = new ArrayList<>();
        for (Object space : this.toArray()) {
            if (((Space)space).getVehicle().getType().equals(type)) {
                spaces.add((Space)space);
            }
        }
        return spaces;
    }

    default Deque<Space> getEmptySpaces() {
        LinkedList<Space> spaces = new LinkedList<>();
        for (Object space : this.toArray()) {
            if (((Space)space).isEmpty()) {
                spaces.add((Space)space);
            }
        }
        return spaces;
    }
}

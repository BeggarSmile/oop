package model;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class OwnersFloor implements Floor, Cloneable {

    private Integer size;
    private Space[] spaces;
    private static final int CAPACITY_DEFAULT = 16;

    public OwnersFloor () {
        this(CAPACITY_DEFAULT);
    }

    public OwnersFloor (int numberSpace) {
        this.spaces = new Space[numberSpace];
    }

    public OwnersFloor (Space[] spaces) {
        size = spaces.length;
        Space[] newSpaces = new Space[size * 2];
        System.arraycopy(spaces,0,newSpaces ,0, size);
        this.spaces = newSpaces;
    }

    public String toString() {
        StringBuilder strBuild = new StringBuilder("Spaces: ");

        for (int i = 0; i < size; i++) {
            strBuild.append("\n").append(spaces[i].toString());
        }

        return strBuild.toString();
    }

    public int hashCode() {
        int hash = 53 * size;
        for (int i = 0; i < size; i++) {
            hash ^= spaces[i].hashCode();
        }
        return hash;
    }

    private boolean compareToArrays(Space[] spaces) {
        for (int i = 0; i < size; i++) {
            if (!this.spaces[i].equals(spaces[i]))
                return false;
        }
        return true;
    }

    public boolean equals(Object object) {
        if (!(object instanceof OwnersFloor && ((OwnersFloor) object).size == size)) {
            return false;
        }
            for (int i = 0; i < size; i++) {
                if (!((OwnersFloor) object).spaces[i].equals(spaces[i]))
                    return false;
            }

        return true;
    }

    public Object clone() throws CloneNotSupportedException {
        OwnersFloor clone = (OwnersFloor)super.clone();
        for (int i = 0; i < size; i++) {
            clone.spaces[i] = spaces.clone()[i];
        }
        return clone;
    }

    public int compareTo(Floor floor) {
        return this.size.compareTo(floor.size());
    }

    public Space[] increase (Space[] spaces) {
            Space[] newSpaces = new RentedSpace[size * 2];
            System.arraycopy(spaces,0,newSpaces ,0, size);
            spaces = newSpaces;
            return spaces;
    }

    public int indexOf (String registrationNumber) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(registrationNumber, "registrationNumber - null");

        // Исключение illegalRegNumber
        PatternCheck.check(registrationNumber);

        for (int i = 0; i < size; i++) {
            if (spaces[i].getVehicle().getRegistrationNumber().equals(registrationNumber))
                return i;
        }
        return -1;
    }

    public int vehiclesQuantity() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (!spaces[i].isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public int vehiclesQuantity(VehicleTypes type) {
        // Исключение isNull
        Objects.requireNonNull(type, "type - null");

        int count = 0;
        for (int i = 0; i < size; i++) {
            if (!spaces[i].isEmpty() && spaces[i].getVehicle().getType() == type) {
                count++;
            }
        }
        return count;
    }

    public boolean hasRentedSpace() {
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i] instanceof RentedSpace) return true;
        }

        return false;
    }

    public int searchFirstRentedSpace() {
        if (!hasRentedSpace()) throw new NoRentedSpaceException();

        for (int i = 0; i < size; i++) {
            if (spaces[i] instanceof RentedSpace) return i;
        }

        return -1;
    }

    public LocalDate nearestRentEndsDate() {
        int index = searchFirstRentedSpace();
        LocalDate minDate = spaces[index].getSinceDate();

        for (int i = index; i < size; i++) {
            if (spaces[i] instanceof RentedSpace)
                if (spaces[i].getSinceDate().isBefore(minDate))
                    minDate = spaces[i].getSinceDate();
        }

        return minDate;
    }

    public Space spaceWithNearestRentEndsDate() {

        LocalDate minDate = nearestRentEndsDate();
        int index = searchFirstRentedSpace();

        for (int i = index; i < size; i++) {
            if (spaces[i] instanceof RentedSpace)
                if (spaces[i].getSinceDate() == minDate)
                    return spaces[i];
        }

        return spaces[index];
    }

    public boolean add (Space space) {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");

        if (spaces.length == size) {
            spaces = increase(spaces);
            }
        spaces[size] = space;
        size++;
        return true;
    }

    public boolean add (int index, Space space) {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");

        // Исключение illegalIndex
        Objects.checkIndex(index, size);

        Objects.checkIndex(index, size);

        if (spaces.length == size) {
            spaces = increase(spaces);
        }
        System.arraycopy(spaces, index, spaces, index + 1, size - index - 1);
        spaces[index] = space;

        return true;
    }

    public Space get (int index) {
        // Исключение illegalIndex
        Objects.checkIndex(index, size);

        return spaces[index];
    }

    public Space get (String registrationNumber) throws IlleagalRegistrationNumberFormat {
        int index = indexOf(registrationNumber);
        if (index == -1 ) throw new NoSuchElementException();
        return spaces[index];
    }

    public int getIndex (Space space) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");
        for (int i = 0; i < size; i++) {
            if (spaces[i].equals(space)) return i;
        }

        throw new NoSuchElementException();
    }

    public boolean hasSpace (String registrationNumber) throws IlleagalRegistrationNumberFormat {
        return indexOf(registrationNumber) != -1;
    }

    public Space set (int index, Space space) {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");

        // Исключение illegalIndex
        Objects.checkIndex(index, size);

        Space removedSpace = spaces[index];
        spaces[index] = space;
        return removedSpace;
    }

    public Space remove (int index) {
        // Исключение illegalIndex
        Objects.checkIndex(index, size);

        Space space = spaces[index];
        System.arraycopy(spaces, index + 1, spaces, index, size - index - 1);
        size--;
        spaces[size] = null;
        return space;
    }

    public Space remove (String registrationNumber) throws IlleagalRegistrationNumberFormat {
        int index = indexOf(registrationNumber);
        if (index != -1) return remove(index);

        // Исключение noSuchElement
        else throw new NoSuchElementException();
    }

    public boolean remove (Space space) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");
         for (int i = 0; i < size; i++) {
                if (space.equals(spaces[i])) {
                    remove(i);
                    return true;
                }
            }
        return false;
    }

    public int size() {
        return size;
    }

    public Space[] getSpaces () {
        Space[] newSpace = new Space[size];
        System.arraycopy(spaces, 0, newSpace, 0, size);
        return newSpace;
    }

    public Vehicle[] getVehicles() {
        Vehicle[] newVehicles = new Vehicle[vehiclesQuantity()];
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (spaces[i].isEmpty()) {
                newVehicles[count] = spaces[i].getVehicle();
                count++;
            }
        }

        return newVehicles;
    }

    public Space[] getSpaces(VehicleTypes type) {
        // Исключение isNull
        Objects.requireNonNull(type, "type - null");

        Space[] newSpaces = new Space[vehiclesQuantity(type)];
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (!spaces[i].isEmpty() && spaces[i].getVehicle().getType() == type) {
                newSpaces[count] = spaces[i];
                count++;
            }
        }
        return newSpaces;
    }

    public int getSpaces(Person person) {
        // Исключение isNull
        Objects.requireNonNull(person, "person - null");

        int count = 0;

        for (int i = 0; i < size; i++) {
            if (spaces[i].getPerson() == person) count++;
        }

        return count;
    }

    public Space[] getEmptySpaces() {
        Space[] emptySpaces = new Space[size - vehiclesQuantity()];
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (!spaces[i].isEmpty()) {
                emptySpaces[count] = spaces[i];
                count++;
            }
        }
        return emptySpaces;
    }
}
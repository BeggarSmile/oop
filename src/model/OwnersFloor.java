package model;


import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Objects;

public class OwnersFloor implements Floor, Cloneable {
    private int size;
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

    //todo чет не работает, не забудь спросить
    public String toString() {
        StringBuilder strBuild = new StringBuilder("Spaces: ");

        for (int i = 0; i < size; i++) {
            strBuild.append(String.format("%n", spaces[i].getPerson().toString() + " TC: " + spaces[i].getVehicle().toString()));
        }

        return strBuild.toString();
    }

    public int hashCode() {
        int hash = spaces[0].hashCode();

        for (int i = 1; i < size; i++) {
            hash ^= spaces[i].hashCode();
        }

        return (53 * size * hash);
    }

    public boolean equals(Object object) {
        return (object instanceof OwnersFloor && ((OwnersFloor) object).getSpaces() == spaces);
    }

    public Object clone() throws CloneNotSupportedException{
        OwnersFloor clone = (OwnersFloor)super.clone();
        clone.spaces = (Space[])spaces.clone();

        return clone;
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
        PatternCheck patternCheck = new PatternCheck(registrationNumber);
        if (!patternCheck.check()) throw new IlleagalRegistrationNumberFormat();

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

    public boolean searchRentedSpace() {
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i] instanceof RentedSpace) return true;
        }

        return false;
    }

    public LocalDate nearestRentEndsDate() {
        // Исключение notFound
        if (!searchRentedSpace()) throw new NoRentedSpaceException();

        LocalDate minDate = spaces[0].getSinceDate();

        for (int i = 0; i < size; i++) {
            if (spaces[i].getSinceDate().isBefore(minDate)) minDate = spaces[i].getSinceDate();
        }

        return minDate;
    }

    public Space spaceWithNearestRentEndsDate() {
        // Исключение notFound
        if (!searchRentedSpace()) throw new NoRentedSpaceException();

        LocalDate minDate = nearestRentEndsDate();

        for (int i = 0; i < size; i++) {
            if (spaces[i].getSinceDate() == minDate) return spaces[i];
        }

        return null;
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
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        if (spaces.length == size) {
            spaces = increase(spaces);
        }
        System.arraycopy(spaces, index, spaces, index + 1, size - index - 1);
        spaces[index] = space;

        return true;
    }

    public Space get (int index) {
        // Исключение illegalIndex
        if (index > 0 || index <= size) throw new IndexOutOfBoundsException();

        return spaces[index];
    }

    public Space get (String registrationNumber) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(registrationNumber, "registrationNumber - null");

        // Исключение illegalRegNumber
        PatternCheck patternCheck = new PatternCheck(registrationNumber);
        if (!patternCheck.check()) throw new IlleagalRegistrationNumberFormat();

        int index = indexOf(registrationNumber);
        if (index != -1) return spaces[index];

        // Исключение noSuchElement
        else throw new NoSuchElementException();
    }

    public int getIndex (Space space) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");

        return indexOf(space.getVehicle().getRegistrationNumber());
    }

    public boolean hasSpace (String registrationNumber) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(registrationNumber, "registrationNumber - null");

        // Исключение illegalRegNumber
        PatternCheck patternCheck = new PatternCheck(registrationNumber);
        if (!patternCheck.check()) throw new IlleagalRegistrationNumberFormat();

        return indexOf(registrationNumber) != -1;
    }

    public Space set (int index, Space space) {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");

        // Исключение illegalIndex
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Space removedSpace = spaces[index];
        spaces[index] = space;
        return removedSpace;
    }

    public Space remove (int index) {
        // Исключение illegalIndex
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Space space = spaces[index];
        System.arraycopy(spaces, index + 1, spaces, index, size - index - 1);
        size--;
        spaces[size] = null;
        return space;
    }

    public Space remove (String registrationNumber) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(registrationNumber, "registrationNumber - null");

        // Исключение illegalRegNumber
        PatternCheck patternCheck = new PatternCheck(registrationNumber);
        if (!patternCheck.check()) throw new IlleagalRegistrationNumberFormat();

        int index = indexOf(registrationNumber);
        if (index != -1) return remove(index);

        // Исключение noSuchElement
        else throw new NoSuchElementException();
    }

    public boolean remove (Space space) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");

        if (hasSpace(space.getVehicle().getRegistrationNumber())) {
            remove(space.getVehicle().getRegistrationNumber());
            return true;
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
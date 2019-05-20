package model;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
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

    private class SpaceIterator implements java.util.Iterator<Space>{
        Space[] spaceIter;
        int indexPosition = 0;

        public SpaceIterator(Space[] spaces) {
            this.spaceIter = spaces;
        }

        public boolean hasNext() {
            return size >= indexPosition + 1;
        }

        public Space next() {
            if (!this.hasNext()) throw new NoSuchElementException();
            Space nextSpace = spaceIter[indexPosition];
            this.indexPosition++;
            return nextSpace;
        }
    }

    public Iterator<Space> iterator() {
        return new SpaceIterator(spaces);
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

    public int getIndex (Space space) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");
        for (int i = 0; i < size; i++) {
            if (spaces[i].equals(space)) return i;
        }

        throw new NoSuchElementException();
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

}
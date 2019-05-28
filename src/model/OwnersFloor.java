package model;


import java.time.LocalDate;
import java.util.*;

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

    //todo об этом тоже не забудь спросить
    public boolean contains(Object object) {
        for (int i = 0; i < size; i++) {
            if (spaces[i] == object) return true; //todo equals
        }
        return false;
    }

    //todo Обязательно спроси, и на листах такая же хрень
    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean containsAll(Collection<?> collection) {
        boolean flags = true;
        //todo foreach + contains
        for (int i = 0; i < size; i++) {
            if (!flags) return false;
            flags = false;
            for (int j = 0; j < collection.size(); j++) {
                if (this.spaces[i] == spaces[j]) flags = true;
            }
        }
        return true;
    }

    private class SpaceIterator implements java.util.Iterator<Space>{
        int indexPosition = 0;

        public boolean hasNext() {
            return size < indexPosition;
        }

        public Space next() {
            if (!this.hasNext()) throw new NoSuchElementException();
            Space nextSpace = spaces[indexPosition];
            this.indexPosition++;
            return nextSpace;
        }
    }

    public Iterator<Space> iterator() {
        return new SpaceIterator();
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
        return size - floor.size();
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

    public int getIndex (Space space) {
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

    public boolean remove (Object object) {
        // Исключение isNull
        Objects.requireNonNull(object, "space - null");
         for (int i = 0; i < size; i++) {
                if (object.equals(spaces[i])) {
                    remove(i);
                    return true;
                }
            }
        return false;
    }

    public int size() {
        return size;
    }

    public Space[] toArray () {
        Space[] newSpace = new Space[size];
        System.arraycopy(spaces, 0, newSpace, 0, size);
        return newSpace;
    }

}
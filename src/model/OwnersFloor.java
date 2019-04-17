package model;


public class OwnersFloor implements Floor {
    private int size;
    private Space[] spaces;
    private static final int CAPACITY_DEFAULT = 16;

    public Space[] increase (Space[] spaces) {
            Space[] newSpaces = new RentedSpace[size * 2];
            System.arraycopy(spaces,0,newSpaces ,0, size);
            spaces = newSpaces;
            return spaces;
    }

    public int indexOf (String registrationNumber) {
        for (int i = 0; i < size; i++) {
            if (spaces[i].getVehicle().getRegistrationNumber().equals(registrationNumber))
                return i;
        }
        return -1;
    }

    public int vehiclesQuantity() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (spaces[i].isEmpty()) {
                count++;
            }
        }
        return count;
    }

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

    public boolean add (Space space) {
        if (spaces.length == size) {
            spaces = increase(spaces);
            }
        spaces[size] = space;
        size++;
        return true;
    }

    public boolean add (int index, Space space) {
            if (spaces.length == size) {
                spaces = increase(spaces);
            }
            System.arraycopy(spaces, index, spaces, index + 1, size - index - 1);
            spaces[index] = space;

        return true;
    }

    public Space get (int index) {
        return spaces[index];
    }

    public Space get (String registrationNumber) {
        int index = indexOf(registrationNumber);
        if (index != -1) return spaces[index];
        return null;
    }

    public boolean hasSpace (String registrationNumber) {
        return indexOf(registrationNumber) != -1;
    }

    public Space set (int index, Space space) {
        Space removedSpace = spaces[index];
        spaces[index] = space;
        return removedSpace;
    }

    public Space remove (int index) {
        Space space = spaces[index];
        System.arraycopy(spaces, index + 1, spaces, index, size - index - 1);
        size--;
        spaces[size] = null;
        return space;
    }

    public Space remove (String registrationNumber) {
        int index = indexOf(registrationNumber);
        return remove(index);
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
        int count = 0;
        Vehicle[] newVehicles = new Vehicle[spaces.length];
        for (int i = 0; i < size; i++) {
            if (spaces[i].isEmpty()) {
                newVehicles[count] = spaces[i].getVehicle();
                count++;
            }
        }
        Vehicle[] vehicles = new Vehicle[count];
        System.arraycopy(newVehicles, 0, vehicles, 0, count);
        return vehicles;
    }
}
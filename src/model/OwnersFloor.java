package model;


public class OwnersFloor implements Floor {
    private int size;
    private RentedSpace[] spaces;
    private static final int CAPACITY_DEFAULT = 16; //todo плохое имя - done

    public RentedSpace[] increase (RentedSpace[] spaces) {
            RentedSpace[] newSpaces = new RentedSpace[size * 2];
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
            if (spaces[i].isEmpty()) { //todo эта проверка будет в методе RentedSpace.isEmpty() - done
                count++;
            }
        }
        return count;
    }
    public OwnersFloor () {
        this(CAPACITY_DEFAULT);
    }

    public OwnersFloor (int numberSpace) {
        this.spaces = new RentedSpace[numberSpace];
    }

    public OwnersFloor (RentedSpace[] spaces) {
        size = spaces.length;
        RentedSpace[] newSpaces = new RentedSpace[size * 2];
        System.arraycopy(spaces,0,newSpaces ,0, size);
        this.spaces = newSpaces;
    }

    public boolean add (RentedSpace space) {
        if (spaces.length == size) {
            spaces = increase(spaces);
            }
        spaces[size] = space;
        size++;
        return true;
    }

    public boolean add (int index, RentedSpace space) {
            if (spaces.length == size) {
                spaces = increase(spaces);
            }
            //todo System.arraycopy - done
            System.arraycopy(spaces, index, spaces, index + 1, size - index - 1);
            spaces[index] = space;

        return true;
    }

    public RentedSpace get (int index) {
        return spaces[index];
    }

    public RentedSpace get (String registrationNumber) {
        int index = indexOf(registrationNumber);
        if (index != -1) return spaces[index];
        return null;
    }

    public boolean hasSpace (String registrationNumber) {
        return indexOf(registrationNumber) != -1;
    }

    public RentedSpace set (int index, RentedSpace space) {
        RentedSpace removedSpace = spaces[index];
        spaces[index] = space;
        return removedSpace;
    }

    public RentedSpace remove (int index) {
        RentedSpace space = spaces[index];
        System.arraycopy(spaces, index + 1, spaces, index, size - index - 1);
        size--;
        spaces[size] = null;
        return space;
    }

    public RentedSpace remove (String registrationNumber) {
        int index = indexOf(registrationNumber);
        //todo а дальше идет дубль remove(index) - вызываей его - done
        return remove(index);
    }

    public int size() {
        return size;
    }

    public RentedSpace[] getSpaces () {
        RentedSpace[] newSpace = new RentedSpace[size];
        System.arraycopy(spaces, 0, newSpace, 0, size);
        return newSpace;
    }

    public Vehicle[] getVehicles() {
        int count = 0;
        Vehicle[] newVehicles = new Vehicle[spaces.length];
        for (int i = 0; i < size; i++) {
            if (spaces[i].isEmpty()) { //todo эта проверка будет в методе RentedSpace.isEmpty() - done
                newVehicles[count] = spaces[i].getVehicle();
                count++;
            }
        }
        Vehicle[] vehicles = new Vehicle[count];
        System.arraycopy(newVehicles, 0, vehicles, 0, count);
        return vehicles;
    }
}
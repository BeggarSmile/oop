package model;

public class Parking {
    private Floor[] floors;
    private int size;

    public Floor[] increase (Floor[] floors) {
        Floor[] newFloor = new Floor[size * 2];
        System.arraycopy(floors,0,newFloor ,0, size);
        floors = newFloor;
        return floors;
    }

    public Parking (int numberFloors) {
        floors = new Floor[numberFloors];
    }

    public Parking (Floor[] floors) {
        size = floors.length;
        Floor[] newFloors = floors;
        System.arraycopy(floors, 0 , newFloors, 0, size);
        this.floors = newFloors;
    }

    public boolean add (Floor floor) {
        if (floors.length == size) {
            floors = increase(floors);
        }
        floors[size] = floor;
        size++;
        return true;
    }

    public boolean add (int index, Floor floor) {
            if (floors.length == size) {
                floors = increase(floors);
            }
            System.arraycopy(floors, index, floors, index + 1, size - index - 1);
            floors[index] = floor;
            size++;
            return true;
    }

    public Floor get (int index) {
        return floors[index];
    }

    public Floor set (int index, Floor floor) {
        Floor oldFloor = floors[index];
        floors[index] = floor;
        return oldFloor;
    }

    public Floor remove (int index) {
        Floor floor = floors[index];
        System.arraycopy(floors, index + 1, floors, index, size - index - 1 );
        floors[size - 1] = null;
        size--;
        return floor;
    }

    public int size () {
        return size;
    }

    public Floor[] getFloors () {
        Floor[] newFloors = new Floor[size];
        System.arraycopy(floors, 0 , newFloors, 0 , size);
        return newFloors;
    }

    public Floor[] sortedBySizeFloors () {

        Floor[] newFloors = getFloors();
        for (int j = 0; j < size - 1; j++) {
            for (int i = 0; i < size - 1; i++) {
                if (newFloors[i].size() > newFloors[i + 1].size()) {
                    Floor newFloor = newFloors[i];
                    newFloors[i] = newFloors[i + 1];
                    newFloors[i + 1] = newFloor;
                }
            }
        }
        return newFloors;
    }

    public Vehicle[] getVehicles () {
        int vehiclesQuantity = 0;
        for (int i = 0; i < size; i++) {
            vehiclesQuantity += floors[i].vehiclesQuantity();
        }

        Vehicle[] allVehicles = new Vehicle[vehiclesQuantity];
        vehiclesQuantity = 0;

        Vehicle[] vehicles;
        for (int i = 0; i < size; i++) {
            vehicles = floors[i].getVehicles();
            System.arraycopy(vehicles, 0, allVehicles, vehiclesQuantity, vehicles.length);
            vehiclesQuantity += vehicles.length;
        }

        return allVehicles;
    }

    public Space getSpace (String registationNumber) {
        for (int i = 0; i < size; i++) {
            if (floors[i].hasSpace(registationNumber))
                return floors[i].get(registationNumber);
        }
        return null;
    }

    public Space removeSpace (String registrationNumber) {
        Space removedSpace = null;
        for (int i = 0; i < size; i++) {
            if (floors[i].hasSpace(registrationNumber)) {
                removedSpace = floors[i].remove(registrationNumber);
                break;
            }
        }
        return removedSpace;
    }

    public Space setSpace (Space space, String registrationNumber) {
        Space oldSpace = null;
        for (int i = 0; i < size; i++) {
            if (floors[i].indexOf(registrationNumber) != -1) {
                oldSpace = floors[i].set(i, space);
                return oldSpace;
            }
        }
        return oldSpace;
    }
}

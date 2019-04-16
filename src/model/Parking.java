package model;

public class Parking {
    private OwnersFloor[] floors;
    private int size;

    public OwnersFloor[] increase (OwnersFloor[] floors) {
        OwnersFloor[] newFloor = new OwnersFloor[size * 2];
        System.arraycopy(floors,0,newFloor ,0, size);
        floors = newFloor;
        return floors;
    }

    public Parking (int numberFloors) {
        floors = new OwnersFloor[numberFloors];
    }

    public Parking (OwnersFloor[] floors) {
        size = floors.length;
        OwnersFloor[] newFloors = floors;
        System.arraycopy(floors, 0 , newFloors, 0, size);
        this.floors = newFloors;
    }

    public boolean add (OwnersFloor floor) {
        if (floors.length == size) {
            floors = increase(floors);
        }
        floors[size] = floor;
        size++;
        return true;
    }

    public boolean add (int index, OwnersFloor floor) {
            if (floors.length == size) {
                floors = increase(floors);
            }
            System.arraycopy(floors, index, floors, index + 1, size - index - 1);
            floors[index] = floor;
            size++;
            return true;
    }

    public OwnersFloor get (int index) {
        return floors[index];
    }

    public OwnersFloor set (int index, OwnersFloor floor) {
        OwnersFloor ans = floors[index];
        floors[index] = floor;
        return ans;
    }

    public OwnersFloor remove (int index) {
        OwnersFloor floor = floors[index];
        System.arraycopy(floors, index + 1, floors, index, size - index - 1 );
        floors[size - 1] = null;
        size--;
        return floor;
    }

    public int size () {
        return size;
    }

    public OwnersFloor[] getFloors () {
        OwnersFloor[] newFloors = new OwnersFloor[size];
        System.arraycopy(floors, 0 , newFloors, 0 , size);
        return newFloors;
    }

    public OwnersFloor[] sortedBySizeFloors () {

        OwnersFloor[] newFloors = getFloors();
        for (int j = 0; j < size - 1; j++) {
            for (int i = 0; i < size - 1; i++) {
                if (newFloors[i].size() > newFloors[i + 1].size()) {
                    OwnersFloor newFloor = newFloors[i];
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

    public RentedSpace getSpace (String registationNumber) {
        for (int i = 0; i < size; i++) {
            if (floors[i].hasSpace(registationNumber))
                return floors[i].get(registationNumber);
        }
        return null;
    }

    public RentedSpace removeSpace (String registrationNumber) {
        RentedSpace removedSpace = null;
        for (int i = 0; i < size; i++) {
            removedSpace = floors[i].remove(registrationNumber);
            if (removedSpace != null) return removedSpace;
        }
        return removedSpace;
    }

    public RentedSpace setSpace (RentedSpace space, String registrationNumber) {
        RentedSpace oldSpace = null;
        for (int i = 0; i < size; i++) {
            if (floors[i].indexOf(registrationNumber) != -1) {
                oldSpace = floors[i].set(i, space);
                return oldSpace;
            }
        }
        return oldSpace;
    }
}

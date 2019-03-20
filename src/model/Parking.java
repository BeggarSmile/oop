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
        this.floors = floors;
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
        if (floors[index].equals(null)) {
            floors[index] = floor;
            size++;
        }
        else {
            if (floors.length == size) {
                floors = increase(floors);
            }
            for (int i =  - 1; i >= index; i--) {
                floors[i] = floors[i - 1];
            }
            floors[index] = floor;
        }
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
        OwnersFloor[] newFloors = floors;
        return newFloors;
    }

    public OwnersFloor[] sortedBySizeFloors () {
        OwnersFloor[] newFloors = floors;
        for (int j = 0; j < size - 1; j++) {
            for (int i = 0; i < size - 1; i++) {
                if (newFloors[i].Size() > newFloors[i + 1].Size()) {
                    OwnersFloor newFloor = newFloors[i];
                    newFloors[i] = newFloors[i + 1];
                    newFloors[i + 1] = newFloor;
                }
            }
        }
        return newFloors;
    }

    public Vehicle[] getVehicles () {
        int numberVehicle = 0;
        for (int i = 0; i < size; i++) {
            numberVehicle += floors[i].vehiclesQuantity(floors[i].getSpaces()).length;
        }

        Vehicle[] newVehicles = new Vehicle[numberVehicle];
        numberVehicle = 0;

        for (int i = 0; i < size; i++) {
            System.arraycopy(floors[i].getVehicles(), 0, newVehicles, numberVehicle, floors[i].getVehicles().length);
            numberVehicle += floors[i].getVehicles().length;
        }

        return newVehicles;
    }

    public Space getSpace (String registationNumber) {
        for (int i = 0; i < size; i++) {
            if (floors[i].hasSpace(registationNumber)) return floors[i].getSpaces()[floors[i].indexOf(registationNumber)];
        }
        return null;
    }

    public Space removeSpace (String registrationNumber) {
        Space ans = new Space();
        for (int i = 0; i < size; i++) {
            Space removedSpace = floors[i].remove(registrationNumber);
            if (removedSpace != null) return removedSpace;
        }
        return null;
    }

    public Space setSpace (Space space, String registrationNumber) {
        Space ans = new Space();
        for (int i = 0; i < size; i++) {
            if (floors[i].indexOf(registrationNumber) != -1) {
                ans = floors[i].getSpaces()[floors[i].indexOf(registrationNumber)];
                return ans;
            }
        }
        return null;
    }
}

package model;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Parking {
    private Floor[] floors;
    private int size;

    public Parking (int numberFloors) {
        floors = new Floor[numberFloors];
    }

    public Parking (Floor[] floors) {
        size = floors.length;
        this.floors = new Floor[size];
        System.arraycopy(floors, 0 , this.floors, 0, size);
    }

    //todo
    public String toString() {
        StringBuilder strBuild = new StringBuilder("Floors (").append(size).append(" ): ");

        for (int i = 0; i < size; i++) {
            strBuild.append('\n').append(floors[i].toString());
        }

        return strBuild.toString();
    }

    public Floor[] increase (Floor[] floors) {
        Floor[] newFloor = new Floor[size * 2];
        System.arraycopy(floors,0,newFloor ,0, size);
        floors = newFloor;
        return floors;
    }

    public boolean add (Floor floor) {
        // Исключение isNull
        Objects.requireNonNull(floor, "floor - null");

        if (floors.length == size) {
            floors = increase(floors);
        }
        floors[size] = floor;
        size++;
        return true;
    }

    public boolean add (int index, Floor floor) {
        // Исключение isNull
        Objects.requireNonNull(floor, "floor - null");

        // Исключение illegalIndex
        Objects.checkIndex(index, size);

        if (floors.length == size) {
            floors = increase(floors);
        }
        System.arraycopy(floors, index, floors, index + 1, size - index - 1);
        floors[index] = floor;
        size++;
        return true;
    }

    public Floor get (int index) {
        // Исключение illegalIndex
        Objects.checkIndex(index, size);

        return floors[index];
    }

    public Floor set (int index, Floor floor) {
        // Исключение isNull
        Objects.requireNonNull(floor, "floor - null");

        // Исключение illegalIndex
        Objects.checkIndex(index, size);

        Floor oldFloor = floors[index];
        floors[index] = floor;
        return oldFloor;
    }

    public Floor remove (int index) {
        // Исключение illegalIndex
        Objects.checkIndex(index, size);

        Floor floor = floors[index];
        System.arraycopy(floors, index + 1, floors, index, size - index - 1 );
        floors[size - 1] = null;
        size--;
        return floor;
    }

    public int size () {
        return size;
    }

    public Floor[] getFloors() {
        Floor[] newFloors = new Floor[size];
        System.arraycopy(floors, 0 , newFloors, 0 , size);
        return newFloors;
    }

    public Floor[] getFloors(Person person) {
        // Исключение isNull
        Objects.requireNonNull(person, "person - null");

        int count = 0;

        for (int i = 0; i < size; i++) {
            if (floors[i].getSpaces(person) != 0) count++;
        }

        Floor[] newFloors = new Floor[count];
        count = 0;

        for (int i = 0; i < size; i++) {
            if (floors[i].getSpaces(person) != 0){
                System.arraycopy(floors, i, newFloors, count, 1);
                count++;
            }
        }

        return newFloors;
    }

    public Floor[] sortedBySizeFloors() {

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


    public Space getSpace (String registrationNumber) throws IlleagalRegistrationNumberFormat {
        for (int i = 0; i < size; i++) {
            if (floors[i].hasSpace(registrationNumber))
                return floors[i].get(registrationNumber);
        }

        // Исключение noSuchElement
        throw new NoSuchElementException();
    }

    public Space removeSpace (String registrationNumber) throws IlleagalRegistrationNumberFormat {
        Space removedSpace = null;
        for (int i = 0; i < size; i++) {
            if (floors[i].hasSpace(registrationNumber)) {
                return floors[i].remove(registrationNumber);
            }
        }

        // Исключение noSuchElement
        throw new NoSuchElementException();
    }

    public Space setSpace (Space space, String registrationNumber) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");

        Space oldSpace = null;
        for (int i = 0; i < size; i++) {
            if (floors[i].indexOf(registrationNumber) != -1) {
                return floors[i].set(i, space);
            }
        }

        // Исключение noSuchElement
        throw new NoSuchElementException();
    }

    public int emptySpacesQuantity() {
        int quantity = 0;

        for (int i = 0; i < size; i++) {
            quantity += floors[i].size();
        }

        for (int i = 0; i < size; i++) {
            quantity -= floors[i].vehiclesQuantity();
        }

        return quantity;
    }

    public int vehiclesQuantity(VehicleTypes type) {
        // Исключение isNull
        Objects.requireNonNull(type, "type - null");

        int quantity = 0;

        for (int i = 0; i < size; i++) {
            quantity += floors[i].vehiclesQuantity(type);
        }

        return quantity;
    }
}

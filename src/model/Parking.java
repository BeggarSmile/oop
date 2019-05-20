package model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Parking implements Iterable<Floor>{
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

    private class ParkingIterator implements Iterator<Floor> {
        Floor[] floorsIter;
        int indexPosition = 0;

        public ParkingIterator(Floor[] floors) {
            this.floorsIter = floors;
        }

        public boolean hasNext() {
            return size >= indexPosition + 1;
        }

        public Floor next() {
            if (!hasNext()) throw new NoSuchElementException();
            Floor nextFloor = floorsIter[indexPosition];
            indexPosition++;
            return nextFloor;
        }
    }

    public Iterator<Floor> iterator() {
        return new ParkingIterator(floors);
    }

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

        for (Floor floor : floors) {
            if (floor.getSpaces(person) != 0) count++;
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
        Arrays.sort(newFloors);
        return newFloors;
    }

    public Vehicle[] getVehicles () {
        int vehiclesQuantity = 0;
        for (Floor floor : floors) {
            vehiclesQuantity += floor.vehiclesQuantity();
        }

        Vehicle[] allVehicles = new Vehicle[vehiclesQuantity];
        vehiclesQuantity = 0;
        Vehicle[] vehicles;

        for (Floor floor : floors) {
            vehicles = floor.getVehicles();
            System.arraycopy(vehicles, 0, allVehicles, vehiclesQuantity, vehicles.length);
            vehiclesQuantity += vehicles.length;
        }

        return allVehicles;
    }


    public Space getSpace (String registrationNumber) throws IlleagalRegistrationNumberFormat {
        for (Floor floor : floors) {
            if (floor.hasSpace(registrationNumber))
                return  floor.get(registrationNumber);
        }

        // Исключение noSuchElement
        throw new NoSuchElementException();
    }

    public Space removeSpace (String registrationNumber) throws IlleagalRegistrationNumberFormat {
        for (Floor floor : floors) {
            if (floor.hasSpace(registrationNumber)) {
                return  floor.remove(registrationNumber);
            }
        }

        // Исключение noSuchElement
        throw new NoSuchElementException();
    }

    public Space setSpace (Space space, String registrationNumber) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");

        int index = 0;
        for (Floor floor : floors) {
            if (floor.indexOf(registrationNumber) != -1) {
                return floor.set(index, space);
            }
            index++;
        }

        // Исключение noSuchElement
        throw new NoSuchElementException();
    }

    public int emptySpacesQuantity() {
        int quantity = 0;

        for (Floor floor : floors) {
            quantity += floor.size();
        }

        for (Floor floor : floors) {
            quantity -= floor.vehiclesQuantity();
        }

        return quantity;
    }

    public int vehiclesQuantity(VehicleTypes type) {
        // Исключение isNull
        Objects.requireNonNull(type, "type - null");

        int quantity = 0;

        for (Floor floor : floors) {
            quantity += floor.vehiclesQuantity(type);
        }

        return quantity;
    }
}

package model;


public class OwnersFloor {
    //todo имена переменных, методов, атрибутов - с маленькой буквы
    //todo private
    private int size;
    private Space[] spaces;
    private static final int SIZE = 16;

    public Space[] increase (Space[] spaces) {
            Space[] newSpaces = new Space[size * 2];
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

    public Vehicle[] vehiclesQuantity(Space[] spaces) {
        int count = 0;
        Vehicle[] newVehicles = new Vehicle[spaces.length];
        for (int i = 0; i < spaces.length; i++) {
            if (!spaces[i].getVehicle().equals(null)) {
                newVehicles[count] = spaces[i].getVehicle();
                count++;
            }
        }
        Vehicle[] vehicles = new Vehicle[count];
        System.arraycopy(newVehicles, 0, vehicles, 0, count);
        return vehicles;
    }
    //todo this()
    public OwnersFloor () {
        spaces = new Space[SIZE];
        //todo литералы - это зло
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
        //todo расширить массив если надо - и этот код в метод
        //todo сдвигать
        if (spaces[index].equals(null)) {
            spaces[index] = space;
            size++;
        }
        else {
            if (spaces.length == size) {
                spaces = increase(spaces);
            }
            for (int i = size - 1; i >= index; i--) {
                spaces[i] = spaces[i - 1];
            }
            spaces[index] = space;
        }
        return true;
    }

    public Space get (int index) {
        return spaces[index];
    }

    public Space get (String registrationNumber) {
        //todo цикл до size
        //todo indexOf(RegistrationNumber)
        int index = indexOf(registrationNumber);
        if (index != -1) return spaces[index];
        else return null;
    }

    public boolean hasSpace (String registrationNumber) {
        //todo indexOf(RegistrationNumber)
        if (indexOf(registrationNumber) != -1) return true;
        return false;
    }

    public Space set (int index, Space space) {
        Space removedSpace = spaces[index];
        spaces[index] = space;
        return removedSpace;
    }

    public Space remove (int index) {
        Space space = spaces[index];
        //todo System.arraycopy
        System.arraycopy(spaces, index + 1, spaces, index, size - index - 1);
        spaces[size] = null;
        size--;
        return space;
    }

    public Space remove (String registrationNumber) {
        //todo indexOf(RegistrationNumber)
        int token = indexOf(registrationNumber);
        Space space = spaces[token];
        for (int i = token; i < size - 1; i++) {
            spaces[i] = spaces[i + 1];
        }
        spaces[size] = null;
        size--;
        return space;
    }

    public int Size () {
        return size;
    }

    public Space[] getSpaces () {
        //todo возвращай копию Spaces
        Space[] newSpace = new Space[size];
        if (size < spaces.length)
            System.arraycopy(spaces, 0, newSpace, 0, size);
        return newSpace;
    }

    public Vehicle[] getVehicles () {
        Space[] newSpace = new Space[size];
        if (size < spaces.length) System.arraycopy(spaces, 0, newSpace, 0, size);
        return vehiclesQuantity(newSpace);
        //todo массив из 0-count
    }
}
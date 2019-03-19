package model;

import java.util.List;

//todo имена переменных, методов, атрибутов - с маленькой буквы
public class OwnersFloor {
    private int Size;
    //todo private
    Space[] Spaces;

    public OwnersFloor () {
        //todo this()
        this.Spaces = new Space[16]; //todo литералы - это зло
    }

    public OwnersFloor (int NumberSpace) {
        Spaces = new Space[NumberSpace];
    }

    public OwnersFloor (Space[] Spaces) {
        Size = Spaces.length;
        Space[] newSpaces = new Space[Size * 2];
        System.arraycopy(Spaces,0,newSpaces ,0, Size);
        this.Spaces = newSpaces;
    }

    public boolean add (Space space) {
        if (Spaces.length == Size) {
            Space[] newSpaces = new Space[Size * 2];
            System.arraycopy(Spaces,0,newSpaces ,0, Size);
            Spaces = newSpaces;
            }
        Spaces[Size] = space;
        Size++;
        return true;
    }

    public boolean add (int index, Space space) {
        //todo расширить массив если надо - и этот код в метод
            //todo сдвигать
            Spaces[index] = space;
            Size++;
            return true;
    }

    public Space get (int index) {
        return Spaces[index];
    }



    public Space get (String RegistrationNumber) {
        //todo цикл до size
        //todo indexOf(RegistrationNumber)
        for (Space value : Spaces) {
            if (value.getVehicle().getRegistrationNumber().equals(value)) {
                return value;
            }
        }
        return null;
    }

    public boolean hasSpace (String RegistrationNumber) {
        //todo indexOf(RegistrationNumber)
        for (int i = 0; i < Size; i++) {
            if (Spaces[i] != null) {
                if (Spaces[i].getVehicle().getRegistrationNumber().equals(RegistrationNumber)) return true;
            }
        }
        return false;
    }

    public Space set (int index, Space space) {
        Space removedSpace = Spaces[index];
        Spaces[index] = space;
        return removedSpace;
    }

    public Space remove (int index) {
        Space space = Spaces[index];
        //todo System.arraycopy
        for (int i = index; i < Size - 1; i++) {
            Spaces[i] = Spaces[i + 1];
        }
        Spaces[Size - 1] = null;
        Size--;
        return space;
    }

    public Space remove (String RegistrationNumber) {
        int token = -1;
        //todo indexOf(RegistrationNumber)
        for (int i = 0; i < Size; i++) {
            if (Spaces[i].getVehicle().RegistrationNumber == RegistrationNumber) token = i; //todo equals()
        }
        Space space = Spaces[token];
        for (int i = token; i < Size - 1; i++) {
            Spaces[i] = Spaces[i + 1];
        }
        Spaces[Size - 1] = null;
        Size--;
        return space;
    }

    public int Size () {
        return Size;
    }

    public Space[] getSpaces () {
        //todo возвращай копию Spaces
        if (Size < Spaces.length) System.arraycopy(Spaces, 0, Spaces, 0, Size);
        return Spaces;
    }

    public Vehicle[] getVehicles () {
        Vehicle[] Vehicles = new Vehicle[Size];
        int count = 0;
        for (int i = 0; i < Size; i++) {
             if (!Spaces[i].getVehicle().getRegistrationNumber().isEmpty()) {
                 Vehicles[count] = Spaces[i].getVehicle();
                 count++;
             }
        }
        return Vehicles; //todo массив из 0-count
    }
}
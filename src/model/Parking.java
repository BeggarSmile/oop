package model;
//todo имена переменных, методов, атрибутов - с маленькой буквы
public class Parking {
    //todo private
    OwnersFloor[] floors;
    int Size;

    public Parking (int numberFloors) {
        floors = new OwnersFloor[numberFloors];
    }

    public Parking (OwnersFloor[] floors) {
        Size = floors.length;
        OwnersFloor[] newFloors = floors; //todo скопируй элементы как OwnersFloor
        this.floors = floors;
    }

    public boolean add (OwnersFloor floor) {
        if (floors.length == Size) {
            OwnersFloor[] newFloor = new OwnersFloor[Size * 2];
            System.arraycopy(floors,0,newFloor ,0, Size);
            floors = newFloor;
        }
        for (int i = Size; i < floors.length; i++) {
            if (floors[i] == null) floors[i] = floor;
        }
        Size++;
        return true;
    }

    public boolean add (int index, OwnersFloor floor) {
        //todo расширить массив если надо - и этот код в метод
        //todo сдвигать
        floors[index] = floor;
            Size++;
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
        //todo System.arraycopy
        for (int i = index; i < Size - 1; i++) {
            floors[i] = floors[i + 1];
        }
        floors[Size - 1] = null;
        Size--;
        return floor;
    }

    public int size () {
        return Size;
    }

    public OwnersFloor[] getFloors () {
        return floors; //todo возвращай копию
    }

    public OwnersFloor[] sortedBySizeFloors () {
        OwnersFloor[] newFloors = getFloors();
        for (int j = 0; j < Size - 1; j++) {
            for (int i = 0; i < Size - 1; i++) {
                if (newFloors[i].Size() > newFloors[i + 1].Size()) {
                    OwnersFloor newFloor = newFloors[i];
                    newFloors[i] = newFloors[i + 1];
                    newFloors[i + 1] = newFloor;
                }
            }
        }
        if (newFloors[0].Size() > newFloors[Size - 1].Size()) {
            OwnersFloor newFloor = newFloors[0];
            newFloors[0] = newFloors[Size - 1];
            newFloors[Size - 1] = newFloor;
        }
        return newFloors;
    }

    public Vehicle[] getVehicles () {
        int numberVehicle = 0;
        //todo Floor.VehiclesQuantity()
        for (int i = 0; i < Size; i++) {
            numberVehicle += floors[i].getVehicles().length;
        }

        Vehicle[] newVehicles = new Vehicle[numberVehicle];
        numberVehicle = 0;

        for (int i = 0; i < Size; i++) {
            System.arraycopy(floors[i].getVehicles(), 0, newVehicles, numberVehicle, floors[i].getVehicles().length);
            numberVehicle += floors[i].getVehicles().length;
        }

        return newVehicles;
    }

    public Space getSpace (String RegistationNumber) {
        for (int i = 0; i < Size; i++) {
            //todo Floor.hasSpace()
        }
        return null;
    }

    public Space removeSpace (String RegistrationNumber) {
        Space ans = new Space();
        for (int i = 0; i < Size; i++) {
            //todo Floor.remove()

        }
        return null;
    }

    public Space setSpace (Space space, String RegistrationNumber) {
        Space ans = new Space();
        for (int i = 0; i < Size; i++) {
           //todo Floor.indexOf => set()
        }
        return null;
    }
}

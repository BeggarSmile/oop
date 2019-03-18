package model;

public class OwnersFloor {
    private int Size = 0;
    Space[] Spaces = new Space[4];

    public OwnersFloor () {
        this.Spaces = new Space[16];
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
        for (int i = Size; i < Spaces.length; i++) {
            if (Spaces[i] == null) Spaces[i] = space;
        }
        Size++;
        return true;
    }

    public boolean add (int index, Space space) {
        if (Spaces[index].equals(null)) {
            Spaces[index] = space;
            Size++;
            return true;
        }
        else return false;
    }

    public Space get (int index) {
        return Spaces[index];
    }

    public Space get (String RegistrationNumber) {
        for (Space value : Spaces) {
            if (value.getVehicle().getRegistrationNumber().equals(value)) {
                return value;
            }
        }
        return null;
    }

    public boolean hasSpace (String RegistrationNumber) {
        for (int i = 0; i < Size; i++) {
            if (Spaces[i] != null) {
                if (Spaces[i].getVehicle().getRegistrationNumber() == RegistrationNumber) return true;
            }
        }
        return false;
    }

    public Space set (int index, Space space) {
        if (Spaces[index] == null) Size++;
        Spaces[index] = space;
        return Spaces[index];
    }

    public Space remove (int index) {
        Space space = Spaces[index];
        for (int i = index; i < Size - 1; i++) {
            Spaces[i] = Spaces[i + 1];
        }
        Spaces[Size - 1] = null;
        Size--;
        return space;
    }

    public Space remove (String RegistrationNumber) {
        int token = -1;
        for (int i = 0; i < Size; i++) {
            if (Spaces[i].getVehicle().RegistrationNumber == RegistrationNumber) token = i;
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
        if (Size < Spaces.length) System.arraycopy(Spaces, 0, Spaces, 0, Size);
        return Spaces;
    }

    public Vehicle[] getVehicles () {
        if (Size < Spaces.length) System.arraycopy(Spaces, 0, Spaces, 0, Size);
        Vehicle[] Vehicles = new Vehicle[Size];
        for (int i = 0; i < Size; i++) {
            Vehicles[i] = Spaces[i].getVehicle();
        }
        return Vehicles;
    }
}
package model;


public class OwnersFloor implements Floor, Cloneable {
    private int size;
    private Space[] spaces;
    private static final int CAPACITY_DEFAULT = 16;

    public OwnersFloor () {
        this(CAPACITY_DEFAULT);
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

    //todo чет не работает, не забудь спросить
    public String toString() {
        StringBuilder strBuild = new StringBuilder("Spaces: ");

        for (int i = 0; i < size; i++) {
            strBuild.append(String.format("%n", spaces[i].getPerson().toString() + " TC: " + spaces[i].getVehicle().toString()));
        }

        return strBuild.toString();
    }

    public int hashCode() {
        int hash = spaces[0].hashCode();

        for (int i = 1; i < size; i++) {
            hash ^= spaces[i].hashCode();
        }

        return (53 * size * hash);
    }

    public boolean equals(Object object) {
        return (object instanceof OwnersFloor && ((OwnersFloor) object).getSpaces() == spaces);
    }

    public Object clone() throws CloneNotSupportedException{
        OwnersFloor clone = (OwnersFloor)super.clone();
        clone.spaces = (Space[])spaces.clone();

        return clone;
    }

    public Space[] increase (Space[] spaces) {
            Space[] newSpaces = new RentedSpace[size * 2];
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
            if (!spaces[i].isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public int vehiclesQuantity(VehicleTypes type) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (!spaces[i].isEmpty() && spaces[i].getVehicle().getType() == type) {
                count++;
            }
        }
        return count;
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
            if (spaces.length == size) {
                spaces = increase(spaces);
            }
            System.arraycopy(spaces, index, spaces, index + 1, size - index - 1);
            spaces[index] = space;

        return true;
    }

    public Space get (int index) {
        return spaces[index];
    }

    public Space get (String registrationNumber) {
        int index = indexOf(registrationNumber);
        if (index != -1) return spaces[index];
        return null;
    }

    public int getIndex (Space space) {
        return indexOf(space.getVehicle().getRegistrationNumber());
    }

    public boolean hasSpace (String registrationNumber) {
        return indexOf(registrationNumber) != -1;
    }

    public Space set (int index, Space space) {
        Space removedSpace = spaces[index];
        spaces[index] = space;
        return removedSpace;
    }

    public Space remove (int index) {
        Space space = spaces[index];
        System.arraycopy(spaces, index + 1, spaces, index, size - index - 1);
        size--;
        spaces[size] = null;
        return space;
    }

    public Space remove (String registrationNumber) {
        int index = indexOf(registrationNumber);
        return remove(index);
    }

    public boolean remove (Space space) {
        if (hasSpace(space.getVehicle().getRegistrationNumber())) {
            remove(space.getVehicle().getRegistrationNumber());
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public Space[] getSpaces () {
        Space[] newSpace = new Space[size];
        System.arraycopy(spaces, 0, newSpace, 0, size);
        return newSpace;
    }

    public Vehicle[] getVehicles() {
        Vehicle[] newVehicles = new Vehicle[vehiclesQuantity()];
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (spaces[i].isEmpty()) {
                newVehicles[count] = spaces[i].getVehicle();
                count++;
            }
        }

        return newVehicles;
    }

    public Space[] getSpaces(VehicleTypes type) {
        Space[] newSpaces = new Space[vehiclesQuantity(type)];
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (!spaces[i].isEmpty() && spaces[i].getVehicle().getType() == type) {
                newSpaces[count] = spaces[i];
                count++;
            }
        }
        return newSpaces;
    }

    public int getSpaces(Person person) {
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (spaces[i].getPerson() == person) count++;
        }

        return count;
    }

    public Space[] getEmptySpaces() {
        Space[] emptySpaces = new Space[size - vehiclesQuantity()];
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (!spaces[i].isEmpty()) {
                emptySpaces[count] = spaces[i];
                count++;
            }
        }
        return emptySpaces;
    }
}
package model;

public class RentedSpacesFloor implements Floor{

    private Node head = new Node(null);
    private int size;

    public int vehiclesQuantity() {
        Space[] spaces = getSpaces();

        int count = 0;
        for (int i = 0; i < size; i++) {
            if (!spaces[i].isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public int vehiclesQuantity(VehicleTypes type) {
        Space[] spaces = getSpaces();

        int count = 0;
        for (int i = 0; i < size; i++) {
            if (!spaces[i].isEmpty() && spaces[i].getVehicle().getType() == type) {
                count++;
            }
        }
        return count;
    }

    public int indexOf(String registrationNumber) {
        Space[] spaces = getSpaces();

        for (int i = 0; i < size; i++) {
            if (spaces[i].getVehicle().getRegistrationNumber().equals(registrationNumber))
                return i;
        }
        return -1;
    }

    public RentedSpacesFloor(Space[] spaces) {
        for (int i = 0; i < spaces.length; i++) {
            addLast(spaces[i]);
        }
    }

    private boolean addLast(Space space) {
        Node node = new Node(space);
        if (size == 0) {
            head.setNext(node);
            head.setPrevious(node);
            size++;
        }
        else {
            node.setPrevious(head.getPrevious());
            node.setNext(head.getNext());
            head.getPrevious().setNext(node);
            head.setPrevious(node);
            size++;
        }
        return true;
    }

    private boolean addIndex(int index, Space space) {
        Node node = head;
        Node addedNode = new Node(space);

        if (index == 0) {
            node.setNext(head.getNext());
            node.setPrevious(head.getPrevious());
            head.getNext().setPrevious(node);
            head.setNext(node);
        }
        else if (index == size) {
            addLast(space);
        }
        else {
            for (int i = 0; i < size; i++) {
                node = node.getNext();
                if (i == index) {
                    addedNode.setNext(node);
                    addedNode.setPrevious(node.getPrevious());
                    node.setPrevious(addedNode);
                    node.getPrevious().setNext(addedNode);
                }
            }
        }
        return true;
    }

    private Node getNode(int index) {
        Node node = head;

        if (index > size || size == 0) {
            return null;
        }
        else {
            for (int i = 0; i < size; i++) {
                node = node.getNext();
                if (i == index) return node;
            }
        }

        return null;
    }

    private Space setNode(int index, Space space) {
        return getNode(index).setValue(space);
    }

    private Space removeNode(int index) {
        Node node = head;
        Node removedNode = null;

        if (index == 0) {
            removedNode = head.getNext();
            head.getNext().setValue(null);
            head = head.getNext();
        }
        else if (index == size - 1) {
            removedNode = head.getPrevious();
            head.getPrevious().setValue(null);
            head = head.getPrevious();
        }
        else if (index < size) {
            for (int i = 0; i < size; i++) {
                node = node.getNext();
                if (i == index) {
                    removedNode = node;
                    node.getNext().setPrevious(node.getPrevious());
                    node.getPrevious().setNext(node.getNext());
                    return removedNode.getValue();
                }
            }
        }
        return removedNode.getValue();
    }

    public boolean add(Space space) {
            return addLast(space);
    }

    public boolean add(int index, Space space) {
        return addIndex(index, space);
    }

    //todo need complete - done
    public Space get(int index) {
        return getNode(index).getValue();
    }

    //todo need complete - done
    public Space get(String registrationNumber) {
        if (hasSpace(registrationNumber)) {
            for (int i = 0; i < size; i++) {
                if (getNode(i).getValue().getVehicle().getRegistrationNumber().equals(registrationNumber))
                    return getNode(i).getValue();
            }
        }
        return null;
    }

    //todo need complete - done
    public boolean hasSpace(String registrationNumber) {
        for (int i = 0; i < size; i++) {
            if (getNode(i).getValue().getVehicle().getRegistrationNumber().equals(registrationNumber))
                return true;
        }
        return false;
    }

    //todo need complete - done
    public Space set(int index, Space space) {
        return setNode(index, space);
    }

    //todo need complete - done
    public Space remove(int index) {
        return removeNode(index);
    }

    //todo need complete - done
    public Space remove(String registrationNumber) {
        if (hasSpace(registrationNumber)) {
            for (int i = 0; i < size; i++) {
                if (getNode(i).getValue().getVehicle().getRegistrationNumber().equals(registrationNumber))
                    return removeNode(i);
            }
        }
        return null;
    }

    //todo need complete - done
    public int size() {
        return size;
    }

    //todo need complete - done
    public Space[] getSpaces() {
        Space[] spaces = new Space[size];
        Node node = head;

        for (int i = 0; i < size; i++) {
            node = node.getNext();
            spaces[i] = node.getValue();
        }

        return spaces;
    }

    //todo need complete - done
    public Vehicle[] getVehicles() {
        Space[] spaces = getSpaces();
        Vehicle[] vehicles = new Vehicle[vehiclesQuantity()];
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (spaces[i].getVehicle() != null) {
                vehicles[count] = spaces[i].getVehicle();
                count++;
            }
        }

        Vehicle[] newVehicles = new Vehicle[count];
        System.arraycopy(vehicles, 0, newVehicles, 0 ,count);
        return newVehicles;
    }

    public Space[] getSpaces(VehicleTypes type) {
        Space[] oldSpaces = getSpaces();
        Space[] newSpaces = new Space[vehiclesQuantity(type)];
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (!oldSpaces[i].isEmpty() && oldSpaces[i].getVehicle().getType() == type) {
                newSpaces[count] = oldSpaces[i];
                count++;
            }
        }
        return newSpaces;
    }

    public Space[] getEmptySpaces() {
        Space[] oldSpaces = getSpaces();
        Space[] emptySpaces = new Space[size - vehiclesQuantity()];
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (!oldSpaces[i].isEmpty()) {
                emptySpaces[count] = oldSpaces[i];
                count++;
            }
        }
        return emptySpaces;
    }

}

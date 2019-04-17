package model;

public class RentedSpacesFloor implements Floor{

    private Node head = new Node(null);
    private int size;
    private Node first;
    private Node last;

    public int vehiclesQuantity() {
        Space[] spaces = getSpaces();

        int count = 0;
        for (int i = 0; i < size; i++) {
            if (spaces[i].isEmpty()) {
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
            Node node = new Node(spaces[i]);
            if (i == 0) {
                node.setNext(node);
                node.setPrevious(node);
                first = node;
                last = node;
                head.setNext(node);
                head.setPrevious(node);
                size++;
            } else {
                last.setNext(node);
                first.setPrevious(node);
                node.setNext(first);
                node.setPrevious(last);
                last = node;
                head.setPrevious(last);
                size++;
            }
        }
    }

    private boolean addLast(Space space) {
        Node node = new Node(space);
        if (size == 0) {
            first = node;
            last = node;
            head.setNext(first);
            head.setPrevious(last);
            size++;
        }
        else {
            last.setNext(node);
            first.setPrevious(node);
            node.setNext(first);
            node.setPrevious(last);
            last = node;
            head.setPrevious(last);
            size++;
        }
        return true;
    }

    private boolean addIndex(int index, Space space) {
        Node node = head;
        Node addedNode = new Node(space);

        if (index == 0) {
            first.setPrevious(addedNode);
            last.setNext(addedNode);
            addedNode.setNext(first);
            addedNode.setPrevious(last);
            first = addedNode;
            head.setNext(first);
        }
        else if (index == size) {
            addLast(space);
        }
        else {
            for (int i = 0; i < size; i++) {
                node = node.getNext();
                if (i == index) {
                    node.setPrevious(addedNode);
                    addedNode.setNext(node.getNext());
                    node.setNext(addedNode);
                    addedNode.setPrevious(node);
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

        return node;
    }

    private Space setNode(int index, Space space) {
        return getNode(index).setValue(space);
    }

    private Space removeNode(int index) {
        Node node = head;
        Node removedNode = null;
        if (index == 0) {
            removedNode = first;
            head.setNext(node.getNext().getNext());
            node.getNext().getNext().setPrevious(last);
            last.setNext(node.getNext().getNext());
            return removedNode.getValue();
        }
        else if (index == size - 1) {
            removedNode = last;
            head.setPrevious(node.getPrevious().getPrevious());
            node.getPrevious().getPrevious().setNext(first);
            first.setPrevious(node.getPrevious().getPrevious());
            return removedNode.getValue();
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
        int count = 0;
        Space[] spaces = getSpaces();

        for (int i = 0; i < size; i++) {
            if (spaces[i].getVehicle() != null) count++;
        }

        int counter = 0;
        Vehicle[] vehicles = new Vehicle[count];

        for (int i = 0; i < size; i++) {
            if (spaces[i].getVehicle() != null) {
                vehicles[counter] = spaces[i].getVehicle();
                counter++;
            }
        }

        return vehicles;
    }
}

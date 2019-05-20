package model;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class RentedSpacesFloor implements Floor, Cloneable {

    private Node head = new Node(null);
    private Integer size = 0;

    public RentedSpacesFloor(Space[] spaces) {
        for (int i = 0; i < spaces.length; i++) {
            addLast(new Node(spaces[i]));
        }
    }

    private class SpaceIterator implements Iterator<Space>{
        Space[] spaceIter;
        int indexPosition = 0;

        public SpaceIterator(Space[] spaces) {
            this.spaceIter = spaces;
        }

        public boolean hasNext() {
            return size >= indexPosition + 1;
        }

        public Space next() {
            if (!hasNext()) throw new NoSuchElementException();
            Space spaceNext = spaceIter[indexPosition];
            indexPosition++;
            return spaceNext;
        }
    }

    public Iterator<Space> iterator() {
        return new SpaceIterator(getSpaces());
    }

    private boolean addLast(Node node) {
        if (size == 0) {
            head.next = node;
            head.previous = node;
            node.next = node;
            node.previous = node;
            size++;
        }
        else {
            node.previous = head.previous;
            node.next = head.next;
            head.previous.next = node;
            head.next.previous = node;
            head.previous = node;
            size++;
        }
        return true;
    }

    private boolean addIndex(int index, Node addedNode) {
        Node node;
        if (size == 0) {
            addLast(addedNode);
        }
        else if (index == 0) {
            addedNode.next = head.next;
            addedNode.previous = head.previous;
            head.next.previous = addedNode;
            head.previous.next = addedNode;
            head.next = addedNode;
            size++;
        }
        else if (index == size) {
            addLast(addedNode);
        }
        else {
            node = getNode(index);
            addedNode.next = node;
            addedNode.previous = node.previous;
            node.previous = addedNode;
            node.previous.next = addedNode;
            size++;
        }
        return true;
    }

    private Node getNode(int index) {
        Objects.checkIndex(index, size);
        Node node = head;
            for (int i = 0; i < size; i++) {
                node = node.next;
                if (i == index) return node;
            }
        throw new IndexOutOfBoundsException();
    }

    private Space setNode(int index, Node node) {
        Space space = node.value;
        return getNode(index).setValue(space);
    }

    private Space removeNode(int index) {
        Node node = head;
        Node removedNode = null;
        if (size == 1 && index == 0) {
            removedNode = head.next;
            head.next = null;
            head.previous = null;
            size--;
        }
        else if (index == 0) {
            removedNode = head.next;
            head.next = head.next.next;
            head.previous.next = head.next.next;
            head.next.previous = head.previous;
            size--;
        }
        else if (index == size - 1) {
            removedNode = head.previous;
            head.previous = head.previous.previous;
            head.next.previous = head.previous.previous;
            head.previous.next = head.next;
            size--;
        }
        else if (index < size) {
            node = getNode(index);
            removedNode = node;
            node.next.previous = node.previous;
            node.previous.next = node.next;
            size--;
        }
        removedNode.next = null;
        removedNode.previous = null;
        return removedNode.value;
    }

    public String toString() {
        StringBuilder strBuild = new StringBuilder("Rented spaces: ");
        Node node = head;

        for (int i = 0; i < size; i++) {
            node = node.next;
            strBuild.append('\n').append(node.value.toString());
        }

        return strBuild.toString();
    }

    public int hashCode() {
        Node node = head.next;
        int hash = head.next.value.hashCode();

        for (int i = 1; i < size; i++) {
            node = node.next;
            hash ^= node.value.hashCode();
        }

        return (53 * size * hash);
    }

    public boolean equals(Object object) {
        if (!(object instanceof RentedSpacesFloor && (((RentedSpacesFloor) object).size == size)))
        {
            return false;
        }
            Node node = head;
            Node objNode = ((RentedSpacesFloor) object).head;
            if (!node.equals(objNode)) return false;
            for (int i = 0; i < size; i++) {
                node = node.next;
                objNode = objNode.next;
                if (!node.equals(objNode)) return false;
            }
        return true;
    }

    //todo протесть - done
    public Object clone() throws CloneNotSupportedException{
        RentedSpacesFloor clone = (RentedSpacesFloor)super.clone();
        clone.head = head.clone();
        Node node = clone.head;

        for (int i = 0; i < size; i++) {
            node.next = node.next.clone();
            node = node.next;
        }
        node.next = clone.head.next;
        return clone;
    }

    public int compareTo(Floor floor) {
        return this.size.compareTo(floor.size());
    }

    public boolean hasRentedSpace() {
        Node node = head;

        for (int i = 0; i < size; i++) {
            node = node.next;
            if (node.value instanceof RentedSpace) return true;
        }

        return false;
    }

    public int vehiclesQuantity() {
        Node node = head;
        int count = 0;
        for (int i = 0; i < size; i++) {
            node = node.next;
            if (!node.value.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public int vehiclesQuantity(VehicleTypes type) {
        // Исключение isNull
        Objects.requireNonNull(type, "type - null");

        Node node = head;
        int count = 0;
        for (int i = 0; i < size; i++) {
            node = node.next;
            if (node.value.getVehicle().getType() == type) {
                count++;
            }
        }
        return count;
    }

    public boolean add(Space space) {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");

        Node node = new Node(space);
        return addLast(node);
    }

    public boolean add(int index, Space space) {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");

        // Исключение illegalIndex
        Node node = new Node(space);
        return addIndex(index, node);
    }

    public Space get(int index) {
        // Исключение illegalIndex
        return getNode(index).value;
    }

    public int getIndex(Space space) throws IlleagalRegistrationNumberFormat {
        return indexOf(space.getVehicle().getRegistrationNumber());
    }

    public Space set(int index, Space space) {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");
        Node node = new Node(space);
        return setNode(index, node);
    }

    public Space remove(int index) {
        return removeNode(index);
    }

    public boolean remove(Space space) throws IlleagalRegistrationNumberFormat {
        //todo space.equals() - done
        Node node = head;

        for (int i = 0; i < size; i++) {
            node = node.next;
            if (node.value.equals(space)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public Space[] getSpaces() {
        Space[] spaces = new Space[size];
        Node node = head;

        for (int i = 0; i < size; i++) {
            node = node.next;
            spaces[i] = node.value;
        }

        return spaces;
    }

}

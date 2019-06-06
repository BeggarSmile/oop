package model;

import java.time.LocalDate;
import java.util.*;

public class RentedSpacesFloor implements Floor, Cloneable {

    private Node head = new Node(null);
    private int size = 0;

    public RentedSpacesFloor(Space[] spaces) {
        for (int i = 0; i < spaces.length; i++) {
            addLast(new Node(spaces[i]));
        }
    }

    public boolean contains(Object object) {
        Node node = head;
        for (int i = 0; i < size; i++) {
            node = node.next;
            if (node.value.equals(object)) return true;
        }
        return false;
    }

    public <T> T[] toArray(T[] a) {
        //todo см у Фунтикова
        Space[] elementData = toArray();
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    public boolean containsAll(Collection<?> collection) {
        boolean flags = true;
        for (Object obj : collection)
            if (!contains(obj)) return false;
        return true;
    }

    public boolean removeAll(Collection<?> collection) {
        boolean flags = false;

        for (Object obj : collection) {
            Node node = head;
            for (int i = 0; i < size; i++) {
                node = node.next;
                if (node.value.equals(obj)) {
                    node.previous.next = node.next;
                    node.next.previous = node.previous;
                    node.previous = null;
                    node.next = null;
                    flags = true;
                }
            }
        }
        return flags;
    }

    public boolean retainAll(Collection<?> collection) {
        boolean flags = false;
            Node node = head;
            for (int i = 0; i < size; i++) {
                node = node.next;
                if (!collection.contains(node.value)) {
                    node.previous.next = node.next;
                    node.next.previous = node.previous;
                    node.previous = null;
                    node.next = null;
                    flags = true;
                }
            }
        return flags;
    }

    private class SpaceIterator implements Iterator<Space>{
        int indexPosition = 0;
        Node node = head.next;

        public boolean hasNext() {
            return size < indexPosition;
        }

        public Space next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node nextNode = node;
            indexPosition++;
            node = node.next;
            return nextNode.value;
        }
    }

    public Iterator<Space> iterator() {
        return new SpaceIterator();
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
        if (!(object instanceof RentedSpacesFloor || (((RentedSpacesFloor) object).size != size)))
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
        return size - floor.size();
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

    public boolean remove(Object object)  {
        Node node = head;

        for (int i = 0; i < size; i++) {
            node = node.next;
            if (node.value.equals(object)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public Space[] toArray() {
        Space[] spaces = new Space[size];
        Node node = head;

        for (int i = 0; i < size; i++) {
            node = node.next;
            spaces[i] = node.value;
        }

        return spaces;
    }

}

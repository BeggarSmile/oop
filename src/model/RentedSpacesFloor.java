package model;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Objects;

public class RentedSpacesFloor implements Floor, Cloneable{

    private Node head = new Node(null);
    private int size;

    public RentedSpacesFloor(Space[] spaces) {
        for (int i = 0; i < spaces.length; i++) {
            addLast(new Node(spaces[i]));
        }
    }
    //todo приватные методы работают с нодами, а не Space - done
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

    //остальное пофиксили
    private boolean addIndex(int index, Node addedNode) {
        Node node;
        //todo size == 0 - done
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
        Node node = head;

        if (index > size || size == 0) {
            return null;
        }
        else {
            for (int i = 0; i < size; i++) {
                node = node.next;
                if (i == index) return node;
            }
        }

        return null;
    }

    private Space setNode(int index, Node node) {
        Space space = node.value;
        return getNode(index).setValue(space);
    }

    private Space removeNode(int index) {
        Node node = head;
        Node removedNode = null;
        //todo removedNode.next = null; removedNode.previous = null; - done
        //todo size == 1 - done
        if (size == 1 && index == 0) {
            removedNode = head.next;
            head.next = null;
            head.previous = null;
            size--;
        }
        else if (index == 0) {
            //todo не корректно. 0-й нод должен быть убран нафиг, а в head.next = head.next.next, head.previous.next = head.next.next - done
            removedNode = head.next;
            head.next = head.next.next;
            head.previous.next = head.next.next;
            head.next.previous = head.previous;
            size--;
        }
        else if (index == size - 1) {
            //todo не корректно, аналогично предыдущему - done
            removedNode = head.previous;
            head.previous = head.previous.previous;
            head.next.previous = head.previous.previous;
            head.previous.next = head.next;
            size--;
        }
        else if (index < size) {
            //todo getNode(index) - done
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

    //todo чет не работает, не забудь спросить
    public String toString() {
        StringBuilder strBuild = new StringBuilder("Rented spaces: ");
        Node node = head;

        for (int i = 0; i < size; i++) {
            node = node.next;
            strBuild.append(String.format("%n", node.value.getPerson().toString()));
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
        return (object instanceof RentedSpacesFloor && ((RentedSpacesFloor) object).getSpaces() == getSpaces());
    }

    public Object clone() throws CloneNotSupportedException{
        RentedSpacesFloor clone = (RentedSpacesFloor)super.clone();

        return clone;
    }

    public boolean searchRentedSpace() {
        Node node = head;

        for (int i = 0; i < size; i++) {
            node = node.next;
            if (node.value instanceof RentedSpace) return true;
        }

        return false;
    }

    public LocalDate nearestRentEndsDate() {
        // Исключение notFound
        if (!searchRentedSpace()) throw new NoRentedSpaceException();

        Node node = head;
        LocalDate minDate = node.next.value.getSinceDate();

        for (int i = 0; i < size; i++) {
            node = node.next;
            if (node.value.getSinceDate().isBefore(minDate)) minDate = node.value.getSinceDate();
        }

        return minDate;
    }

    public Space spaceWithNearestRentEndsDate() {
        // Исключение notFound
        if (!searchRentedSpace()) throw new NoRentedSpaceException();

        Node node = head;
        LocalDate minDate = nearestRentEndsDate();

        for (int i = 0; i < size; i++) {
            node = node.next;
            if (node.value.getSinceDate() == minDate) return node.value;
        }

        return null;
    }

    public int vehiclesQuantity() {
        //todo циклом по нодам и считаем - done
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
        //todo циклом по нодам и считаем - done

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

    public int indexOf(String registrationNumber) throws IlleagalRegistrationNumberFormat {
        //todo циклом по нодам и считаем - done

        // Исключение isNull
        Objects.requireNonNull(registrationNumber, "registrationNumber - null");

        // Исключение illegalRegNumber
        PatternCheck patternCheck = new PatternCheck(registrationNumber);
        if (!patternCheck.check()) throw new IlleagalRegistrationNumberFormat();

        Node node = head;
        for (int i = 0; i < size; i++) {
            node = node.next;
            if (node.value.getVehicle().getRegistrationNumber().equals(registrationNumber))
                return i;
        }
        return -1;
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
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Node node = new Node(space);
        return addIndex(index, node);
    }

    public Space get(int index) {
        // Исключение illegalIndex
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        return getNode(index).value;
    }

    //TODO убираем дублирование поиска элемента по номеру - done
    public Space get(String registrationNumber) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(registrationNumber, "registrationNumber - null");

        // Исключение illegalRegNumber
        PatternCheck patternCheck = new PatternCheck(registrationNumber);
        if (!patternCheck.check()) throw new IlleagalRegistrationNumberFormat();

        if (hasSpace(registrationNumber)) {
            return getNode(indexOf(registrationNumber)).value;
        }

        // Исключение noSuchElement
        else throw new NoSuchElementException();
    }

    public int getIndex(Space space) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");

        return indexOf(space.getVehicle().getRegistrationNumber());
    }

    //TODO убираем дублирование поиска элемента по номера - done
    public boolean hasSpace(String registrationNumber) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(registrationNumber, "registrationNumber - null");

        // Исключение illegalRegNumber
        PatternCheck patternCheck = new PatternCheck(registrationNumber);
        if (!patternCheck.check()) throw new IlleagalRegistrationNumberFormat();

        return indexOf(registrationNumber) != -1;
    }

    public Space set(int index, Space space) {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");

        // Исключение illegalIndex
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Node node = new Node(space);
        return setNode(index, node);
    }

    public Space remove(int index) {
        // Исключение illegalIndex
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        return removeNode(index);
    }

    //TODO убираем дублирование поиска элемента по номеру - done
    public Space remove(String registrationNumber) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(registrationNumber, "registrationNumber - null");

        // Исключение illegalRegNumber
        PatternCheck patternCheck = new PatternCheck(registrationNumber);
        if (!patternCheck.check()) throw new IlleagalRegistrationNumberFormat();

        if (hasSpace(registrationNumber)) {
            return removeNode(indexOf(registrationNumber));
        }

        // Исключение noSuchElement
        else throw new NoSuchElementException();
    }

    public boolean remove(Space space) throws IlleagalRegistrationNumberFormat {
        // Исключение isNull
        Objects.requireNonNull(space, "space - null");

        if (hasSpace(space.getVehicle().getRegistrationNumber())){
            remove(space.getVehicle().getRegistrationNumber());
            return true;
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

    //todo need complete - done
    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[vehiclesQuantity()];
        int count = 0;
        Node node = head;
        //todo циклом по нодам - done
        for (int i = 0; i < size; i++) {
            node = node.next;
            if (node.value.getVehicle() != null) {
                vehicles[count] = node.value.getVehicle();
                count++;
            }
        }

        Vehicle[] newVehicles = new Vehicle[count];
        System.arraycopy(vehicles, 0, newVehicles, 0 ,count);
        return newVehicles;
    }

    public Space[] getSpaces(VehicleTypes type) {
        // Исключение isNull
        Objects.requireNonNull(type, "type - null");

        Space[] newSpaces = new Space[vehiclesQuantity(type)];
        int count = 0;
        Node node = head;
        //todo циклом по нодам - done
        for (int i = 0; i < size; i++) {
            node = node.next;
            if (!node.value.isEmpty() && node.value.getVehicle().getType() == type) {
                newSpaces[count] = node.value;
                count++;
            }
        }
        return newSpaces;
    }

    public int getSpaces(Person person) {
        // Исключение isNull
        Objects.requireNonNull(person, "person - null");

        int count = 0;
        Node node = head;

        for (int i = 0; i < size; i++) {
            node = node.next;
            if (node.value.getPerson() == person) count++;
        }

        return count;
    }

    public Space[] getEmptySpaces() {
        Space[] emptySpaces = new Space[size - vehiclesQuantity()];
        int count = 0;
        Node node = head;
        //todo циклом по нодам - done
        for (int i = 0; i < size; i++) {
            if (!node.value.isEmpty()) {
                emptySpaces[count] = node.value;
                count++;
            }
        }
        return emptySpaces;
    }

}

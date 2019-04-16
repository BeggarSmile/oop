package model;

public class RentedSpacesFloor implements Floor{

    private Node head;
    private int size;
    private Node first;
    private Node last;

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

    private boolean addLast(RentedSpace space) {
        Node node = new Node(space);
        last.setNext(node);
        first.setPrevious(node);
        node.setNext(first);
        node.setPrevious(last);
        last = node;
        head.setPrevious(last);
        size++;
        return true;
    }

    private boolean addIndex(int index, RentedSpace space) {
        Node node = head;
        Node addedNode = new Node(space);

        for (int i = 0; i < size; i++) {
            node = node.getNext();
            if (i == index) {
                node.getNext().setPrevious(addedNode);
                addedNode.setNext(node.getNext());
                node.setNext(addedNode);
                addedNode.setPrevious(node);
            }
        }

        return true;
    }

    private Node getNode(int index) {
        Node node = head;

        for (int i = 0; i < size; i++) {
            node = node.getNext();
            if (i == index) return node;
        }

        return node;
    }

    public boolean add(RentedSpace space) {
            return addLast(space);
    }

    public boolean add(int index, RentedSpace space) {
        return addIndex(index, space);
    }

    //todo need complete
    public RentedSpace get(int index) {
        return getNode(index).getValue();
    }

    //todo need complete
    public RentedSpace get(String registrationNumber) {
        return null;
    }

    //todo need complete
    public boolean hasSpace(String registrationNumber) {
        return true;
    }

    //todo need complete
    public RentedSpace set(int index, RentedSpace space) {
        return null;
    }

    //todo need complete
    public RentedSpace remove(int index) {
        return null;
    }

    //todo need complete
    public RentedSpace remove(String registrationNumber) {
        return null;
    }

    //todo need complete
    public int size() {
        return 0;
    }

    //todo need complete
    public RentedSpace[] getSpaces() {
        return null;
    }

    //todo need complete
    public Vehicle[] getVehicles() {
        return null;
    }
}

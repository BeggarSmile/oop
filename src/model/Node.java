package model;

public class Node {
    private Space value;
    private Node next;
    private Node previous;

    public Node(Space value, Node next, Node previous) {
        this.value = value;
        this.next = next;
        this.previous = previous;
    }

    public Node(Space value){
        this(value, null, null);
    }

    public Space setValue(Space value) {
        Space oldSpace = value;
        this.value = value;
        return oldSpace;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Space getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrevious() {
        return previous;
    }
}

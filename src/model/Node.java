package model;

class Node implements Cloneable{
    Space value;
    Node next;
    Node previous;

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

    public Node clone() throws CloneNotSupportedException{
        Node clone = (Node)super.clone();

        return clone;
    }
}

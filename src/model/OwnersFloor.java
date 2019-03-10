package model;

public class OwnersFloor {
    int NumberSpace = 4;
    int NumberHolder = 0;

    Space[] FloorSpace = new Space[4];

    public OwnersFloor () {
        this.FloorSpace = new Space[16];
        NumberSpace = 16;
    }

    public OwnersFloor (int NumberSpace) {
        this.FloorSpace = new Space[NumberSpace];
        this.NumberSpace = NumberSpace;
    }

    public OwnersFloor (Space[] FloorSpace, int NumberHolder) {
        this.FloorSpace = FloorSpace;
        NumberSpace = FloorSpace.length;
        this.NumberHolder = NumberHolder;
    }

    public void addSpace (Object Space) {
    }
}

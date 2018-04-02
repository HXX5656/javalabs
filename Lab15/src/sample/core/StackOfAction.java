package sample.core;

import javafx.scene.image.ImageView;

public class StackOfAction {
    private static Direction[] elements;
    private static int size;
    public static final int DEFAULT_CAPACITY = 16;

    public StackOfAction() {
        this(DEFAULT_CAPACITY);
    }

    public StackOfAction(int capacity) {
        //创建空间
        elements = new Direction[capacity];
    }

    public void push(Direction move) {
        if (size >= elements.length) {
            Direction[]temp = new Direction[elements.length * 2];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }
        elements[size++] = move;
    }

    public Direction pop() {
        if (empty()) {
            System.out.println("您已无路可退。");
            return elements[0];
        }
        else {
        return elements[--size];
        }
    }

    public Direction peek() {
        if (empty()) {
            System.out.println("您已无路可退。");
            return elements[0];
        } else {
            return elements[size - 1];
        }
    }

    public boolean empty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
    public static void footpoint(Icon[][] map, int x, int y) {
        for (int i = 0; i <map.length ; i++) {
            for (int j = 0; j <map[0].length ; j++) {
                if (map[i][j]==Icon.FOOTPRINT) {
                    map[i][j]=Icon.EMPTY;
                }
            }
        }
        int posX=x;
        int posY=y;
        if (size-1>=0) {
            switch (elements[size-1]) {
                case SOUTH:
                    if (posX-1>=1&&posY>=1) {
                        map[--posX][posY]=Icon.FOOTPRINT;
                    }
                    break;
                case EAST:
                    if (posX>=1&&posY-1>=1) {
                        map[posX][--posY]=Icon.FOOTPRINT;
                    }
                    break;
                case NORTH:
                    if (posX+1>=1&&posY>=1) {
                        map[++posX][posY]=Icon.FOOTPRINT;
                    }

                    break;
                case WEST:
                    if (posX>=1&&posY+1>=1) {
                        map[posX][++posY]=Icon.FOOTPRINT;
                    }
                    break;
            }
        }
        if (size-2>=0) {
            switch (elements[size-2]) {
                case SOUTH:
                    if (posX-1>=1&&posY>=1) {
                        map[--posX][posY]=Icon.FOOTPRINT;
                    }
                    break;
                case EAST:
                    if (posX>=1&&posY-1>=1) {
                        map[posX][--posY]=Icon.FOOTPRINT;
                    }
                    break;
                case NORTH:
                    if (posX+1>=1&&posY>=1) {
                        map[++posX][posY]=Icon.FOOTPRINT;
                    }

                    break;
                case WEST:
                    if (posX>=1&&posY+1>=1) {
                        map[posX][++posY]=Icon.FOOTPRINT;
                    }
                    break;
            }
        }
        map[x][y]=Icon.HERO;
    }
    public static void setSize(int value) {
        size=value;
    }


}

public class StackOfAction {
    private static char[] elements;
    private static int size;
    public static final int DEFAULT_CAPACITY = 16;

    public StackOfAction() {
        this(DEFAULT_CAPACITY);
    }

    public StackOfAction(int capacity) {
        //创建空间
        elements = new char[capacity];
    }

    public void push(char move) {
        if (size >= elements.length) {
            char[]temp = new char[elements.length * 2];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }
        elements[size++] = move;
    }

    public char pop() {
        if (empty()) {
            System.out.println("您已无路可退。");
            return elements[0];
        } else {
            return elements[--size];
        }
    }

    public char peek() {
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
    public static void footpoint(char[][] map,int x,int y) {
        for (int i = 0; i <map.length ; i++) {
            for (int j = 0; j <map[0].length ; j++) {
                if (map[i][j]=='．') {
                    map[i][j]='　';
                }
            }
        }
        int posX=x;
        int posY=y;
        if (size-1>=0) {
        switch (elements[size-1]) {
            case 's':
                if (posX-1>=1&&posY>=1) {
                map[--posX][posY]='．';
                }
                break;
            case 'd':
                if (posX>=1&&posY-1>=1) {
                map[posX][--posY]='．';
                }
                break;
            case 'w':
                if (posX+1>=1&&posY>=1) {
                    map[++posX][posY]='．';
                }

                break;
            case 'a':
                if (posX>=1&&posY+1>=1) {
                    map[posX][++posY]='．';
                }
                break;
        }
        }
        if (size-2>=0) {
        switch (elements[size-2]) {
            case 's':
                if (posX-1>=1&&posY>=1) {
                    map[--posX][posY]='．';
                }
                break;
            case 'd':
                if (posX>=1&&posY-1>=1) {
                    map[posX][--posY]='．';
                }
                break;
            case 'w':
                if (posX+1>=1&&posY>=1) {
                    map[++posX][posY]='．';
                }

                break;
            case 'a':
                if (posX>=1&&posY+1>=1) {
                    map[posX][++posY]='．';
                }
                break;
        }
    }
    map[x][y]='○';
    }
    public static void setSize(int value) {
        size=value;
    }


}

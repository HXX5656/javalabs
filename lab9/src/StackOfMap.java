public class StackOfMap {
    private char[][][] elements;
    private int size;
    public static final int DEFAULT_CAPACITY = 16;

    public StackOfMap() {
        this(DEFAULT_CAPACITY);
    }

    public StackOfMap(int capacity) {
        //创建空间，我的程序用的都是21*21的地图
        elements = new char[capacity][21][21];
    }

    public void push(char[][] map) {
        if (size >= elements.length) {
            char[][][] temp = new char[elements.length * 2][21][21];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }
        elements[size++] = map;//指针指向这里?所以变了？恩恩
    }

    public char[][] pop() {
        if (empty()) {
            System.out.println("您已无路可退。");
            return elements[0];
        } else {
            return elements[--size];
        }
    }

    public char[][] peek() {
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

    public void out() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 21; j++) {
                for (int k = 0; k < 21; k++) {
                    System.out.print("" + elements[i][j][k]);
                }
                System.out.print("\n");
            }
            System.out.print("\n");

        }
    }
}

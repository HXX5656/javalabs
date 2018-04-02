public class Util {

    public static char[][] array2DCopy(char[][] origin) {
        char[][] map0 = new char[21][21];
        for (int m = 0; m < 21; m++) {
            System.arraycopy(origin[m], 0, map0[m], 0, 21);
        }
        return map0;
    }
}

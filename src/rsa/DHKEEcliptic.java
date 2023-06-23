package rsa;

public class DHKEEcliptic {


    public static long singleKey(Point p, int k, int a, int n) {
        Point key = DoubleAndAddAlgorithm.applyDoubleAndAddAlgorithm(k, p, n, a);
        System.out.println("Final point is " + key);
        System.out.println("Key is " + key.x);
        return key.x;
    }

    public static void main(String[] args) {
        System.out.println(singleKey(new Point(5, 9), 6, 1, 11));
    }
}

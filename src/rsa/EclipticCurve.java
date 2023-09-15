package rsa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EclipticCurve {

    public static List<Point> calculatePoints(int a, int b, int n) {
        System.out.println("------------Y side of things-----------");
        System.out.println("y     |    y^2");
        StringBuilder stringBuilder = new StringBuilder();

        List<List<Integer>> yLists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            yLists.add(new ArrayList<>());
        }
        stringBuilder.append("x     |    z\n");

        int[] xList = new int[n];
        for (int i = 0; i < n; i++) {
            int y2 = (i * i) % n;
            System.out.printf("%d     |   %d\n", i, y2);
            yLists.get(y2).add(i);
            int z = (((y2 * i) % n) + ((a * i) % n) + b) % n;
            xList[i] = z;
            stringBuilder.append(String.format("%d     |   %d\n", i, z));
        }

        System.out.println("------------X side of things-----------");
        System.out.println(stringBuilder);
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < xList.length; i++) {

            for (int y : yLists.get(xList[i])) {
                points.add(new Point(i, y));
                System.out.print(points.get(points.size() - 1));
            }
            System.out.println();

        }
        System.out.println(points);
        System.out.println("The order of the group is " + (points.size() + 1));
        return points;
    }

    // y = x ^ 3 + ax + b
    public static void main(String[] args) {
        calculatePoints(1, 0, 7);
        //genPoints(new Point(0, 3), 7, 3, true);
    }

    public static void genPoints(Point p1, long p, long a, boolean print) {
        Point first = p1;
        Point second = p1;
        int count = 1;
        System.out.println("------------Points Generation-----------------");
        System.out.println(p1);
        while (true) {
            System.out.println();
            System.out.println("P1 = " + first + "    P2 = " + second);
            Point result = addPoints(first, second, p, a, print);
            count++;
            if (result == null) {
                System.out.println("Points gen complete");
                System.out.println("Total points gen included phi is: " + count);
                break;
            }
            second = result;
        }
    }


    public static Point addPoints(Point p1, Point p2, long p, long a, boolean print) {

        long s = 0;
        if (p1.equals(p2)) {
            if (print) {
                System.out.println("P1 is equal to P2");
                System.out.println("Apply formula S = (3 * x1 ^ 2 + a) / 2y1  mod p");
                System.out.printf("S = (3 * %d ^ 2 + a)/ 2 * %d  mod %d\n", p1.x, p1.y, p);
            }
            s = ((3 * p1.x * p1.x) + a) % p *
                    (Modulo.getInverseUsingExtendedEuclidean((2 * p1.y) % p, p, false) % p);
        } else {
            if (p2.x - p1.x == 0) {
                return null;
            }
            if (print) {
                System.out.println("P1 is not equal to P2");
                System.out.println("Apply formula S = (y2 - y1) / (x2- x1) mod p");
                System.out.printf("S = (%d - %d) / (%d- %d) mod %d\n", p2.y, p1.y, p2.x, p1.x, p);
            }
            s = (Modulo.extractPositiveMod(p2.y - p1.y, p) *
                    (Modulo.getInverseUsingExtendedEuclidean(Modulo.extractPositiveMod(p2.x - p1.x, p), p, false) % p));
        }

        s = s % p;
        if (print) {
            System.out.printf("S = %d\n", s);
            System.out.println("x3 = S ^ 2 - x1 - x2  mod p ");
            System.out.printf("x3 = %d ^ 2 - %d - %d  mod %d\n", s, p1.x, p2.x, p);
        }
        int x3 = (int) Modulo.extractPositiveMod(s * s - p1.x - p2.x, p);
        if (print) {
            System.out.println("x3 = " + x3);
            System.out.println("y3 = S(x1 - x3) - y1  mod p");
            System.out.printf("y3 = %d(%d - %d) - %d  mod %d\n", s, p1.x, x3, p1.y, p);
        }
        int y3 = (int) Modulo.extractPositiveMod(s * (p1.x - x3) - p1.y, p);

        Point point = new Point(x3, y3);
        if (print) {

            System.out.println("y3 = " + y3);
            System.out.println(point);
        }

        return point;
    }
}


class Point {
    int x;
    int y;

    @Override
    public String toString() {
        return "(" +
                x +
                ", " + y +
                ')';
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

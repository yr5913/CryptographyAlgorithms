package rsa;

public class Modulo {


    public static void applyEucledian(int x, int y) {
        int big = x;
        int small = y;
        if (y > x) {
            big = y;
            small = x;
        }

        while (true) {
            int quo = big / small;
            int rem = big % small;
            System.out.printf("%d = %d . %d + %d\n", big, quo, small, rem);
            if (rem == 0) {
                break;
            }
            big = small;
            small = rem;
        }

    }


    public static void main(String[] args) {
        applyEucledian(32, 640);
    }
}

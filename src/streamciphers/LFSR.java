package streamciphers;

import java.util.Arrays;

public class LFSR {

    /**
     * Generates the keystream using Linear feedback shift registers
     *
     * @param onOrOff      the sbox values ex:(s5,s4,s3,s2,s1,s0) == (0, 1, 1, 0, 1, 1)
     *                     feedback of s4, s3, s1, s0 is used for s5 generation in the next round
     * @param initialFeed  the initial feed value for sboxes
     * @param print        if the generation sequence with the steps need to be printed in console or not
     *                     suggested value to be true if you are learning the algorithm
     * @param numberOfGens optional parameter
     * @return the keystream generated
     */
    public static String keyStream(int[] onOrOff, int[] initialFeed, boolean print, int numberOfGens) {
        StringBuilder stringBuilder = new StringBuilder();
        int[] current = Arrays.copyOf(initialFeed, initialFeed.length);
        if (print) {
            System.out.println("The polynomial is: " + polynomial(onOrOff));
            System.out.println(Arrays.toString(initialFeed));
        }
        int i = 0;
        while (true) {
            int nextBit = nextBit(current, onOrOff);
            int output = current[current.length - 1];
            shift(current);
            current[0] = nextBit;
            if (print) {
                System.out.println(Arrays.toString(current) + "\t" + output);
            }

            stringBuilder.append(output);
            i++;
            if (numberOfGens > 0) {
                if (i == numberOfGens) {
                    break;
                }
            } else {
                if (equals(current, initialFeed)) {
                    break;
                }
            }
        }
        if (print) {
            System.out.println("The length before repeat is " + stringBuilder.length());
        }
        return stringBuilder.toString();
    }

    public static String polynomial(int[] onOrOff) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("X ^ ");
        stringBuilder.append(onOrOff.length);
        for (int i = onOrOff.length - 1; i > -1; i--) {
            if (onOrOff[i] == 1) {
                stringBuilder.append(" + ");
                stringBuilder.append("X ^ ");
                stringBuilder.append(i);
            }
        }
        return stringBuilder.toString();
    }

    private static int nextBit(int[] current, int[] onOrOff) {
        int sum = 0;
        for (int i = 0; i < current.length; i++) {
            if (onOrOff[i] == 1) {
                sum += current[i];
            }
        }
        return sum % 2;
    }

    private static void shift(int[] current) {
        for (int i = current.length - 1; i > 0; i--) {
            current[i] = current[i - 1];
        }
    }

    private static boolean equals(int[] current, int[] initial) {
        for (int i = 0; i < current.length; i++) {
            if (current[i] != initial[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(keyStream(new int[]{0, 1, 0, 1, 1}, new int[]{1, 0, 0, 1, 1}, true, -1));
    }
}

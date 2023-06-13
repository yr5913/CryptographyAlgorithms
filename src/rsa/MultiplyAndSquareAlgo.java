package rsa;

import utils.Helpers;

public class MultiplyAndSquareAlgo {

    public static void main(String[] args) {
        System.out.println(findMod(3, 197, 101, true));
    }

    public static int findMod(int x, int e, int n, boolean print) {
        String binary = Integer.toBinaryString(e);
        if (print)
            System.out.printf("Binary representation of %d is %s\n", e, binary);

        char[] binaryChars = binary.toCharArray();
        int padLen = 25;
        StringBuilder iStringBuilder = new StringBuilder();
        iStringBuilder.append(Helpers.leftPad("i", ' ', padLen));
        iStringBuilder.append(" |");
        StringBuilder hStringBuilder = new StringBuilder();
        hStringBuilder.append(Helpers.leftPad("h", ' ', padLen));
        hStringBuilder.append(" |");
        StringBuilder y2StringBuilder = new StringBuilder();
        y2StringBuilder.append(Helpers.leftPad("y^2", ' ', padLen));
        y2StringBuilder.append(" |");
        StringBuilder yxStringBuilder = new StringBuilder();
        yxStringBuilder.append(Helpers.leftPad("y.x", ' ', padLen));
        yxStringBuilder.append(" |");
        int y = x;
        for (int i = 1; i < binaryChars.length; i++) {
            iStringBuilder.append(Helpers.leftPad(binaryChars.length - i - 1, ' ', padLen));
            iStringBuilder.append(" |");
            hStringBuilder.append(Helpers.leftPad(binaryChars[i] - '0', ' ', padLen));
            hStringBuilder.append(" |");
            int result = (y * y) % n;
            y2StringBuilder.append(Helpers.leftPad(String.format("%d ^ 2 mod %d = %d", y, n, result), ' ', padLen));
            y2StringBuilder.append(" |");
            y = result;
            if (binaryChars[i] == '1') {
                result = (y * x) % n;
                yxStringBuilder.append(Helpers.leftPad(String.format("%d * %d mod %d = %d", y, x, n, result), ' ', padLen));
                yxStringBuilder.append(" |");
                y = result;
            } else {
                yxStringBuilder.append(Helpers.leftPad("NA", ' ', padLen));
                yxStringBuilder.append(" |");

            }
        }
        if (print) {
            System.out.println(iStringBuilder);
            System.out.println(hStringBuilder);
            System.out.println(y2StringBuilder);
            System.out.println(yxStringBuilder);
        }

        return y;
    }
}
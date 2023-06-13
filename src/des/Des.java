package des;

import utils.Helpers;

public class Des {


    private static int[][][] sboxes = new int[][][]{
            Sboxes.S1, Sboxes.S2, Sboxes.S3, Sboxes.S4, Sboxes.S5, Sboxes.S6, Sboxes.S7, Sboxes.S8
    };

    private static String storedKey = "";

    private static String[] storedKeys = new String[17];

    public static int[] keyPermutationTwo = new int[]{14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};
    public static int[] initKeyPermutation = new int[]{57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4};

    public static int[] heartFunctionExpansion = new int[]{32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};
    public static int[] heartFunctionFinalPermut = new int[]{16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25};
    public static int[] initialPermutation = new int[]{58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7};

    public static String getSboxOutput(String ascii, int[][] Sbox) {
        String value1 = "" + ascii.charAt(0) + ascii.charAt(5);
        String value2 = ascii.substring(1, ascii.length() - 1);
        int row = Integer.parseInt(value1, 2);
        int column = Integer.parseInt(value2, 2);
        int valueInDecimal = Sbox[row][column];
        String ans = Helpers.leftPad(Integer.toBinaryString(valueInDecimal), '0', 4);
        System.out.printf("SBOX(%s) == ROW %d COLUMN %d == %d == %s\n", ascii, row, column, valueInDecimal, ans);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(getSboxOutput("010001", Sboxes.S1));
        System.out.println(getSboxOutput("010101", Sboxes.S1));


        String x = "";
        for (int i = 0; i < 64; i++) {
            x += "0";
        }
        scheduleKey(5, x, true);

        String y = applyDes(x, x, 1, true);
        System.out.println(y);
    }

    public static String scheduleKey(int rounds, String initialKey, boolean print) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("=========================Key Scheduling Algorithm===================\n");
        String key = "";
        if (initialKey.equals(storedKey)) {

        } else {
            stringBuilder.append("Key didn't match, starting rounds\n");
            stringBuilder.append("---------------Initial Permutation--------------\n");
            stringBuilder.append("Key after initial Permutation\n");
            String permutedKey = Permutation.permutation(initKeyPermutation, initialKey);
            stringBuilder.append(permutedKey + "\n");
            if (print) {
                System.out.println(stringBuilder);
            }
            storedKeys[0] = permutedKey;
            for (int i = 0; i < rounds; i++) {
                key = scheduleKeySingleRound(storedKeys[i], i + 1, print);
            }
        }

        return key;
    }

    public static String scheduleKeySingleRound(String prevKey, int round, boolean print) {
        String key = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("----Round " + round + " ------------------------\n");
        stringBuilder.append("After left shift key is\n");
        String left = prevKey.substring(0, 28);
        String right = prevKey.substring(28);
        String leftShift = shift(left, 1);
        String rightShift = shift(right, 1);
        stringBuilder.append(leftShift);
        stringBuilder.append(" ");
        stringBuilder.append(rightShift);
        stringBuilder.append("\n");
        storedKeys[round] = leftShift + rightShift;
        key = Permutation.permutation(keyPermutationTwo, storedKeys[round - 1]);
        stringBuilder.append("After permute final round key is\n");
        stringBuilder.append(key + "\n");
        if (print)
            System.out.println(stringBuilder);

        return key;
    }

    private static String shift(String text, int shiftBy) {
        char[] result = new char[text.length()];
        char[] current = text.toCharArray();
        for (int i = 0; i < text.length(); i++) {
            result[(i + shiftBy) % current.length] = current[i];
        }
        return String.valueOf(current);
    }

    public static String applyDes(String text, String initialKey, int rounds, boolean print) {
        scheduleKey(rounds, initialKey, print);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("=============Start DES Algo=================\n");
        stringBuilder.append("=======Initial Permutation=======\n");
        stringBuilder.append("Text after initial permutation\n");
        String encrypt = Permutation.permutation(initialPermutation, text);
        stringBuilder.append(encrypt + "\n");
        System.out.println(stringBuilder);
        String prevText = encrypt;
        for (int i = 0; i < rounds; i++) {
            prevText = singleDesRound(prevText, print, i + 1);
        }
        return prevText;
    }

    private static String singleDesRound(String prevText, boolean print, int roundNumber) {
        String prevLeft = prevText.substring(0, 32);
        String prevRight = prevText.substring(32);

        String currentLeft = prevRight;
        String currentRight = Helpers.xor(function(prevRight, storedKeys[roundNumber]), prevLeft);
        String ans = currentLeft + currentRight;
        System.out.println("Output after one round");
        Helpers.printAscii(ans, 4);
        return ans;
    }

    public static String function(String vector, String key) {

        System.out.println("---------Apply Heart Function--------------");
        String expand = Permutation.permutation(heartFunctionExpansion, vector);
        System.out.println("Text after expansion");
        System.out.println(expand);
        String result = Helpers.xor(expand, key);
        System.out.println("Text after xor");
        System.out.println(result);
        System.out.println("---Apply Sboxes--");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            stringBuilder.append(getSboxOutput(result.substring(6 * i, 6 * i + 6), sboxes[i]));
        }
        String sBoxOutput = stringBuilder.toString();
        System.out.println("Sbox output in pretty: ");
        Helpers.printAscii(sBoxOutput, 4);
        System.out.println("Final permut pretty");
        String ans = Permutation.permutation(heartFunctionFinalPermut, sBoxOutput);
        Helpers.printAscii(ans,4);
        return ans;
    }


}

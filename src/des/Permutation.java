package des;

import utils.Helpers;

import java.util.Arrays;

public class Permutation {

    public static String permutation(int[] permuteArray, String text) {
        Helpers.removeSpaces(text);
        char[] chars = new char[permuteArray.length];
        char[] currentChars = text.toCharArray();
        for (int i = 0; i < permuteArray.length; i++) {
            chars[i] = currentChars[permuteArray[i] - 1];
        }
        return String.valueOf(chars);
    }

    public static int[] getInverseOfPermutation(int[] permuteArray) {
        int[] tempPos = new int[permuteArray.length];
        for (int i = 0; i < tempPos.length; i++) {
            tempPos[permuteArray[i] - 1] = i + 1;
        }
        return tempPos;
    }

    public static void main(String[] args) {
        int[] permuteArray = new int[]{8, 13, 4, 9, 16, 5, 12, 1, 7, 14, 3, 10, 15, 6, 11, 2};
        System.out.println(permutation(permuteArray, "1011000110101100"));
        System.out.println(Arrays.toString(getInverseOfPermutation(permuteArray)));
        System.out.println(getInverseOfPermutation(permuteArray));
    }
}

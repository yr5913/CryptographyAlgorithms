package ceaser;

import utils.Helpers;

public class TranspositionCipher {


    public static String encrypt(String text, int key) {
        int current = 0;
        char[] array = Helpers.removeSpaces(text).toCharArray();
        StringBuilder sb = new StringBuilder();
        while (current < array.length) {
            char[][] matrix = new char[key][key];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[i][j] = current < array.length ? array[current++] : 'a';
                }
            }
            transpose(matrix);
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    sb.append(matrix[i][j]);
                }
            }
        }
        return sb.toString();

    }


    private static void transpose(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                char temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(TranspositionCipher.encrypt("elliptic curve cryptosystems", 4));
    }
}

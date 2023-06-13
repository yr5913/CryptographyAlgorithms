package ceaser;

import utils.Helpers;

public class PermutationCipher {

    public static String encrypt(String text, int[] key) {
        text = Helpers.removeSpaces(text);
        char[] characters = text.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            if (i + key.length <= characters.length) {
                encryptABlock(i, key, characters, stringBuilder);
                i = i + key.length;
            } else {
                char[] extraBlock = new char[key.length];
                int start = i;
                while (i < text.length()) {
                    extraBlock[i - start] = characters[i];
                    i++;
                }
                encryptABlock(0, key, extraBlock, stringBuilder);
            }
        }
        return stringBuilder.toString();
    }

    private static void encryptABlock(int start, int[] key, char[] array, StringBuilder stringBuilder) {
        for (int i = start; i < start + key.length; i++) {
            char current = array[key[i - start] + start - 1];
            if (current != '\u0000') {
                stringBuilder.append(current);
            }
        }
    }

    public static void main(String[] args) {
        int[] key = {5, 1, 2, 3, 4};
        System.out.println(PermutationCipher.encrypt("elliptic curve cryptosystems", key));
    }
}

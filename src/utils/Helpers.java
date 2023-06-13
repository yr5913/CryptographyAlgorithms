package utils;

public class Helpers {
    public static String removeSpaces(String text) {
        char[] array = text.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ' ') {
                continue;
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public static String xor(String key, String encrypt) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = key.toCharArray();
        char[] encrypted = encrypt.toCharArray();
        for (int i = 0; i < key.length(); i++) {
            char current = '1';
            if (chars[i] == encrypted[i]) {
                current = '0';
            }

            stringBuilder.append(current);
        }
        return stringBuilder.toString();
    }

    public static String asciiToText(String text, int bits) {
        StringBuilder stringBuilder = new StringBuilder();
        int loop = text.length() / bits;
        int start = 0;
        for (int i = 0; i < loop; i++) {
            String current = text.substring(start, start + bits);
            stringBuilder.append((char) asciiCharToInt(current, bits));
            start += bits;
        }
        return stringBuilder.toString();
    }

    public static int asciiCharToInt(String character, int bits) {
        char[] chars = character.toCharArray();
        int sum = 0;
        int pow = chars.length - 1;
        for (int i = 0; i < chars.length; i++) {
            sum += (Math.pow(2, pow--) * Character.getNumericValue(chars[i]));
        }
        return sum;
    }

    public static String leftPad(String text, char paddingChar, int len) {
        int padlen = len - text.length();
        for (int i = 0; i < padlen; i++) {
            text = paddingChar + text;

        }
        return text;
    }

    public static String leftPad(int digit, char paddingChar, int len) {
        return leftPad(String.valueOf(digit), paddingChar, len);
    }

    public static void printAscii(String text, int bits) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < text.length(); i++) {
            if (i % bits == 0) {
                System.out.print(" ");
            }
            ;
            System.out.print(chars[i]);
        }
        System.out.println();
    }

    public static String textToAscii(String text, int bits) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            stringBuilder.append(charToAscii(chars[i], bits));
        }
        return stringBuilder.toString();
    }

    public static String charToAscii(char c, int bits) {
        StringBuilder current = new StringBuilder();
        int x = c;
        while (x != 0) {
            current.append(x % 2);
            x = x / 2;
        }
        while (current.length() != bits) {
            current.append('0');
        }
        return current.reverse().toString();
    }

}

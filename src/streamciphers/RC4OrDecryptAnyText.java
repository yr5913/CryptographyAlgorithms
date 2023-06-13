package streamciphers;

import utils.Helpers;

public class RC4OrDecryptAnyText {

    public static void main(String[] args) {

        String text = "01000011 00011011 00010010 00110000 11111000 10100111" +
                "10001110 11101001 00010100 00011101 01100100";
        text = Helpers.removeSpaces(text);
        // Convert the plain text to Ascii
        String givenText = "BARACKOBAMA";
        String asciiGivenText = Helpers.textToAscii(givenText, 8);
        //perform XOR on plain text and cipher text to get key
        String key = Helpers.xor(text, asciiGivenText);
        // remove the nonce on the key to get actual key
        key = removeNonce(key, 1, 8);
        //Add different nonce to key
        key = removeNonce(key, -2, 8);
         String newEncrypted = "01000110 00010100 00001111 00110011 11110000 10101001" +
                "10010110 11111110 00000011 00011100 01110110";

        newEncrypted = Helpers.removeSpaces(newEncrypted);
        // perform xor on nonce key and encrypted text to get the plain text ascii encoding
        String actualAscii = Helpers.xor(key, newEncrypted);
        // get the plain text from ascii encoding
        String decrypted = Helpers.asciiToText(actualAscii, 8);
        System.out.println(decrypted  );
        System.out.println(key);
        System.out.println(asciiGivenText);
        System.out.println(text);
        System.out.println();
    }

    public static String removeNonce(String text, int nonce, int bits){
        StringBuilder stringBuilder = new StringBuilder();
        int loop = text.length() / bits;
        int start = 0;
        for (int i = 0; i < loop; i++) {
            String current = text.substring(start, start + bits);
            int sum = Helpers.asciiCharToInt(current, bits);
            sum-=nonce;
            stringBuilder.append(Helpers.charToAscii((char) sum, bits));
            start += bits;
        }
        return stringBuilder.toString();
    }
}

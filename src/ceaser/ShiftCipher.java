package ceaser;

import java.util.Arrays;

public class ShiftCipher {


    public static String encrypt(String text, int key) {
        char[] textChars = text.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char singleChar : textChars) {
            char start = 'a' > singleChar ? 'A' : 'a';
            stringBuilder.append((char) (start + (singleChar - start + key) % 26));
        }
        return stringBuilder.toString();
    }

    public static String decrypt(String text, int key) {
        return encrypt(text, 26 - key);
    }

    public static String[] decryptWithoutKey(String text) {
        String[] array = new String[25];
        for (int i = 1; i < 26; i++) {
            array[i - 1] = decrypt(text, i);
        }
        return array;
    }

    public static void main(String[] args) {

        // CRYPTO
        System.out.println(Arrays.toString(decryptWithoutKey("svunhnvpuhaptlmvynvaaluhwylalyuhabyhslcluaaoyldaolzlhzvuzvb\n" +
                "avmihshujlpuhshukdolylzbttlyzjhushzakljhklzhukdpualyzhspmlaptlayvbislpziyldpun")));
    }
}

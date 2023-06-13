package ceaser;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class SubstitutionCipher {
    // public static char[] alphabetsInOrder = "etaoinshrdlcumwfgypbvkjxqz".toCharArray();
    public static char[] alphabetsInOrder = "etoarsnhldifmupwbcygvkxj".toCharArray();

    public static Frequency[] getFrequencyTable(String text) {
        char[] textChars = text.toLowerCase().toCharArray();
        Frequency[] frequencyArray = new Frequency[26];
        for (char singleChar : textChars) {
            if (singleChar < 'a' || singleChar > 'z') {
                continue;
            }
            int index = singleChar - 'a';
            if (frequencyArray[index] == null) {
                frequencyArray[index] = new Frequency(singleChar, 1);
            } else {
                frequencyArray[index].setFrequency(frequencyArray[index].getFrequency() + 1);
            }
        }
        double totalFreq = Arrays.stream(frequencyArray).filter(a -> a != null).mapToDouble(a -> a.getFrequency()).sum();
        Arrays.stream(frequencyArray)
                .forEach(a -> {
                    if (a == null) {
                        return;
                    }
                    BigDecimal bigDecimal = new BigDecimal(a.getFrequency() * 100);
                    bigDecimal = bigDecimal.divide(new BigDecimal(totalFreq), 4, 4);
                    a.setFrequency(bigDecimal.doubleValue());
                });
        System.out.println(Arrays.toString(frequencyArray));

        Arrays.sort(frequencyArray, (a1, a2) -> a1 == null ? 1 : a2 == null ? -1 : Double.compare(a2.getFrequency(), a1.getFrequency()));

        Arrays.stream(frequencyArray).filter(a -> a != null).forEach(a -> System.out.println(a.getFrequency()));
        Arrays.stream(frequencyArray).filter(a -> a != null).forEach(a -> System.out.println(Character.toUpperCase(a.getLetter())));
        return frequencyArray;
    }


    public static String replace(Frequency[] frequencies, String text) {
        int[] pos = new int[26];
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] != null)
                pos[frequencies[i].getLetter() - 'a'] = i;
        }
        StringBuilder sb = new StringBuilder();
        for (char singleChar : text.toCharArray()) {
            if ((singleChar >= 'a' && singleChar <= 'z') || (singleChar >= 'A' && singleChar <= 'Z')) {
                char start = 'a';
                if (singleChar < 'a')
                    start = 'A';
                char current = SubstitutionCipher.alphabetsInOrder[pos[singleChar - start]];
                if (singleChar < 'a')
                    current = Character.toUpperCase(current);
                sb.append(current);
                continue;
            }
            sb.append(singleChar);
        }
        return sb.toString();
    }

    public static void replaceContinueWithUserInput(String text) {
        text = text.toLowerCase();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("choose first: ");
                String first = scanner.next();
                System.out.println("choose second: ");
                String second = scanner.next();
                text = text.replace(first.charAt(0), second.charAt(0));
                System.out.println(text);
                System.out.println("Satisfied");
                String ok = scanner.next();
                if (ok.startsWith("y")) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = """
                Ozgvi, rg dzh hzrw gsv nzm xznv uiln gsv mligs, uiln Ilkvih Tzgv. Sv xznv
                lm ullg, ovzwrmt srh ozwvm slihv yb gsv yirwov. Rg dzh ozgv zugvimllm zmw
                gsv ilkvih, hzwwovih zmw gzmmvih hgzooh dviv zoivzwb xolhvw, gsv hgivvg vnkgb.
                Rg dzh slg yfg gsv nzm szw z yozxp xlzg gsildm levi srh hslfowvih. Sv wivd
                zggvmgrlm gl srnhvou. Sv hglkkvw rm uilmg lu gsv Low Mzizplig Rmm, hgllw
                gsviv uli z nlnvmg, orhgvmvw gl gsv sfyyfy lu elrxvh. Zh fhfzo, zg gsrh
                slfi, rg dzh ufoo lu kvlkov. Gsv hgizmtvi wrw mlg vmgvi gsv Low Mzizplig.
                Sv kfoovw srh slihv ufigsvi wldm gsv hgivvg gl zmlgsvi gzevim, z hnzoovi
                lmv, xzoovw Gsv Ulc. Mlg
                vmqlbrmt gsv yvhg lu ivkfgzgrlmh, rg dzh zonlhg vnkgb.
                """;
        Frequency[] frequencies = getFrequencyTable(text);
        System.out.println(Arrays.toString(frequencies));
        System.out.println(replace(frequencies, text));
        //replaceContinueWithUserInput(text);
    }
}

class Frequency {
    char letter;
    double frequency;

    public Frequency(char letter, int frequency) {
        this.letter = letter;
        this.frequency = frequency;
    }

    public char getLetter() {
        return letter;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return letter + " --> " + frequency;
    }
}

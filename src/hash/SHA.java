package hash;

import java.util.*;

public class SHA {
    private static final String[] CONSTANTS = new String[]{"00000000000000000000000000000011",
            "00000000000000000000000000000101",
            "00000000000000000000000000000111",
            "00000000000000000000000000001011",
            "00000000000000000000000000001101",
            "00000000000000000000000000010001"};

    private static final int LENGTH = 64;

    private static final String INIT_VECTOR = "10110011110010100010110011110010";

    public static String generateHash(String text, boolean print) {
        text = doPadding(text, LENGTH);
        String h = INIT_VECTOR;
        if (print)
            System.out.println("Text after padding is " + text);
        for (int i = 0; i < text.length() / LENGTH; i++) {
            String m = text.substring(i * LENGTH, i * LENGTH + 64);
            if (print)
                System.out.println("Text m is " + m);
            String afterFunction = applyFunction(h, m, print);
            if (print)
                System.out.println("after function value is " + afterFunction);
            h = xor(afterFunction, h);
        }
        return h;
    }


    public static String applyFunction(String h, String m, boolean print) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append("-------------------Current stream" + i + "-----------\n");
            String w;
            if (i % 2 == 0) {
                w = m.substring(0, LENGTH / 2);
            } else {
                w = m.substring(LENGTH / 2);
            }
            stringBuilder.append("\nW is " + w);
            String a = and(w, leftShift(h, LENGTH / 4));
            stringBuilder.append("\nleftShift is " + leftShift(h, LENGTH / 4));
            stringBuilder.append("\nA is " + a);
            h = xor(w, a);
            stringBuilder.append("\nFirst H is " + h);
            h = leftShift(h, i);
            stringBuilder.append("\nSecond H is " + h);
            h = xor(h, CONSTANTS[i]);
            stringBuilder.append("\nThird H is " + h);
            stringBuilder.append("\n");
        }
        if (print)
            System.out.println(stringBuilder);
        return h;
    }

    public static String leftShift(String text, int shiftBy) {
        shiftBy = shiftBy % text.length();
        return shiftBy == 0 ? text : text.substring(shiftBy) + text.substring(0, shiftBy);
    }

    public static String doPadding(String text, int len) {
        int rem = text.length() % len;
        return rem == 0 ? text : text + "0".repeat(len - rem);
    }


    public static String and(String one, String two) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = one.toCharArray();
        char[] encrypted = two.toCharArray();
        for (int i = 0; i < one.length(); i++) {
            char current = '0';
            if (chars[i] == '1' && encrypted[i] == '1') {
                current = '1';
            }

            stringBuilder.append(current);
        }
        return stringBuilder.toString();
    }

    public static String xor(String one, String two) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = one.toCharArray();
        char[] encrypted = two.toCharArray();
        for (int i = 0; i < one.length(); i++) {
            char current = '1';
            if (chars[i] == encrypted[i]) {
                current = '0';
            }

            stringBuilder.append(current);
        }
        return stringBuilder.toString();
    }

    public static void collisions() {


        String[] chars = new String[]{"0", "1"};
        Queue<String> queue = new ArrayDeque<>();
        queue.add("1");
        HashMap<String, String> hashMap = new HashMap<>();
        Set<String> set = new HashSet<>();
        int count = 0;
        while (!queue.isEmpty()) {
            String current = queue.remove();
            for (int i = 0; i < chars.length; i++) {
                current = current + chars[i];
                String calcHash = doPadding(current, 64);
                String hash = generateHash(current, false);
                if (hashMap.containsKey(hash) && !set.contains(hash)) {
                    System.out.println("----------------------Entry -------------------");
                    System.out.println(hashMap.get(hash));
                    System.out.println(calcHash);
                    System.out.println(hash);
                    set.add(hash);
                    count++;
                }
                hashMap.put(hash, calcHash);

                queue.add(current);
            }
            if (count > 1) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(generateHash("0000000000000000000000000000000000000000000000000000000000000001", false));
        System.out.println(generateHash("0000000000000000000000000000000000000000000000010000000000000001", false));
        //System.out.println(generateHash("0000000000000000000000000000000000000000000000000000000000000010"));
        System.out.println();
    }
}

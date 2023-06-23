package rsa;

public class DoubleAndAddAlgorithm {


    public static Point applyDoubleAndAddAlgorithm(int k, Point p, int n, int a) {
        StringBuilder stringBuilder = new StringBuilder();
        String kInBinary = Integer.toBinaryString(k);
        addNewLineAtEnd(String.format("K in binary: %s", kInBinary), stringBuilder);
        char[] chars = kInBinary.toCharArray();
        String current = "P";
        Point result = p;
        for (int i = 1; i < chars.length; i++) {

            StringBuilder formula = new StringBuilder();
            formula.append(2);
            formula.append(" . ");
            formula.append("(").append(current).append(")");
            if (chars[i] == '1') {
                formula.append(" + P");
            }
            current = formula.toString();
            stringBuilder.append(chars[i]);
            stringBuilder.append(" :");
            stringBuilder.append(formula);
            stringBuilder.append(" = 2 . ");
            stringBuilder.append(result);
            result = EclipticCurve.addPoints(result, result, n, a, true);
            if (chars[i] == '1') {
                stringBuilder.append(" + ").append(p);
                result = EclipticCurve.addPoints(p, result, n, a, true);
            }

            stringBuilder.append(" = ").append(result);
            addNewLineAtEnd("", stringBuilder);
        }
        System.out.println(stringBuilder);
        return result;

    }

    public static void addNewLineAtEnd(String s, StringBuilder stringBuilder) {
        stringBuilder.append(s);
        stringBuilder.append("\n");
    }

    public static void main(String[] args) {
        applyDoubleAndAddAlgorithm(9, new Point(8, 10), 29, 4);
    }
}

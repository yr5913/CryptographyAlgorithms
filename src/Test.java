import utils.Helpers;

public class Test {

    public static void main(String[] args) {
        String x = "4 B2EF08 D3C975 A 61" +
                "DOB7491AE35C2F86" +
                "14 BDC37EAF 6805 92 6BD81 4A 7950 FE 2 3 C";
        StringBuilder stringBuilder = new StringBuilder();

        x = Helpers.removeSpaces(x);
        char[] chars = x.toCharArray();
        stringBuilder.append("public static int[][] S8 ={\n{");
        for (int i = 0; i < x.length(); i++) {

            if (i % 16 == 0) {

            }
            if (chars[i] < 'A') {
                stringBuilder.append(Integer.valueOf(chars[i] + ""));
            } else {
                stringBuilder.append((10 + chars[i] - 'A'));
            }
            if (i % 16 != 15)
                stringBuilder.append(", ");
            else {
                if (i == 63) {
                    stringBuilder.append("}\n};");
                } else
                    stringBuilder.append("},\n{");
            }
        }
        System.out.println(stringBuilder);
    }
}

package rsa;

import java.math.BigDecimal;

public class ChineseRemainderTheorem {


    public static long applyChineseRemainder(long[] remainders, long[] numbers) {
        long numberN = 1;
        for (int i = 0; i < numbers.length; i++) {
            numberN *= numbers[i];
        }
        long[] inverses = new long[numbers.length];

        long[] Ns = new long[numbers.length];
        long sum = 0;
        for (int i = 0; i < remainders.length; i++) {
            Ns[i] = numberN / numbers[i];
            inverses[i] = Modulo.getInverseUsingExtendedEuclidean(
                    Modulo.extractPositiveMod(Ns[i], numbers[i]), numbers[i], false);


            sum += Modulo.extractPositiveMod(remainders[i] * Ns[i] * inverses[i], numberN);
            sum = Modulo.extractPositiveMod(sum, numberN);
        }

        return sum;
    }

    public static long applyChineseRemainderRSA(long x, long d, long n, long p, long q) {
        long xp = x % p;
        long xq = x % q;
        long dp = d % (p - 1);
        long dq = d % (q - 1);
        long yp = MultiplyAndSquareAlgo.findMod(xp, dp, p, false);
        long yq = MultiplyAndSquareAlgo.findMod(xq, dq, q, false);
        long cp = Modulo.getInverseUsingExtendedEuclidean(q % p, p, false);
        long cq = Modulo.getInverseUsingExtendedEuclidean(p % q, q, false);
        BigDecimal left = new BigDecimal(String.valueOf(q)).multiply(new BigDecimal(String.valueOf(cp))).multiply(new BigDecimal(String.valueOf(yp)));
        BigDecimal right = new BigDecimal(String.valueOf(p)).multiply(new BigDecimal(String.valueOf(cq))).multiply(new BigDecimal(String.valueOf(yq)));
        long y = left.add(right).divideAndRemainder(new BigDecimal(String.valueOf(n)))[1].longValue();
        return y;
    }

    public static void main(String[] args) {
        System.out.println(applyChineseRemainder(new long[]{2, 3, 2}, new long[]{3, 5, 7}));
    }
}

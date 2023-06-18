package rsa;

import java.util.*;

public class Modulo {


    public static GCD applyEucledian(long x, long y, boolean print) {
        long big = x;
        long small = y;
        if (y > x) {
            big = y;
            small = x;
        }
        long last = small;
        List<Long> quotients = new ArrayList<>();
        while (true) {
            long quo = big / small;
            long rem = big % small;
            quotients.add(quo);
            if (print)
                System.out.printf("%d = %d . %d + %d\n", big, quo, small, rem);
            if (rem == 0) {
                break;
            }
            big = small;
            small = rem;
            last = rem;
        }
        quotients.remove(quotients.size() - 1);
        long[] returnArray = new long[quotients.size()];
        for (int i = 0; i < quotients.size(); i++) {
            returnArray[i] = quotients.get(i);
        }
        return new GCD(last, returnArray);

    }


    public static long getInverseUsingExtendedEuclidean(long x, long n, boolean print) {
        GCD gcd = applyEucledian(x, n, print);
        if (gcd.gcd != 1) {
            return -1;
        }
        long t1 = 0;
        long t2 = 1;
        for (int i = 0; i < gcd.quotients.length; i++) {
            long latestT = t1 - gcd.quotients[i] * t2;
            if (print)
                System.out.printf("%d         | %d - %d * %d = %d\n", gcd.quotients[i], t1, gcd.quotients[i], t2, latestT);
            t1 = t2;
            t2 = latestT;
        }
        return extractPositiveMod(t2, n);
    }

    public static long extractPositiveMod(long x, long n) {
        while (true) {
            if (x > -1) {
                break;
            }
            x += n;
        }
        return x % n;
    }


    public static long phiOfNWithTwoPrimeFactors(long x, long y) {
        return (x - 1) * (y - 1);
    }

    public static void main(String[] args) {
        GCD gcd = applyEucledian(12, 67, true);
        System.out.println(getInverseUsingExtendedEuclidean(168, 1239, true));
        System.out.println(gcd.gcd + " " + Arrays.toString(gcd.quotients));
        System.out.println(-5 % 10);
        int z = 7;
        int i = 3;
        while (true) {
            if (MultiplyAndSquareAlgo.findMod(7, i, 99, false) == 10) {
                System.out.println(i);
                break;
            }
            i++;
        }
    }

    public static Map<Long, Integer> factorize(long n) {
        Map<Long, Integer> factors = new HashMap<>();
        for (long i = 2; i <= Math.sqrt(n); i++) {
            int count = 0;
            while (n % i == 0) {
                count++;
                n = n / i;
            }
            if (count != 0) {
                factors.put(i, count);
            }
        }
        return factors;
    }
}

class GCD {
    long gcd;
    long[] quotients;

    public GCD(long gcd, long[] quotients) {
        this.gcd = gcd;
        this.quotients = quotients;
    }
}

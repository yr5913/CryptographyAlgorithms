package rsa;

public class DHKE {

    public static DHKEKeys generatePublicAndCommonKey(long p, long alpha, long xA, long xB, boolean print) {
        if (print) {
            System.out.printf("Xa: %d\n", xA);
            System.out.printf("Xb: %d\n", xB);
            System.out.println("Ya = α ^ Xa mod p");
        }
        long yA = helper(xA, alpha, p, print);
        if (print)
            System.out.println("Yb = α ^ Xb mod p");
        long yB = helper(xB, alpha, p, print);
        if (print)
            System.out.println("K = Yb ^ Xa mod p");
        long commonKey = helper(xA, yB, p, print);
        if (print)
            System.out.println("K = Ya ^ Xb mod p");
        helper(xB, yA, p, print);
        return new DHKEKeys(yA, yB, commonKey);
    }

    private static long helper(long a, long alpha, long p, boolean print) {

        long value = MultiplyAndSquareAlgo.findMod(alpha, a, p, false);
        if (print) {
            System.out.printf("   = %d ^ %d mod %d = %d\n", alpha, a, p, value);
        }
        return value;
    }

    public static void main(String[] args) {
        generatePublicAndCommonKey(467, 2, 3, 5, true);
        System.out.println("-----------Next-----------------");
        generatePublicAndCommonKey(467, 2, 400, 134, true);
        System.out.println("-----------Next-----------------");
        generatePublicAndCommonKey(323, 2, 125, 3, true);

    }
}

class DHKEKeys {
    long Ya;
    long Yb;
    long commonKey;

    public DHKEKeys(long ya, long yb, long commonKey) {
        Ya = ya;
        Yb = yb;
        this.commonKey = commonKey;
    }
}

package rsa;

import utils.Helpers;

public class BreakRSA {

    public static void main2(String[] args) {
        long n = 179176752980291197L;
        long[] primeFactors = useSquareRootTechnique(n);
        long p = primeFactors[0];
        long q = primeFactors[1];
        System.out.println("p is " + p);
        System.out.println("q is " + q);
        RSAImplement rsaImplement = new RSAImplement(p, q);
        long cipher = Long.parseLong("11100001001011111010111000110100111010011101100110001001", 2);
        System.out.println("Cipher in decimal, " + cipher);
        System.out.println("Value of n is " + rsaImplement.getN());
        System.out.println("Value of phiOfN is " + rsaImplement.getPhiOfN());
        long privateKey = rsaImplement.getPrivateKey(73);
        System.out.println("Private key is " + privateKey);
        long text = rsaImplement.encryptOrDecrypt(privateKey, cipher);
        System.out.println("Decrypted text in decimal is "+ text);
        String textInBinary = Long.toBinaryString(text);
        Helpers.printAscii(textInBinary, 7);
        String actualText = Helpers.asciiToText(textInBinary, 7);
        System.out.println("Decrypted text is: " + actualText);
        //long phiOfN = Modulo.phiOfNWithTwoPrimeFactors();
    }

    public static void main(String[] args) {
        long publicKey = 5;
        RSAImplement rsaImplement = new RSAImplement(13, 29);
        long cipher = Long.parseLong("11100001001011111010111000110100111010011101100110001001", 2);
        System.out.println("Cipher in decimal, " + cipher);
        System.out.println("Value of n is " + rsaImplement.getN());
        System.out.println("Value of phiOfN is " + rsaImplement.getPhiOfN());
        long privateKey = rsaImplement.getPrivateKey(publicKey);
        System.out.println("Private key is " + privateKey);
    }

    public static long[] useSquareRootTechnique(long n) {
        long p = (long) Math.sqrt(n);
        while (true) {
            if (n % p == 0) {
                return new long[]{p, n / p};
            }
            p--;
        }
    }
}

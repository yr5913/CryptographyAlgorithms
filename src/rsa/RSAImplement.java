package rsa;

public class RSAImplement {
    private long p;
    private long q;
    private long phiOfN;
    private long n;

    public RSAImplement(long p, long q) {
        this.p = p;
        this.q = q;
        this.n = p * q;
        this.phiOfN = Modulo.phiOfNWithTwoPrimeFactors(p, q);
    }


    public long getPrivateKey(long publicKey) {
        return Modulo.getInverseUsingExtendedEuclidean(publicKey, phiOfN, true);
    }

    public long encryptOrDecrypt(long key, long text) {
        return ChineseRemainderTheorem.applyChineseRemainderRSA(text, key, this.n, p, q);
    }

    public long getP() {
        return p;
    }

    public void setP(long p) {
        this.p = p;
    }

    public long getQ() {
        return q;
    }

    public void setQ(long q) {
        this.q = q;
    }

    public long getPhiOfN() {
        return phiOfN;
    }

    public void setPhiOfN(long phiOfN) {
        this.phiOfN = phiOfN;
    }

    public long getN() {
        return n;
    }

    public void setN(long n) {
        this.n = n;
    }

    public static void main(String[] args) {
        RSAImplement rsaImplement = new RSAImplement(13, 29);
        System.out.println();
        System.out.println(rsaImplement.getPrivateKey(5));
    }
}

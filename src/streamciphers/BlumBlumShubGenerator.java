package streamciphers;

import utils.Helpers;

/**
 * This class generates a key stream using BlumBlumShubGenerator
 */
public class BlumBlumShubGenerator {

    /**
     * This method generates the key stream using BlumBlumShubGenerator
     *
     * @param n           the n in the generator
     * @param seed        the seed value for generator
     * @param generations number of bits in the key stream to be generated
     * @return the key stream generated
     */
    public static String generate(long n, long seed, int generations) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < generations; i++) {
            seed = seed % n;
            long result = (seed * seed) % n;
            stringBuilder.append(result % 2);
            seed = result;
        }
        return stringBuilder.toString();
    }


    /**
     * This method is the start of the program
     *
     * @param args the args
     */
    public static void main(String[] args) {
        // call to generate key stream
        String keyStream = generate(307 * 491, 40, 63);
        // print the generated keystream
        System.out.println(keyStream);
        // decrypt the text using the keystream by applying XOR
        String decrypt = Helpers.xor(keyStream, "101110001001000001000000111110100110010000000011001000111110000");
        // convert the ascii encoding to plain text and print the same
        System.out.println(Helpers.asciiToText(decrypt, 7));
    }
}

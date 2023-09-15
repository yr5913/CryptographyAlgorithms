# Cryptographic Algorithms Project

This Java project implements a wide range of cryptographic algorithms efficiently by employing advanced techniques such
as double-and-add, the Chinese Remainder Theorem, and multiply-and-square. The project covers a diverse set of
cryptographic functionalities, including Caesar ciphers, DES (Data Encryption Standard), hash generation using SHA,
RSA (Rivest–Shamir–Adleman), and stream ciphers such as Blum-Blum Shub and LFSR (Linear Feedback Shift Register).
Additionally, it provides utility functions for common cryptographic operations.

## Project Structure

The project is meticulously organized into distinct packages, each containing specific cryptographic algorithms:

- **ceaser**: Houses various Caesar cipher implementations.
    - `PermutationCipher.java`: Implements a permutation cipher.
    - `ShiftCipher.java`: Implements a shift cipher.
    - `SubstitutionCipher.java`: Implements a substitution cipher.
    - `TranspositionCipher.java`: Implements a transposition cipher.

- **des**: Contains code related to the DES algorithm, utilizing optimization techniques.
    - `Des.java`: Implements the DES algorithm efficiently.
    - `Permutation.java`: Employs techniques like double-and-add for permutation operations in DES.
    - `Sboxes.java`: Contains S-box values used in DES.

- **hash**:
    - `SHA.java`: Efficiently generates hashes using the SHA algorithm.

- **res**: Employs advanced techniques for RSA (Rivest–Shamir–Adleman) encryption and key exchange.
    - `BreakRSA.java`: Utilizes the Chinese Remainder Theorem to break RSA encryption in specific scenarios.
    - `ChineseRemainderTheorem.java`: Implements the Chinese Remainder Theorem efficiently.
    - `DHKE.java`: Implements the Diffie-Hellman Key Exchange (DHKE) algorithm with optimizations.
    - `DHKEEclipctic.java`: Utilizes elliptic curves for efficient DHKE.
    - `DoubleAndAddAlgorithm.java`: Employs the double-and-add algorithm for faster operations.
    - `EclipticCurve.java`: Provides optimized elliptic curve operations.
    - `Modulo.java`: Performs modulo operations on large numbers efficiently.
    - `MultiplyAndSquareAlgo.java`: Utilizes the multiply-and-square algorithm for efficient exponentiation.
    - `RSAHelpers.java`: Contains helper code optimized for RSA.
    - `RSAImplement.java`: Implements the RSA algorithm efficiently.

- **streamciphers**: Implements stream ciphers efficiently.
    - `BlumBlumShubGenerator.java`: Employs the Blum-Blum Shub generator.
    - `LFSR.java`: Implements a Linear Feedback Shift Register efficiently.
    - `RC4OrDecryptAnyText.java`: Provides an optimized implementation of the RC4 cipher.

- **utils**: Contains utility functions optimized for cryptography.
    - `Helpers.java`: Includes common helper methods optimized for operations like XOR, ASCII to text, text to ASCII,
      and more.

## Usage

This project serves as an excellent resource for understanding and utilizing efficient implementations of cryptographic
algorithms in Java. Each package contains specific algorithms optimized for performance.

## How to Run

To run the project or individual algorithms, compile the Java files and execute them using the Java runtime. Ensure that
you have Java installed on your system.

For instance, to run the efficient DES algorithm, you can do the following:

  ```shell
  javac src/des/Des.java
  java src/des/Des
  ```

### License

This project is licensed under the MIT License, allowing you to freely use and modify the code while respecting the
terms of the license.
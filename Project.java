package project;

import java.util.LinkedList;
import java.util.Scanner;

public class Encryption_and_Decryption_LL {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        LinkedList<Character> plaintextList = new LinkedList<>();
        for (char c : plaintext.toCharArray()) {
            plaintextList.add(c);
        }
        int choice=0;
        do {
            System.out.println("Choose an encryption/decryption technique:");
            System.out.println("1. Caesar Cipher");
            System.out.println("2. Additive Cipher");
            System.out.println("3. Multiplicative Cipher");
            System.out.println("4. Transposition Cipher");
            System.out.println("5. Exit");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    processCaesarCipher(plaintextList);
                    break;
                case 2:
                    processAdditiveCipher(plaintextList);
                    break;
                case 3:
                    processMultiplicativeCipher(plaintextList);
                    break;
                case 4:
                    processTranspositionCipher(plaintextList);
                    break;
                case 5:
                	break;
                default:
                    System.out.println("Invalid choice. Exiting.");
            }
        }while(choice!=5);
        
        scanner.close();
    }

    static void processCaesarCipher(LinkedList<Character> plaintextList) {

        LinkedList<Character> caesarCipherList = caesarCipherEncrypt(plaintextList);
        LinkedList<Character> caesarDecryptedList = caesarCipherDecrypt(caesarCipherList);

        System.out.println("\nCaesar Cipher:");
        System.out.println("Encrypted: " + listToString(caesarCipherList));
        System.out.println("Decrypted: " + listToString(caesarDecryptedList) + "\n\n");
    }

    static void processAdditiveCipher(LinkedList<Character> plaintextList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Additive Cipher key: ");
        int additiveKey = scanner.nextInt();

        LinkedList<Character> additiveCipherList = additiveCipherEncrypt(plaintextList, additiveKey);
        LinkedList<Character> additiveDecryptedList = additiveCipherDecrypt(additiveCipherList, additiveKey);

        System.out.println("\nAdditive Cipher:");
        System.out.println("Encrypted: " + listToString(additiveCipherList));
        System.out.println("Decrypted: " + listToString(additiveDecryptedList) + "\n\n");
    }

    static void processMultiplicativeCipher(LinkedList<Character> plaintextList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Multiplicative Cipher key: ");
        int multiplicativeKey = scanner.nextInt();

        LinkedList<Character> multiplicativeCipherList = multiplicativeCipherEncrypt(plaintextList, multiplicativeKey);
        LinkedList<Character> multiplicativeDecryptedList = multiplicativeCipherDecrypt(multiplicativeCipherList, multiplicativeKey);

        System.out.println("\nMultiplicative Cipher:");
        System.out.println("Encrypted: " + listToString(multiplicativeCipherList));
        System.out.println("Decrypted: " + listToString(multiplicativeDecryptedList) + "\n\n");
    }

    static void processTranspositionCipher(LinkedList<Character> plaintextList) {
        LinkedList<Character> transpositionCipherList = transpositionCipherEncrypt(plaintextList);
        LinkedList<Character> transpositionDecryptedList = transpositionCipherDecrypt(transpositionCipherList);

        System.out.println("\nTransposition Cipher:");
        System.out.println("Encrypted: " + listToString(transpositionCipherList));
        System.out.println("Decrypted: " + listToString(transpositionDecryptedList) + "\n\n");
    }

    static LinkedList<Character> caesarCipherEncrypt(LinkedList<Character> plaintextList) {
        LinkedList<Character> encryptedList = new LinkedList<>();
        for (char c : plaintextList) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                encryptedList.add((char) ((c - base + 3) % 26 + base));
            } else {
                encryptedList.add(c);
            }
        }
        return encryptedList;
    }

    static LinkedList<Character> caesarCipherDecrypt(LinkedList<Character> ciphertextList) {
        LinkedList<Character> decryptedList = new LinkedList<>();
        for (char c : ciphertextList) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                decryptedList.add((char) ((c - base - 3 + 26) % 26 + base));
            } else {
                decryptedList.add(c);
            }
        }
        return decryptedList;
    }

    static LinkedList<Character> additiveCipherEncrypt(LinkedList<Character> plaintextList, int key) {
        LinkedList<Character> encryptedList = new LinkedList<>();
        for (char c : plaintextList) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                encryptedList.add((char) ((c - base + key) % 26 + base));
            } else {
                encryptedList.add(c);
            }
        }
        return encryptedList;
    }

    static LinkedList<Character> additiveCipherDecrypt(LinkedList<Character> ciphertextList, int key) {
        LinkedList<Character> decryptedList = new LinkedList<>();
        for (char c : ciphertextList) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                decryptedList.add((char) ((c - base - key + 26) % 26 + base));
            } else {
                decryptedList.add(c);
            }
        }
        return decryptedList;
    }

    static LinkedList<Character> multiplicativeCipherEncrypt(LinkedList<Character> plaintextList, int key) {
        LinkedList<Character> encryptedList = new LinkedList<>();
        for (char c : plaintextList) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                encryptedList.add((char) (((c - base) * key) % 26 + base));
            } else {
                encryptedList.add(c);
            }
        }
        return encryptedList;
    }

    static LinkedList<Character> multiplicativeCipherDecrypt(LinkedList<Character> ciphertextList, int key) {
        int modInverse = 0;
        for (int i = 0; i < 26; i++) {
            if ((key * i) % 26 == 1) {
                modInverse = i;
                break;
            }
        }

        LinkedList<Character> decryptedList = new LinkedList<>();
        for (char c : ciphertextList) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                decryptedList.add((char) (((c - base) * modInverse + 26) % 26 + base));
            } else {
                decryptedList.add(c);
            }
        }
        return decryptedList;
    }

    static LinkedList<Character> transpositionCipherEncrypt(LinkedList<Character> plaintextList) {
        int numRows = 3;
        int numCols = plaintextList.size() / numRows + plaintextList.size() % numRows;

        char[][] grid = new char[numRows][numCols];
        int index = 0;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (index < plaintextList.size()) {
                    grid[i][j] = plaintextList.get(index);
                    index++;
                } else {
                    grid[i][j] = ' ';
                }
            }
        }

        LinkedList<Character> encryptedList = new LinkedList<>();
        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                encryptedList.add(grid[j][i]);
            }
        }

        return encryptedList;
    }

    static LinkedList<Character> transpositionCipherDecrypt(LinkedList<Character> ciphertextList) {
        int numRows = 3;
        int numCols = ciphertextList.size() / numRows + ciphertextList.size() % numRows;

        char[][] grid = new char[numRows][numCols];
        int index = 0;

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                if (index < ciphertextList.size()) {
                    grid[j][i] = ciphertextList.get(index);
                    index++;
                } else {
                    grid[j][i] = ' ';
                }
            }
        }

        LinkedList<Character> decryptedList = new LinkedList<>();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                decryptedList.add(grid[i][j]);
            }
        }

        return decryptedList;
    }

    static String listToString(LinkedList<Character> charList) {
        StringBuilder strBuilder = new StringBuilder();
        for (Character c : charList) {
            strBuilder.append(c);
        }
        return strBuilder.toString();
    }
}

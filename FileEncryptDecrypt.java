import java.util.Scanner;
import java.nio.file.*;
import java.io.*;

public class FileEncryptDecrypt {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose 'e' for encryption or 'd' for decryption:");
        String choice = scanner.nextLine();
        System.out.println("Enter the file name or path:");
        String filePath = scanner.nextLine();

        String text = new String(Files.readAllBytes(Paths.get(filePath)));
        String outputText = "";
        int shift = 3;

        for (char c : text.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                if (choice.equals("e")) {
                    outputText += (char) ((c - base + shift) % 26 + base);
                } else if (choice.equals("d")) {
                    outputText += (char) ((c - base - shift + 26) % 26 + base);
                }
            } else {
                outputText += c;
            }
        }

        String outputFilePath = filePath.replace(".txt", choice.equals("e") ? "_encrypted.txt" : "_decrypted.txt");
        Files.write(Paths.get(outputFilePath), outputText.getBytes());

        System.out.println("Output saved to " + outputFilePath);
        scanner.close();
    }
}
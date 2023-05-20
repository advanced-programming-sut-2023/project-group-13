package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.util.ArrayList;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class SaveAndLoadData {
    private static final String SECRET_KEY_ALGORITHM = "AES";
    private static final String ENCRYPTION_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY_FILENAME = "secret.key";

    public static void SaveToJson(ArrayList<?> data, String fileName) {
        try {
            // Convert data to JSON
            String jsonData = new Gson().toJson(data);

            // Generate or load the secret key
            SecretKey secretKey = getSecretKey();

            // Encrypt the JSON data
            byte[] encryptedData = encryptData(jsonData, secretKey);

            // Calculate the SHA-256 hash of the encrypted data
            String hashedData = hashSHA256(encryptedData);

            // Write the hashed data to the hash file
            String hashFileName = getHashFileName(fileName);
            FileWriter fileWriter = new FileWriter(hashFileName);
            fileWriter.write(hashedData);
            fileWriter.close();

            // Write the encrypted data to the file
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(encryptedData);
            fileOutputStream.close();
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> ArrayList<T> LoadData(String fileName, Type type) throws IOException {
        try {
            // Load the secret key
            SecretKey secretKey = loadSecretKey();

            // Read the encrypted data from the file
            byte[] encryptedBytes = Files.readAllBytes(Paths.get(fileName));

            // Calculate the SHA-256 hash of the encrypted data
            String decryptedHash = hashSHA256(encryptedBytes);

            // Verify the integrity of the data
            if (!verifySHA256(decryptedHash, fileName)) {
                throw new RuntimeException("Data integrity compromised. Hash verification failed.");
            }

            // Decrypt the encrypted data
            String decryptedData = decryptData(encryptedBytes, secretKey);

            // Deserialize the decrypted JSON data
            return new Gson().fromJson(decryptedData, type);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    private static SecretKey getSecretKey() throws NoSuchAlgorithmException, IOException {
        // Check if the secret key file exists
        File secretKeyFile = new File(SECRET_KEY_FILENAME);
        if (secretKeyFile.exists()) {
            // Load the secret key from the file
            byte[] keyBytes = Files.readAllBytes(Paths.get(SECRET_KEY_FILENAME));
            return new SecretKeySpec(keyBytes, SECRET_KEY_ALGORITHM);
        } else {
            // Generate a new secret key
            KeyGenerator keyGenerator = KeyGenerator.getInstance(SECRET_KEY_ALGORITHM);
            SecretKey secretKey = keyGenerator.generateKey();

            // Save the secret key to a file
            FileOutputStream fileOutputStream = new FileOutputStream(SECRET_KEY_FILENAME);
            fileOutputStream.write(secretKey.getEncoded());
            fileOutputStream.close();

            return secretKey;
        }
    }

    private static SecretKey loadSecretKey() throws IOException {
        // Load the secret key from the file
        byte[] keyBytes = Files.readAllBytes(Paths.get(SECRET_KEY_FILENAME));
        return new SecretKeySpec(keyBytes, SECRET_KEY_ALGORITHM);
    }

    private static byte[] encryptData(String data, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data.getBytes());
    }

    private static String decryptData(byte[] encryptedBytes, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    private static String hashSHA256(byte[] data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data);
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean verifySHA256(String hashedData, String fileName) throws IOException {
        // Read the stored hash from the hash file
        String storedHash = new String(Files.readAllBytes(Paths.get(getHashFileName(fileName))));

        // Compare the stored hash with the provided hashed data
        return storedHash.equals(hashedData);
    }

    private static String getHashFileName(String fileName) {
        return fileName + ".hash";
    }
}

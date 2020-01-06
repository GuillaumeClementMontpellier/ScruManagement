package util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class Cryptor {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static byte[] hexToBytes(String hex) {
        byte[] val = new byte[hex.length() / 2];
        for (int i = 0; i < val.length; i++) {
            int index = i * 2;
            int j = Integer.parseInt(hex.substring(index, index + 2), 16);
            val[i] = (byte) j;
        }
        return val;
    }

    private static String generateHash(String password, String salt) throws InvalidKeySpecException {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] byteSalt = hexToBytes(salt);

        PBEKeySpec spec = new PBEKeySpec(chars, byteSalt, iterations, 64 * 8);
        SecretKeyFactory skf = null;
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert skf != null;
        byte[] hash = skf.generateSecret(spec).getEncoded();

        return bytesToHex(hash);
    }

    private static String getSaltRandom() {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] salt = new byte[16];
        assert sr != null;
        sr.nextBytes(salt);
        return bytesToHex(salt);
    }

    public static void main(String[] args) throws InvalidKeySpecException {
        String originalPassword = "password";

        String salt = getSaltRandom();
        System.out.println("Salt : " + salt);
        byte[] bSalt = hexToBytes(salt);
        String reSalt = bytesToHex(bSalt);
        System.out.println("Salt : " + reSalt);

        String generatedPasswordHash = generateHash(originalPassword, salt);
        System.out.println("Hash : " + generatedPasswordHash);
    }
}

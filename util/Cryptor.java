package util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class Cryptor {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Charset cs = StandardCharsets.UTF_16;

        String originalPassword = "password";
        String generatedPasswordHash = generateHash(originalPassword, "Salt is not empty", cs);
        System.out.println(generatedPasswordHash);

    }

    private static String generateHash(String password, String salt, Charset cs) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] byteSalt = cs.encode(salt).array();

        PBEKeySpec spec = new PBEKeySpec(chars, byteSalt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return cs.decode(ByteBuffer.wrap(hash)).toString();
    }

//    private static String getSalt(Charset cs) throws NoSuchAlgorithmException {
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//        byte[] salt = new byte[16];
//        sr.nextBytes(salt);
//        return cs.decode(ByteBuffer.wrap(salt)).toString();
//    }
}

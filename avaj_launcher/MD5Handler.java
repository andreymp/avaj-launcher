package avaj_launcher;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
// import avaj_launcher.SimulationException;

public class MD5Handler {

    private static final MessageDigest md = MessageDigest.getInstance("md5");
    private static final String DIGEST_STRING = "HG58YZ3CR9";
    private static final String CHARSET_UTF_8 = "utf-8";
    private static final String SECRET_KEY_ALGORITHM = "DESede";
    private static final String TRANSFORMATION_PADDING = "DESede/CBC/PKCS5Padding";

    public static String decrypt(String str) throws SimulationException {
        byte[] message = str.getBytes();
        final byte[] digestOfPassword = md.digest(DIGEST_STRING.getBytes(CHARSET_UTF_8));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8; ) {
            keyBytes[k++] = keyBytes[j++];
        }
        final SecretKey key = new SecretKeySpec(keyBytes, SECRET_KEY_ALGORITHM);
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher cipher = Cipher.getInstance(TRANSFORMATION_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        final byte[] plainTextBytes = str.getBytes(CHARSET_UTF_8);
        final byte[] cipherText = cipher.doFinal(plainTextBytes);
        return cipherText;
    }

    public static void main(String[] args) {
        System.out.println(decrypt(args[0]));
    }
}
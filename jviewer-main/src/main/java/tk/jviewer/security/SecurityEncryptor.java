package tk.jviewer.security;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * Provides encrypt mechanism.
 */
public class SecurityEncryptor {

    //TODO: take out to the security file
    private static final char[] PASSWORD = "enfldsgbnlsngdlksdsgm".toCharArray();
    private static final byte[] SALT = {
            (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
            (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
    };

    private static final String ALGORITHM = "PBEWithMD5AndDES";

    public static String encrypt(String property) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
            Cipher pbeCipher = Cipher.getInstance(ALGORITHM);
            pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
            return new BASE64Encoder().encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred during data encrypting. Please refer to JViewer support.", e);
        }
    }
}

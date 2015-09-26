package tk.jviewer.security;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * Serves for data encryption.
 */
public final class SecurityEncryptor {

    private static final String ALGORITHM = SecurityConfigProvider.getInstance().getSecurityProperty("algorithm");
    private static final char[] PASSWORD = SecurityConfigProvider.getInstance().getSecurityProperty("passphrase").toCharArray();
    private static final byte[] SALT = SecurityConfigProvider.getInstance().getSalt();

    private SecurityEncryptor() {
    }

    /**
     * Returns an encrypted string.
     * @param string unencrypted string.
     * @return see description.
     */
    public static String encrypt(String string) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
            Cipher pbeCipher = Cipher.getInstance(ALGORITHM);
            pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
            return new BASE64Encoder().encode(pbeCipher.doFinal(string.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred during data encrypting. Please refer to JViewer support.", e);
        }
    }
}

package tk.jviewer.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Provides access to security properties.
 */
final class SecurityConfigProvider {

    private static final String SALT_PROPERTY = "salt";

    private static Properties securityProperties;
    private static SecurityConfigProvider instance = new SecurityConfigProvider();

    private SecurityConfigProvider() {
        securityProperties = new Properties();
        try {
            securityProperties.load(new FileInputStream(System.getProperty("security.property.file")));
        } catch (IOException e) {
            throw new IllegalStateException("Could not load security configuration", e);
        }
    }

    /**
     * Returns the current instance of {@link SecurityConfigProvider}.
     * @return see description.
     */
    static SecurityConfigProvider getInstance() {
        return instance;
    }

    /**
     * Returns the value of property by specified property name.
     * @param propertyName name of property.
     * @return see description.
     */
    String getSecurityProperty(String propertyName) {
        return securityProperties.getProperty(propertyName);
    }

    /**
     * Returns the current salt for encryption.
     * @return see description.
     */
    byte[] getSalt() {
        String property = getSecurityProperty(SALT_PROPERTY);
        String[] byteValues = property.substring(1, property.length() - 1).split(",");
        byte[] bytes = new byte[byteValues.length];

        for (int i=0, len=bytes.length; i<len; i++) {
            bytes[i] = Byte.parseByte(byteValues[i].trim());
        }
        return bytes;
    }
}

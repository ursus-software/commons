package rs.ursus.commons.utils;

import org.apache.commons.io.Charsets;
import org.bouncycastle.crypto.generators.SCrypt;
import org.bouncycastle.util.encoders.Base64;

/**
 * Created by nighthawk on 4/26/16.
 */
public class SCryptUtil {

    private static final int blockSize = 16;
    private static final int costParam = 2 ^ 14;
    private static final int parallelizationParam = 1;
    private static final int keyLength = 256;

    /**
     * Memory usage: 128 * costParam * blockSize
     * Iterations: costParam * parallelizationParam
     * http://stackoverflow.com/questions/11126315/what-are-optimal-scrypt-work-factors
     */
    public static byte[] generate(byte[] password, byte[] salt) {
        return SCrypt.generate(password, salt, costParam, blockSize, parallelizationParam, keyLength);
    }

    public static String generate(String password, byte[] salt) {
        return Base64.toBase64String(generate(password.getBytes(Charsets.UTF_8), salt));
    }

}

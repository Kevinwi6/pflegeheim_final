package Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The PasswordService class provides methods for password hashing.
 */
public class PasswordService {

    /**
     * Generates a hash for the given password using the SHA-256 algorithm.
     *
     * @param PW the password to hash
     * @return the hashed password as a string
     * @throws NoSuchAlgorithmException if the SHA-256 algorithm is not available
     */
    public String hashPW(String PW) throws NoSuchAlgorithmException {
        MessageDigest digset = MessageDigest.getInstance("SHA-256");
        byte[] hash = digset.digest(PW.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

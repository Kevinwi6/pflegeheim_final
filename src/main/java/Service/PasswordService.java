package Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordService {

    public String hashPW(String PW) throws NoSuchAlgorithmException {
        MessageDigest digset = MessageDigest.getInstance("SHA-256");
        byte[] hash = digset.digest(PW.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
    }

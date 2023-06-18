import Service.LoginService;
import Service.PasswordService;
import model.LoginModel;
import org.hsqldb.rights.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    @Test
    void loginSuccessful() {
        LoginService loginService = new LoginService();
        assertTrue(loginService.login("NhPlusUser", "superSavePW123"));
    }

    @Test
    void loginUnsuccessful_WrongName() {
        LoginService loginService = new LoginService();
        assertFalse(loginService.login("NhPlusUse", "superSavePW123"));
    }

    @Test
    void loginUnsuccessful_WrongPassword() {
        LoginService loginService = new LoginService();
        assertFalse(loginService.login("NhPlusUser", "superSavePW12"));
    }

    @Test
    void passwordIsEncrypted() {
        String testPW = "Password";
        PasswordService passwordService = new PasswordService();
        String hashedPW = passwordService.hashPW(testPW);
        assertNotEquals(testPW, hashedPW);
    }
}

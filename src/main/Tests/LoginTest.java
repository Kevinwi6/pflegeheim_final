import Service.LoginService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}

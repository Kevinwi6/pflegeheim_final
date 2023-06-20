package controller;

import Service.LoginService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * The LoginController class handles the login functionality of the application.
 * It is responsible for handling user input from the username and password fields,
 * performing the login operation, and displaying appropriate error messages.
 */
public class LoginController {

    @FXML
    public TextField usernameField;

    @FXML
    public PasswordField passwordField;

    @FXML
    private BorderPane mainBorderPane;

    /**
     * Handles the login button action.
     * It calls the LoginService to validate the login credentials.
     * If the login is successful, it loads the main window view.
     * If the login fails, it displays an error dialog.
     */
    @FXML
    public void handleLogin() {
        LoginService loginService = new LoginService();
        if (loginService.login(usernameField.getText(), passwordField.getText())) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainWindowView.fxml"));
            try {
                mainBorderPane.setCenter(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            MainWindowController controller = new MainWindowController();
        } else {
            showErrorDialog("Falsche Login-Daten");
        }
    }

    /**
     * Displays an error dialog with the given error message.
     *
     * @param message the error message
     */
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


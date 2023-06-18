package controller;

import Service.LoginService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    private BorderPane mainBorderPane;

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

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

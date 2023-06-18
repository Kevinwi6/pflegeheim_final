import controller.AllPatientController;
import controller.LoginController;
import controller.Main;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CareGiverTest extends Application {

    private Stage primaryStage;
    @Test
    void showAllCaregiver() {
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/LoginView.fxml"));
        BorderPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(pane);
        this.primaryStage.setTitle("NHPlus");
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();

        LoginController loginController = new LoginController();
        loginController.usernameField.setText("NhPlusUser");
        loginController.passwordField.setText("superSavePW123");
        loginController.handleLogin();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        showAllCaregiver();
    }
}

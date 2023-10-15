import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main  extends Application {
    public static void main(String[] args) {
        launch();
        }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/View/ToDo.fxml"));
        Parent root= loader.load();
        Scene scene = new Scene(root);
        Stage stage =  new Stage();
        stage.setTitle("My ToDo application");
        stage.setScene(scene);
        stage.show();
    }
}

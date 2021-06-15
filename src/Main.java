import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/LoginView.fxml"));
        primaryStage.setTitle("Prijava | eKnjiznica");
        primaryStage.setScene(new Scene(root, 920, 650));
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(950);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}

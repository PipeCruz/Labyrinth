package GUI;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    //main
    public static void main(String[] args) {
        launch(args);
    }

    //initialize javafx application
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Files/Labyrinth.fxml"));
        //primaryStage.setOnCloseRequest(Event::consume);
        primaryStage.setTitle("Labyrinth");
        primaryStage.setScene(new Scene(root, 680, 680));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/Pictures/icon.png"));
        primaryStage.setOnCloseRequest(Event::consume);
        primaryStage.show();
    }
}

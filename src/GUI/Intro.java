package GUI;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

class Intro{
    static void intro(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(
                new Image("/Pictures/icon.png"));
        alert.setTitle("Welcome!");
        alert.setHeaderText(null);
        alert.setContentText("Welcome to the Labyrinth!\n" +
                            "Put something informative here i think\n" +
                            "More info!");
        alert.showAndWait();
    }
}

package GUI;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

class Intro{
    //introduction
    static void intro(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(
                new Image("/Pictures/icon.png"));
        alert.setTitle("Welcome!");
        alert.setHeaderText(null);
        alert.setContentText("Welcome to the Terra Riddler's Labyrinth!\n" +
                "This passage will determine how 'Terra' you are.\n" +
                "You must find your way through to reach the exit.\n" +
                "Along the way you will be presented with:\n" +
                "Various questions\n" +
                "Fill in the blanks\n" +
                "Trivia\n" +
                "All CS/Terra related!\n" +
                "Answer correctly or suffer the consequences\n" +
                "Open the menu to exit or for help at any time\n" +
                "At the end you'll be presented with an analysis\n" +
                "Good luck!"
        );
        alert.showAndWait();
    }
}

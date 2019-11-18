package GUI;

import LabyrinthClasses.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Labyrinth {
    private final ArrayList<Question> usedQuestions;
    private final int numQuestions;

    //Instance variables
    private Maze maze;
    private Player player;
    private final ImageView playerIco;
    private boolean blindPunish, brickPunish, flipRC, scram;
    private final Position endPosition;
    //FXML linked instance vars
    @FXML
    private GridPane gridMaze;
    @FXML
    private MenuButton menuButton;
    private int correct;
    private int scramCount;

    //this constructor constructs the gui elements necessary
    public Labyrinth() {
        endPosition = new Position(22, 8);
        ArrQs qs = new ArrQs();
        usedQuestions = qs.getUsedQuestions();
        playerIco = new ImageView(new Image("/Pictures/PlayerIcon.png"));
        correct = 0;
        numQuestions = usedQuestions.size();
        scramCount = 0;

        blindPunish = false;
        brickPunish = false;
        flipRC = false;
        scram = false;

    }

    //shows intro then initializes the instance variables not linked to the FXML document
    @FXML
    private void initialize() {
        Intro.intro();
        maze = new Maze();
        player = new Player();
        firstLoad();
    }

    //updates the map after each time the player moves
    @FXML
    private void updateMap(KeyEvent k) {
        KeyCode kCode = k.getCode();
        if (kCode == KeyCode.W) {
            player.move(Direction.North, maze);
            updateMaze();
        } else if (kCode == KeyCode.A) {
            player.move(Direction.West, maze);
            updateMaze();
        } else if (kCode == KeyCode.S) {
            player.move(Direction.South, maze);
            updateMaze();
        } else if (kCode == KeyCode.D) {
            player.move(Direction.East, maze);
            updateMaze();
        }
    }

    //updates the maze so that the tiles are placed where they should be
    //this method is EXTEMELY inneficient and the tiles should not move this much
    private void updateMaze() {
        Random random = new Random();
        gridMaze.getChildren().remove(playerIco);
        scramCount++;
        if (scram && scramCount % 15 == 0 && !blindPunish) {
            gridMaze.getChildren().remove(0, 265);
            helpUpdate(random);
        }
        for (int i = 0; i < maze.getMaze().length; i++) {
            for (int j = 0; j < maze.getMaze()[i].length; j++) {
                if (player.getPosition().equals(new Position(i, j))) {
                    gridMaze.add(playerIco, j, i);
                    System.out.println("player is at:" + i + "," + j);
                }
                if (player.getPosition().equals(endPosition)) {
                    end();
                }
            }
        }
        gameCheck();
    }

    //helper method for above , this method scrambles the tiles
    private void helpUpdate(Random random) {
        for (int i = 0; i < maze.getMaze().length; i++) {
            for (int j = 0; j < maze.getMaze()[i].length; j++) {
                if (random.nextInt(10) > 5) {
                    gridMaze.add(new ImageView(new Image("/Pictures/brickWall.jpg")), j, i);
                } else {
                    gridMaze.add(new ImageView(new Image("/Pictures/brickPath.jpg")), j, i);
                }
            }
        }
    }

    //when the player finishes the labyrinth they are prompted with a dialog box showing their score on the questions
    private void end() {
        double percent = (100.0 * correct) / numQuestions;
        DecimalFormat df = new DecimalFormat("##.##");
        String d = df.format(percent);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(
                new Image("/Pictures/icon.png"));
        alert.setHeaderText("Congratulations! You finished with a " + d + "% ");
        String options = (percent == 100.0 ? "You really must be a Terra student.\nSad!"
                : (percent > 85.0 ? "You could pass as a Terra student but it would be a little suspicious"
                : (percent < 75.0 ? "You don't go to Terra?\nWhat are you even doing here?"
                : (percent >= 75 && percent <= 85 ? "You're almost a nerd(!)\nBe proud if you want." : null)))
        );
        alert.setContentText(options);
        ButtonType buttonTypeOne = new ButtonType("CLOSE");
        alert.getButtonTypes().setAll(buttonTypeOne);

        Optional<ButtonType> result = alert.showAndWait();
        stage.close();
        System.exit(0);
    }

    // checks current status of player in the map
    private void gameCheck() {
        for (Question q : usedQuestions) {
            if(player.getPosition().equals(q.getPos())) {
                System.out.println("player on question here at " + q.getPos());

                AtomicReference<String> userIn = new AtomicReference<>("");
                TextInputDialog dialog = new TextInputDialog("Enter Answer Here");
                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(
                        new Image("/Pictures/icon.png"));
                dialog.setTitle("Question!");
                dialog.setHeaderText(q.getQuestion());
                dialog.setContentText("Response:");
                dialog.setHeaderText(q.getQuestion());
                dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
                dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);
                dialog.getDialogPane().getScene().getWindow().setOnCloseRequest(Event::consume);
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(userIn::set);
                if(userIn.get().equalsIgnoreCase(q.getAnswer())){
                    System.out.println("CORRECT");
                    valid(true,q,userIn);
                }else{
                    System.out.println("INCORRECT");
                    valid(false, q, userIn);//punish the user
                }
                dialog.close();
                usedQuestions.remove(q);
                return;
            }
        }
    }

    //checks the user's answer to a question, and if incorrect punishes them
    private void valid(boolean b, Question q, AtomicReference<String> userIn) {
        if (b) {
            correct++;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(
                    new Image("/Pictures/icon.png"));
            alert.setHeaderText("Good job");
            alert.setHeaderText("You answered correctly!\n" +
                    "The question: " + q.getQuestion() + "\n" +
                    "Your answer: " + userIn.toString());
            alert.getDialogPane().getScene().getWindow().setOnCloseRequest(Event::consume);
            alert.setContentText("Onwards!\n(existing effects will be removed!)");
            alert.showAndWait();
            if (blindPunish) {
                for (int i = 0; i < maze.getMaze().length; i++) {
                    for (int j = 0; j < maze.getMaze()[0].length; j++) {
                        if (maze.getMaze()[i][j]) {
                            gridMaze.add(new ImageView(new Image("/Pictures/brickWall.jpg")), j, i);
                        } else {
                            gridMaze.add(new ImageView(new Image("/Pictures/brickPath.jpg")), j, i);
                        }
                    }
                }
                blindPunish = false;
            }
            if (brickPunish) {
                for(int i = 0; i< maze.getMaze().length; i++){
                    for(int j = 0; j <maze.getMaze()[0].length; j++){
                        if(maze.getMaze()[i][j]){
                            gridMaze.add(new ImageView(new Image("/Pictures/brickWall.jpg")),j,i);
                        }
                        else {
                            System.out.println("not true");
                            gridMaze.add(new ImageView(new Image("/Pictures/brickPath.jpg")),j,i);
                        }
                    }
                }
                 brickPunish=false;
            }if(flipRC){
                for(int i = 0; i< maze.getMaze().length; i++){
                    for(int j = 0; j <maze.getMaze()[0].length; j++){
                        if(maze.getMaze()[i][j]){
                            gridMaze.add(new ImageView(new Image("/Pictures/brickWall.jpg")),j,i);
                        }
                        else {
                            System.out.println(false);
                            gridMaze.add(new ImageView(new Image("/Pictures/brickPath.jpg")),j,i);
                        }
                    }
                }
                flipRC=false;
            }
            if(scram){
                for(int i = 0; i< maze.getMaze().length; i++){
                    for(int j = 0; j <maze.getMaze()[0].length; j++){
                        if(maze.getMaze()[i][j]){
                            gridMaze.add(new ImageView(new Image("/Pictures/brickWall.jpg")),j,i);
                        } else {
                            System.out.println(false);
                            gridMaze.add(new ImageView(new Image("/Pictures/brickPath.jpg")), j, i);
                        }
                    }
                }
                scram = false;
            }
        }

        if (!b) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(
                    new Image("/Pictures/icon.png"));
            alert.setTitle("INCORRECT");
            alert.setHeaderText("The correct answer was " + q.getAnswer() + "!");
            alert.getDialogPane().getScene().getWindow().setOnCloseRequest(Event::consume);
            alert.setContentText("This will not go without punishment.");
            alert.showAndWait();
            punish();
        }
    }

    //punishes the player when a question is incorrect by sending them to the start, scrambling tiles, or inverting rows and columms
    private void punish() {
        Random random = new Random();
        int x = random.nextInt(5);
        if(x==4){//sets player back to start
            System.out.println("RESET PLAYER POS");
            gridMaze.getChildren().remove(playerIco);
            player.setPosition(new Position(11,11));
            gridMaze.add(playerIco,11,11);
            alert(
                    "Your precious progress!\nAll lost...",
                    "Uh Oh!",
                    "Start Again");

        }else if(x==3){
            System.out.println("MAKE IT WHITE");
            if(!blindPunish){
                gridMaze.getChildren().remove(0,529);
                blindPunish = true;
            }
            alert(
                    "Time to test your memory!\nDon't worry, it won't be too bad.",
                    "Can you remember the path?",
                    "Blind Time!"
            );
        }else if(x==2){
            System.out.println("BRICK IT");
            if(!blindPunish) {
                gridMaze.getChildren().remove(0, 529);
                blindPunish = true;
            }
            for(int i = 0; i < maze.getMaze().length; i++){
                for(int j = 0; j < maze.getMaze()[i].length; j++){
                    gridMaze.add(new ImageView(new Image("/Pictures/brickWall.jpg")),j,i);
                }
            }

            alert(
                    "Bricks,Bricks,Bricks!\nSame path, just more bricks.",
                    "Bricks?",
                    "Bricked!"
            );
        }else if(x==1){
            System.out.println("FLIP IT");
            if(!flipRC){
                if(!blindPunish) {
                    gridMaze.getChildren().remove(0,529);
                    blindPunish=false;
                }
                flipRC=true;
                for(int i = 0; i< maze.getMaze().length; i++) {
                    for (int j = 0; j < maze.getMaze()[0].length; j++) {
                        if (maze.getMaze()[i][j]) {
                            gridMaze.add(new ImageView(new Image("/Pictures/brickWall.jpg")), i, j);
                        } else {
                            gridMaze.add(new ImageView(new Image("/Pictures/brickPath.jpg")), i, j);
                        }
                    }
                }
            }
            alert(
                    "Confusing, isn't it?",
                    "?deneppaH thaW",
                    "R,C-->C,R!"
            );
        }else if(x==0){//scramble it randomly with new boolean scram
            System.out.println("SCRAMBLE");
            if(!scram){
                if(!blindPunish){
                    gridMaze.getChildren().remove(0,529);
                    blindPunish=false;
                }
                scram = true;
                helpUpdate(random);
            }
            alert(
                    "Its like a nice soup of brick.\nActivates every few moves.",
                    "Scccccramble!!",
                    ":blender noises:"
            );
        }
    }

    //used to generate an alert box
    private void alert(String content, String header, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/Pictures/icon.png"));

        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.showAndWait();
    }

    //exit dialog
    //user chose to keep playing or exit
    @FXML
    private void exit() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(
                new Image("/Pictures/icon.png"));
        alert.setHeaderText("Are you sure you would like to leave?");
        alert.setContentText("All progress will be lost");
        ButtonType buttonTypeOne = new ButtonType("Keep Playing");
        ButtonType buttonTypeCancel = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOne) {
            // ... user chose "One"
            System.out.println("kept playing");
        } else {
            System.out.println("closed");
            System.exit(0);
            // ... user chose CANCEL or closed the dialog
        }
    }

    //they open the menu button
    @FXML
    private void openMenu(MouseEvent mouseEvent) {
        if (menuButton.isShowing() && mouseEvent.getEventType().equals(MouseEvent.MOUSE_EXITED_TARGET)) {
            System.out.println("menu opened");
            menuButton.hide();
        } else if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_ENTERED_TARGET)) {
            System.out.println("menu closed");
            menuButton.show();
        }
    }

    //they open the help dialog
    @FXML
    public void help() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(
                new Image("/Pictures/icon.png"));
        alert.setTitle("Help");
        alert.setHeaderText("So you need help?");
        alert.setContentText("Use WASD to move.\n" +
                "Answer questions correctly and reach the exit,\n" +
                "Correctly answered questions remove side effects.\n" +
                "Good luck!");
        alert.showAndWait();
    }

    //initial load
    @FXML
    private void firstLoad() {
        try {
            for (int i = 0; i < maze.getMaze().length; i++) {
                for (int j = 0; j < maze.getMaze()[0].length; j++) {
                    if (maze.getMaze()[i][j]) {
                        gridMaze.add(new ImageView(new Image("/Pictures/brickWall.jpg")), j, i);
                    } else {
                        gridMaze.add(new ImageView(new Image("/Pictures/brickPath.jpg")), j, i);
                    }
                    if (player.getPosition().equals(new Position(i, j))) {
                        gridMaze.add(playerIco, j, i);
                    }
                }
            }
        }catch(IllegalArgumentException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("catch");
        }finally{
            System.out.println("successfully loaded without errors");
        }
    }

}

package GUI;
import LabyrinthClasses.*;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Labyrinth {
    //TODO
    //INTRO
    //SPRITE
    //PUNISHMENTS--almost done
    //Qs and As
    //EXIT



    //@FXML items
    @FXML public GridPane gridMaze;
    @FXML public MenuButton menuButton;

    //Instance variables
    private Maze maze;
    private Player player;
    private ArrayList<Question> usedQuestions;
    private boolean blindPunish,brickPunish,flipRC;
    private int correct;
    private ImageView playerIco;
    private Position endPosition;

    public Labyrinth(){
        endPosition = new Position(22,8);
        ArrQs qs = new ArrQs();
        usedQuestions = qs.getUsedQuestions();
        playerIco = new ImageView(new Image("/Pictures/PlayerIcon.png"));
        correct=0;
        blindPunish = false;
        brickPunish=false;
        flipRC=false;
    }

    @FXML public void initialize(){
        Intro.intro();//maybe change this to be another scene that will then load this scene?
        maze = new Maze();
        player = new Player(maze);
        firstLoad();
    }


    @FXML public void updateMap(KeyEvent k) {
        if(k.getCode()== KeyCode.W){
            player.move(Direction.North,maze);
            updateMaze();
        }else if(k.getCode()==KeyCode.A){
            player.move(Direction.West,maze);
            updateMaze();
        }else if(k.getCode()==KeyCode.S){
            player.move(Direction.South,maze);
            updateMaze();
        }else if(k.getCode()==KeyCode.D){
            player.move(Direction.East,maze);
            updateMaze();
        }
    }

    private void updateMaze() {
        gridMaze.getChildren().remove(playerIco);
        for(int i = 0; i < maze.getMaze().length; i++){
            for(int j = 0; j < maze.getMaze()[i].length; j++){
                if(player.getPosition().equals(new Position(i,j))){
                    gridMaze.add(playerIco,j,i);
//                    System.out.println("player is at:" +i+","+j);
                }if(player.getPosition().equals(endPosition)){
                    end();
                }
            }
        }
        gameCheck();
    }

    private void end() {
        System.out.println("ask for replay here");
        System.out.println("display num correct/5 " + (correct*100/5));
        //ask for replay here
    }

    // FIXME: 11/16/2019
    private void gameCheck() {
//        for (int i = 0; i<usedQuestions.size(); i++) {
////            Question q = usedQuestions.get(i);
////            if(player.getPosition().equals(q.getqPos())) {
////                System.out.println("player on question here at " + q.getqPos());
////
////                AtomicReference<String> userIn = new AtomicReference<>("");
////                TextInputDialog dialog = new TextInputDialog("Enter Answer Here");
////                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
////                stage.getIcons().add(
////                        new Image("/Pictures/icon.png"));
////                dialog.setTitle("Question!");
////                dialog.setHeaderText(q.getQuestion());
////                dialog.setContentText("Response:");
////                dialog.setHeaderText(q.getQuestion());
////                dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
////                dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);
////                dialog.getDialogPane().getScene().getWindow().setOnCloseRequest(Event::consume);
////                Optional<String> result = dialog.showAndWait();
////                result.ifPresent(userIn::set);
////                if(userIn.get().equalsIgnoreCase(q.getAnswer())){
////                    System.out.println("CORRECT");
////                    valid(true,q,userIn);
////                }else{
////                    System.out.println("INCORRECT");
////                    valid(false,q,userIn);//punish the user
////                }
////                dialog.close();
////                usedQuestions.remove(q);
////                return;
////            }
////
        for (Question q : usedQuestions) {
            if(player.getPosition().equals(q.getqPos())) {
                System.out.println("player on question here at " + q.getqPos());

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
                    valid(false,q,userIn);//punish the user
                }
                dialog.close();
                usedQuestions.remove(q);
                return;
            }
        }
    }

     private void valid(boolean b, Question q, AtomicReference<String> userIn) {
        if(b){
            correct++;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(
                    new Image("/Pictures/icon.png"));
            alert.setHeaderText("Good job");
            alert.setHeaderText("Great, answered correctly!\n" +
                    "The question: " + q.getQuestion() + "\n" +
                    "Your answer: " + userIn.toString());
            alert.getDialogPane().getScene().getWindow().setOnCloseRequest(Event::consume);
            alert.setContentText("Onwards!\n(existing effects will be removed!)");
            alert.showAndWait();

            if (blindPunish) {
                for(int i = 0; i< maze.getMaze().length; i++){
                    for(int j = 0; j <maze.getMaze()[0].length; j++){
                        if(maze.getMaze()[i][j]){
                            gridMaze.add(new ImageView(new Image("/Pictures/brickWall.jpg")),j,i);
                        }
                        else {
                            Window.println("not true");
                            gridMaze.add(new ImageView(new Image("/Pictures/brickPath.jpg")),j,i);
                        }
                    }
                }
                blindPunish=false;
            }
            if (brickPunish) {
                for(int i = 0; i< maze.getMaze().length; i++){
                    for(int j = 0; j <maze.getMaze()[0].length; j++){
                        if(maze.getMaze()[i][j]){
                            gridMaze.add(new ImageView(new Image("/Pictures/brickWall.jpg")),j,i);
                        }
                        else {
                            Window.println("not true");
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
                            Window.println("not true");
                            gridMaze.add(new ImageView(new Image("/Pictures/brickPath.jpg")),j,i);
                        }
                    }
                }
                flipRC=false;
            }



//DO NOT PUNISH OK PIPE
        }

        if(!b){
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

    private void punish() {
        Random random = new Random();
        int x = random.nextInt(5);
        if(x==4){//sets player back to start
            System.out.println("RESET PLAYER POS");
            gridMaze.getChildren().remove(playerIco);
            player.setPosition(new Position(11,11));
            gridMaze.add(playerIco,11,11);
            alert(Alert.AlertType.INFORMATION,
                    "Your precious progress!\nAll lost...",
                    "Uh Oh!",
                    "Start Again");

        }else if(x==3){
            System.out.println("MAKE IT WHITE");
            if(!blindPunish){
                gridMaze.getChildren().remove(0,529);
                blindPunish = true;
            }
            alert(Alert.AlertType.INFORMATION,
                    "Time to test your memory!\nDon't worry, it won't be too bad.",
                    "Can you remember the path?",
                    "Blind Time!"
            );
        }else if(x==2){
            System.out.println("BRICK IT");
            if(!brickPunish){
                gridMaze.getChildren().remove(0,529);
                brickPunish=true;
                for(int i = 0; i < maze.getMaze().length; i++){
                    for(int j = 0; j < maze.getMaze()[i].length; j++){
                        gridMaze.add(new ImageView(new Image("/Pictures/brickWall.jpg")),j,i);
                    }
                }
            }
            alert(Alert.AlertType.INFORMATION,
                    "Bricks,Bricks,Bricks!\nSame path, just more bricks.",
                    "Bricks?",
                    "Bricked!"
            );
        }else if(x==1){
            System.out.println("FLIP IT");
            if(!flipRC){
                gridMaze.getChildren().remove(0,529);
                flipRC=true;
                for(int i = 0; i< maze.getMaze().length; i++) {
                    for (int j = 0; j < maze.getMaze()[0].length; j++) {
                        if (maze.getMaze()[i][j]) {
                            gridMaze.add(new ImageView(new Image("/Pictures/brickWall.jpg")), i, j);
                        } else {
                            Window.println("not true");
                            gridMaze.add(new ImageView(new Image("/Pictures/brickPath.jpg")), i, j);
                        }
                    }
                }
            }
            alert(Alert.AlertType.INFORMATION,
                    "Confusing, isn't it?",
                    "Its like you just flipped it?",
                    "R,C-->C,R!"
            );


        }else if(x==0){//scramble it randomly with new boolean scram
            System.out.println("punishment 0");
        }

    }

    private void alert(Alert.AlertType type, String content, String header, String title) {
        Alert alert = new Alert(type);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add( new Image("/Pictures/icon.png"));

        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.showAndWait();
    }

    @FXML public void exit() {
        Window.println("exited");
        System.exit(0);
    }
    @FXML public void openMenu(MouseEvent mouseEvent) {
        if(menuButton.isShowing() && mouseEvent.getEventType().equals(MouseEvent.MOUSE_EXITED_TARGET)){
            Window.println("menu closed");
            menuButton.hide();
        }else if(mouseEvent.getEventType().equals(MouseEvent.MOUSE_ENTERED_TARGET)){
            Window.println("menu opened");
            menuButton.show();
        }
    }
    @FXML public void help() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("So you need help?");
        alert.setContentText("Use WASD to move.\n" +
                "Answer questions correctly and reach the exit,\n" +
                "Correctly answered questions will be removed.\n" +
                "Good luck!");
        alert.showAndWait();
    }
    @FXML private void firstLoad() {
        try{
            for(int i = 0; i< maze.getMaze().length; i++){
                for(int j = 0; j <maze.getMaze()[0].length; j++){
                    if(maze.getMaze()[i][j]){
                        gridMaze.add(new ImageView(new Image("/Pictures/brickWall.jpg")),j,i);
                    }
                    else {
                        Window.println("not true");
                        gridMaze.add(new ImageView(new Image("/Pictures/brickPath.jpg")),j,i);
                    }if(player.getPosition().equals(new Position(i,j))){
                        gridMaze.add(playerIco,j,i);
                    }
                }
            }
        }catch(IllegalArgumentException e){
            System.out.println(e.getStackTrace());
            Window.println("CATCH");
        }finally{
            Window.println("FINALLY");
        }
    }

}

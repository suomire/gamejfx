package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;


public class GameBoardGUI extends Application {
    private GridPane pane = new GridPane();
    private Button[][] child = new Button[16][16];
    private boolean player = true;
    private GameMapCore gameMapCore;
    private int count;
    private Stage stage;

    public Button[][] getChild() {
        return child;
    }

    public void setChild(Button[][] child) {
        this.child = child;
    }

    public final void initMainGUI() {

    }

    public BackgroundImage BackGImages(String str) {
        Image image = new Image(str, 30, 30,
                false, true);
        return new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
    }

    public void initBoard() {
        pane.setGridLinesVisible(false);
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, BorderWidths.FULL)));
        for (int i = 0; i < child.length; i++) {
            pane.getRowConstraints().add(new RowConstraints(30));
            pane.getColumnConstraints().add(new ColumnConstraints(30));
            for (int j = 0; j < child[i].length; j++) {
                Button btn = new Button("");
                final int ii = i;
                final int jj = j;
                child[i][j] = btn;
                btn.setMaxSize(30, 30);
                pane.add(btn, i, j);
                btn.setBackground(new Background(BackGImages("cell.jpg")));
                btn.setAlignment(Pos.BOTTOM_RIGHT);
                btn.visibleProperty().setValue(true);
                btn.setOnMouseClicked(e -> move(btn, ii, jj));
            }
        }
        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 555);
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
        initGame();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("normal.jpg", 300, 225,
                false, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        VBox root = new VBox();
        root.setBackground(new Background(backgroundImage));
        Button start = new Button("Start the game!");
        start.setOnAction(event -> {
            GameBoardGUI gameBoard = new GameBoardGUI();
            gameBoard.initBoard();
        });
        Button close = new Button("Close");
        close.setOnAction(event -> primaryStage.close());
        root.getChildren().addAll(start, close);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 225);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Renju - 連珠");
        start.setAlignment(Pos.CENTER);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void move(Button button, int i, int j) {
        count++;
        if (player) {
            button.setBackground(new Background(BackGImages("whitecell.jpg")));
            boolean continueGame = !(gameMapCore.move(i, j, player));
            if (!continueGame) {
                endTheGame(player);
            }
        } else {
            button.setBackground(new Background(BackGImages("blackcell.jpg")));
            boolean continueGame = !(gameMapCore.move(i, j, player));
            if (!continueGame) {
                endTheGame(player);
            }
        }
        player = !player;
    }

    public void initGame() {
        gameMapCore = new GameMapCore();
    }


    public void endTheGame(boolean player) {
        String playerNum;
        if (player) {
            playerNum = "Player #1 - whites";
        } else {
            playerNum = "Player #2 - blacks";
        }
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setHeaderText("GAME IS OVER");
        Label strWinner = new Label("The winner is " + playerNum);
        GridPane grid = new GridPane();
        grid.add(strWinner, 1, 1);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && (result.get() == buttonTypeOk)) {
            stage.close();
        }


    }
}


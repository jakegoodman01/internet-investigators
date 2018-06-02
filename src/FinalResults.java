import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class FinalResults extends Scene {
    private static int wins;
    private static int losses;

    public FinalResults()
    {
        super(new StackPane());
        StackPane root = new StackPane();
        this.setRoot(root); this.setRoot(root);

        BackgroundMusic.stop();

        StackPane wonLost = new StackPane();
        wonLost.setPadding(new Insets(103, 0, 0, 410));
        VBox wlVBox = new VBox(10);
        Text won = new Text();
        won.setText(Integer.toString(wins));
        won.setFont(new Font(40));
        won.setFill(Color.GREEN);
        Text lost = new Text();
        lost.setText(Integer.toString(losses));
        lost.setFont(new Font(40));
        lost.setFill(Color.RED);
        wlVBox.getChildren().addAll(won, lost);
        wonLost.getChildren().add(wlVBox);

        StackPane mmPane = new StackPane();
        mmPane.setPickOnBounds(false);
        mmPane.setPadding(new Insets(460, 0, 0, 170));
        Photo toMainMenu = new Photo("buttons/mainmenu/ToMainMenu_1.png");
        mmPane.getChildren().add(toMainMenu);

        StackPane overviewPane = new StackPane();
        overviewPane.setPickOnBounds(false);
        overviewPane.setPadding(new Insets(250, 0, 0, 130));
        Photo levelOverview = new Photo("buttons/LevelOverview.png");
        overviewPane.getChildren().add(levelOverview);

        StackPane scorePane = new StackPane();
        scorePane.setPickOnBounds(false);
        scorePane.setPadding(new Insets(295, 0, 0, 0));
        Text score = new Text(Integer.toString(wins*100/6) + " %");
        score.setFill(Color.WHITE);
        score.setFont(new Font(75));
        scorePane.getChildren().add(score);

        toMainMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> this.setRoot(new MainMenu()));

        levelOverview.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            StackPane root2 = new StackPane();
            Scene scene = new Scene(root2);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setHeight(485);
            stage.setWidth(274);
            stage.initStyle(StageStyle.UNDECORATED);
            StackPane exit1 = new StackPane();
            exit1.setPickOnBounds(false);
            exit1.setPadding(new Insets(10, 274-24, 485-24, 10));
            Photo closeButton = new Photo ("buttons/ExitButton.png");
            exit1.getChildren().add(closeButton);

            root2.getChildren().add(new Photo("screens/LevelOverviewWindow.png"));
            root2.getChildren().add(exit1);

            closeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e2 -> stage.close());

            stage.show();
        });

        root.getChildren().add(new Photo("screens/FinalResults.png", 529, 500));
        root.getChildren().add(wonLost);
        root.getChildren().add(mmPane);
        root.getChildren().add(overviewPane);
        root.getChildren().add(scorePane);
    }

    public static void addWin()
    {
        wins++;
    }

    public static void addLoss()
    {
        losses++;
    }

    public static void setWinLosses(int w, int l)
    {
        wins = w;
        losses = l;
    }

}

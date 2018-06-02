import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author Nathan Henry
 * @since 2018-05-22
 * @version 1
 *
 * Hours spent: 1 hour
 */
public class MainMenu extends StackPane {
    public MainMenu() {
        super();
        boolean gameStarted = false;
        this.getChildren().add(new Photo("screens/MainMenu.png", 530, 500));

        VBox buttons = new VBox(20);
        Photo newGame = new Photo("buttons/NewGame.png");
        Photo continueB = new Photo("buttons/Continue.png");
        Photo highScores = new Photo("buttons/HighScores.png");
        Photo quit = new Photo("buttons/Quit.png");

        newGame.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            this.getChildren().clear();
            this.getChildren().add(new Intro());
        });
        continueB.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
        });
        highScores.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
        });
        quit.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> System.exit(0));

        if (gameStarted)
        {
            FinalResults.setWinLosses(0, 0);
            buttons.getChildren().addAll(newGame, continueB, highScores, quit);
        }
        else
        {
            buttons.getChildren().addAll(newGame, highScores, quit);
        }

        buttons.setPadding(new Insets(10, 0, 0, 100));
        buttons.setAlignment(Pos.CENTER);
        this.getChildren().add(buttons);
    }
}

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class Splashscreen extends StackPane {
    public Splashscreen()
    {
        super();
        StackPane background = new StackPane();
        Rectangle r = new Rectangle(500, 550, Color.BLACK); // for a black background
        background.getChildren().add(r);

        StackPane foreground = new StackPane();
        Photo logo = new Photo("screens/ICS ISP Logo Final.png", 400);
        logo.setOpacity(0);
        foreground.setPadding(new Insets(50, 0, 0, 100));
        foreground.getChildren().add(logo);
        FadeTransition fadeLogo = new FadeTransition(Duration.millis(0), logo);
        fadeLogo.setFromValue(0); // starts at 0 opacity
        fadeLogo.setToValue(1.0); // becomes visible
        fadeLogo.setDuration(Duration.millis(3000)); // 3 second animation
        fadeLogo.setDelay(Duration.millis(1000)); // 1 second delay before animation
        fadeLogo.setOnFinished(e -> {
            fadeLogo.setFromValue(1);
            fadeLogo.setToValue(0);
            fadeLogo.play(); // fade out
            fadeLogo.setOnFinished(e2 -> {
                this.getChildren().clear(); // clears the layout
                // enable main menu buttons
            });
        });

        fadeLogo.play();
        this.getChildren().add(background);
        this.getChildren().add(foreground);

    }
}
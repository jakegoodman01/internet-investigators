import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Timeline extends VBox {

    public Timeline(Person person) {
        super();
        Label title = new Label(String.format("%s's Timeline", person.getName()));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(new Font(20));

        BorderPane b = new BorderPane(title);
        this.getChildren().add(b);
    }

    public void addPost(Post post) {
        this.getChildren().add(post.timelineView());
    }
}

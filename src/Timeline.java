import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Timeline extends VBox {

    private Person person;

    public Timeline(Person person) {
        super();
        this.setMaxWidth(380);
        this.person = person;
        Label title = new Label(String.format("%s's Timeline", person.getName()));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        BorderPane b = new BorderPane(title);
        this.getChildren().add(b);
    }

    public void addPost(Post post) {
        HBox box = new HBox(post.timelineView());
        box.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Post p;
            if (post instanceof PicturePost) {
                p = new PicturePost(post.getPostingDate(), ((PicturePost) post).getPhoto());
            } else {
                p = new TextPost(post.getPostingDate(), ((TextPost) post).getText());
            }
            Scene scene = new Scene(p);
            Stage stage = new Stage();
            stage.setTitle(String.format("%s's Post", person.getName()));
            stage.setScene(scene);
            stage.show();
            event.consume();
        });
        this.getChildren().add(box);
    }
}

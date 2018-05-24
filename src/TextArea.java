import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class TextArea extends ScrollPane {

    private VBox vBox;
    private String caption;

    public TextArea(String caption, double photoHeight) {
        super();
        this.caption = caption;

        Rectangle rect = new Rectangle(280, photoHeight * 2, Color.LIGHTGRAY);
        Text text = new Text();
        text.setWrappingWidth(280);
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setText(caption);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        vBox = new VBox();
        vBox.getChildren().add(text);
        vBox.setAlignment(Pos.TOP_LEFT);

        StackPane content = new StackPane();
        content.getChildren().addAll(rect, vBox);

        this.setPrefSize(300, photoHeight);
        this.setContent(content);
    }

    public TextArea(String caption) {
        super();
        this.caption = caption;

        Rectangle rect = new Rectangle(600, 500, Color.LIGHTGRAY);
        Text text = new Text();
        text.setWrappingWidth(600);
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setText(caption);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        vBox = new VBox();
        vBox.getChildren().add(text);
        vBox.setAlignment(Pos.TOP_LEFT);

        StackPane content = new StackPane();
        content.getChildren().addAll(rect, vBox);

        this.setPrefSize(600, 500);
        this.setContent(content);
    }

    public TextArea(String caption, boolean timelineView) {
        super();
        this.caption = caption;

        Rectangle rect = new Rectangle(400, 100, Color.LIGHTGRAY);
        Text text = new Text();
        text.setWrappingWidth(400);
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setText(caption);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        vBox = new VBox();
        vBox.getChildren().add(text);
        vBox.setAlignment(Pos.TOP_LEFT);

        StackPane content = new StackPane();
        content.getChildren().addAll(rect, vBox);

        this.setPrefSize(400, 400);
        this.setContent(content);
    }

    public void addComment(Person person, String comment) {
        Text text = new Text();
        text.setWrappingWidth(280);
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setText(String.format("%s: %s", person.getName(), comment));
        text.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));

        vBox.getChildren().addAll(new Text(), text);
    }

    public void addComments(Person[] people, String[] comments) {
        for (int i = 0; i < people.length; i++) {
            addComment(people[i], comments[i]);
        }
    }

    public String getCaption() {
        return caption;
    }
}
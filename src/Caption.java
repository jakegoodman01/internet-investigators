import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class Caption extends ScrollPane {

    public Caption(String caption, double photoHeight) {
        super();

        Rectangle rect = new Rectangle(400, photoHeight, Color.LIGHTGRAY);
        Text text = new Text();
        text.setWrappingWidth(400);
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setText(caption);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(text);
        vBox.setAlignment(Pos.TOP_LEFT);

        StackPane content = new StackPane();
        content.getChildren().addAll(rect, vBox);

        this.setPrefSize(300, photoHeight);
        this.setContent(content);
    }



}

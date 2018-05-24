import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Date;

public class TextPost extends Post {
    TextArea text;
    private final HBox timelineView;
    private Text numLikes;

    public TextPost (Date postingDate, TextArea text) {
        super(postingDate);
        this.text = text;

        timelineView = new HBox(new TextArea(text.getCaption(), true));

        StackPane stackPane = new StackPane();

        Rectangle dateBox = new Rectangle();
        dateBox.setFill(Color.WHITESMOKE);
        dateBox.setWidth(150);
        dateBox.setHeight(40);

        Text date = new Text(super.formatDate());
        date.setFont(new Font(25));
        date.setWrappingWidth(150);

        Rectangle likeBox = new Rectangle();
        likeBox.setFill(Color.WHITESMOKE);
        likeBox.setWidth(100);
        likeBox.setHeight(40);

        Photo likeImage = new Photo("facebook_like.png", 40);
        likeImage.setAlignment(Pos.BOTTOM_LEFT);

        numLikes = new Text("0");
        numLikes.setFont(new Font(40));
        numLikes.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                ListView<String> list = new ListView<>();
                ObservableList<String> items = FXCollections.observableArrayList (
                        super.getPeopleLiked()
                );
                items.add(0, "People Liked:");
                list.setItems(items);
                likeImage.getChildren().add(list);
            } else {
                likeImage.getChildren().remove(likeImage.getChildren().size() - 1);
            }
        });

        likeImage.setSpacing(15);
        likeImage.getChildren().add(numLikes);

        StackPane.setAlignment(likeBox, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(dateBox, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(date, Pos.BOTTOM_RIGHT);
        stackPane.getChildren().addAll(text, likeBox, likeImage, dateBox, date);

        this.getChildren().add(stackPane);
    }

    public TextArea getText() {
        return text;
    }

    @Override
    public HBox timelineView() {
        return timelineView;
    }

    @Override
    public void addComment(Person person, String comment) {
        text.addComment(person, comment);
    }

    @Override
    public void addComments(Person[] people, String[] comments) {
        text.addComments(people, comments);
    }
}

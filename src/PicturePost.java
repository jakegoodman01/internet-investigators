import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class PicturePost extends Post{
    Photo photo;
    Text numLikes;

    public PicturePost (int year, int month, int day, Photo photo) {
        super(year, month, day);

        StackPane stackPane = new StackPane();
        this.photo = photo;

        Rectangle likeBox = new Rectangle();
        likeBox.setFill(Color.WHITESMOKE);
        likeBox.setX(100);
        likeBox.setY(150);
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
        stackPane.getChildren().addAll(photo, likeBox, likeImage);

        this.getChildren().add(stackPane);
    }

    @Override
    public void addComment(Person person, String comment) {
        photo.addComment(person, comment);
    }

    @Override
    public void addComments(Person[] people, String[] comments) {
        photo.addComments(people, comments);
    }

    @Override
    public void addLike(Person person) {
        super.addLike(person);
        numLikes.setText(Integer.toString(super.getNumLikes()));
    }
}

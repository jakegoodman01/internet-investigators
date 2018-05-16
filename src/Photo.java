import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Photo extends HBox {
    private ImageView imageView;
    private Image image;
    private Caption caption;

    public Photo(String link) {
        super();
        image = new Image(link);
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        this.getChildren().addAll(imageView);
    }

    public Photo(String link, int height) {
        super();
        image = new Image(link);
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(height);
        this.getChildren().addAll(imageView);
    }

    public Photo(String link, int height, String caption) {
        super();
        image = new Image(link);
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(height);
        this.caption = new Caption(caption, imageView.getFitHeight());
        this.getChildren().addAll(imageView, this.caption);
    }

    public Caption getCaption() {
        return caption;
    }
}

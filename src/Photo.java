import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Photo extends ImageView {
    private Image image;
    private String caption;

    public Photo(String link) {
        super();
        image = new Image(link);
        super.setImage(image);
        super.setPreserveRatio(true);
    }

    public Photo(String link, int height) {
        super();
        image = new Image(link);
        super.setImage(image);
        super.setPreserveRatio(true);
        super.setFitHeight(height);
    }

    public Photo(String link, int height, String caption) {
        super();
        image = new Image(link);
        super.setImage(image);
        super.setPreserveRatio(true);
        super.setFitHeight(height);
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
}

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Photo extends HBox {
    private ImageView imageView;
    private Image image;
    private TextArea textArea;
    private String link;

    public Photo(String link) {
        super();
        this.link = link;
        image = new Image(link);
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        this.getChildren().addAll(imageView);
    }

    public Photo(String link, int height) {
        super();
        this.link = link;
        image = new Image(link);
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(height);
        this.getChildren().addAll(imageView);
    }

    public Photo(String link, int width, boolean setWidth) {
        super();
        this.link = link;
        image = new Image(link);
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(width);
        this.getChildren().addAll(imageView);
    }

    public Photo(String link, int height, String caption) {
        super();
        this.link = link;
        image = new Image(link);
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(height);
        this.textArea = new TextArea(caption, imageView.getFitHeight());
        this.getChildren().addAll(imageView, this.textArea);
    }

    public String getLink() {
        return link;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void addComment(Person person, String comment) {
        textArea.addComment(person, comment);
    }

    public void addComments(Person[] people, String[] comments) {
        textArea.addComments(people, comments);
    }
}

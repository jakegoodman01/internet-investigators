import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Photo extends HBox {
    private ImageView imageView;
    private Image image;
    private TextArea textArea;

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
        this.textArea = new TextArea(caption, imageView.getFitHeight());
        this.getChildren().addAll(imageView, this.textArea);
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

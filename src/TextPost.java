import javafx.scene.layout.HBox;

public class TextPost extends Post {
    TextArea text;

    public TextPost (int year, int month, int date, TextArea text) {
        super(year, month, date);
        this.text = text;
    }

    @Override
    public HBox timelineView() {
        return null;
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

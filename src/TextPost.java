import javafx.scene.layout.HBox;

import java.util.Date;

public class TextPost extends Post {
    TextArea text;

    public TextPost (Date postingDate, TextArea text) {
        super(postingDate);
        this.text = text;
    }

    public TextArea getText() {
        return text;
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

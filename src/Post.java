import javafx.scene.layout.HBox;

import java.util.Date;
import java.util.ArrayList;


public abstract class Post extends HBox {
    private Date postingDate;
    private int numLikes;
    private ArrayList<Person> peopleLiked;

    public Post (int year, int month, int date)
    {
        super();
        postingDate = new Date(year, month, date);
        numLikes = 0;
        peopleLiked = new ArrayList<Person>();
    }

    public abstract void addComment(Person person, String comment);

    public abstract void addComments(Person[] people, String[] comments);

    public void addLike(Person person)
    {
        peopleLiked.add(person);
        numLikes++;
    }

    public void addLikes(Person[] people)
    {
        for (Person p : people)
        {
            numLikes++;
            peopleLiked.add(p);
        }
    }
}

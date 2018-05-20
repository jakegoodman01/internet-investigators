import javafx.scene.layout.HBox;

import java.util.Date;
import java.util.ArrayList;


public abstract class Post extends HBox {
    private Date postingDate;
    private int numLikes;
    private ArrayList<Person> peopleLiked;

    public Post (int year, int month, int day)
    {
        super();
        postingDate = new Date(year, month, day);
        numLikes = 0;
        peopleLiked = new ArrayList<Person>();
    }

    public abstract HBox timelineView();

    public abstract void addComment(Person person, String comment);

    public abstract void addComments(Person[] people, String[] comments);

    public void addLike(Person person)
    {
        peopleLiked.add(person);
        numLikes++;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void addLikes(Person[] people)
    {
        for (Person p : people)
        {
            numLikes++;
            peopleLiked.add(p);
        }
    }

    public int getNumLikes() {
        return numLikes;
    }

    // Returns a list of the names of the people who liked this post
    public ArrayList<String> getPeopleLiked() {
        ArrayList<String> people = new ArrayList<>();
        for (Person p : peopleLiked) {
            people.add(p.getName());
        }
        return people;
    }
}

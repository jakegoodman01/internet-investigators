import javafx.scene.layout.HBox;

import java.util.Date;
import java.util.ArrayList;


public abstract class Post extends HBox {
    private Date postingDate;
    private int numLikes;
    private ArrayList<Person> peopleLiked;

    public Post(Date postingDate) {
        super();
        this.postingDate = postingDate;
        numLikes = 0;
        peopleLiked = new ArrayList<>();
    }

    public String formatDate() {
        String dateAsString = postingDate.toString();
        int firstSpace = dateAsString.indexOf(' ');
        int secondSpace = dateAsString.indexOf(' ', firstSpace + 1);
        int thirdSpace = dateAsString.indexOf(' ', secondSpace + 1);
        return dateAsString.substring(0, thirdSpace);
    }

    public abstract HBox timelineView();

    public abstract void addComment(Person person, String comment);

    public abstract void addComments(Person[] people, String[] comments);

    public void addLike(Person person) {
        peopleLiked.add(person);
        numLikes++;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void addLikes(Person[] people) {
        for (Person p : people) {
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

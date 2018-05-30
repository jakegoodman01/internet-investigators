import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {
    private String name;
    private String bio;
    private Photo profilePic;
    private List<Photo> photos;
    private Timeline timeline;
    private List<Person> friends;
    private Map<String, String> hints;

    /**
     * Given variables are assigned to their corresponding fields
     * photos is initialized as an empty ArrayList
     * timeline is initialized as a blank Timeline
     * feinds is initialized as a blank ArrayList
     * @param name The name of the person
     * @param bio The person's bio, to appear in their profile page
     * @param profilePic The persons's profile picture, to appear in their profile page
     */
    public Person(String name, String bio, Photo profilePic) {
        this.name = name;
        this.bio = bio;
        this.profilePic = new Photo(profilePic.getLink(), 100, true);
        this.photos = new ArrayList<>();
        this.timeline = new Timeline(this);
        this.friends = new ArrayList<>();

        this.hints = new HashMap<>();
    }

    /**
     * Getter for name
     * @return name
     */
    public String getName() { return name; }

    /**
     * Getter for bio
     * @return bio
     */
    public String getBio() { return bio; }

    /**
     * Getter for profilePic
     * @return profilePic
     */
    public Photo getRawProfilePic() { return profilePic; }

    /**
     * Getter for profilePic, as a copied object
     * This is to avoid an error caused by the same object being used in two different stages
     * @return A new Photo that is identical to profilePic but as a different object reference
     */
    public Photo getNewProfilePic() { return new Photo(profilePic.getLink()); }

    /**
     * Getter for photos
     * @return photos
     */
    public List<Photo> getPhotos() { return photos; }

    /**
     * Getter for timeline
     * @return timeline
     */
    public Timeline getTimeline() { return timeline; }

    /**
     * Adds a given Photo to photos
     * @param photo Photo to be added to list photos
     */
    public void addPhoto(Photo photo) { photos.add(photo); }

    /**
     * Adds a given Person to friends
     * @param p Person to be added to list friends
     */
    public void addFriend(Person p) { friends.add(p); }

    /**
     * Getter for friends
     * @return friends
     */
    public List<Person> getFriends() { return friends; }

    public Map<String, String> getHints() { return hints; }
}

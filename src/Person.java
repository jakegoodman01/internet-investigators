import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String bio;
    private Photo profilePic;
    private List<Photo> photos;
    private Timeline timeline;

    public Person(String name, String bio, Photo profilePic) {
        this.name = name;
        this.bio = bio;
        this.profilePic = new Photo(profilePic.getLink(), 100, true);
        this.photos = new ArrayList<>();
        this.timeline = new Timeline(this);
    }

    public String getName() { return name; }

    public String getBio() {
        return bio;
    }

    public Photo getProfilePic() {
        return profilePic;
    }

    public List<Photo> getPhotos() { return photos; }

    public Timeline getTimeline() { return timeline; }

    public void addPhoto(Photo photo) { photos.add(photo); }
}

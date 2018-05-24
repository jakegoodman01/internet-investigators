import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String bio;
    private Photo profilePic;
    private List<Photo> photos;
    private Timeline timeline;
    private List<Person> friends;

    public Person(String name, String bio, Photo profilePic) {
        this.name = name;
        this.bio = bio;
        this.profilePic = new Photo(profilePic.getLink(), 100, true);
        this.photos = new ArrayList<>();
        this.timeline = new Timeline(this);
        this.friends = new ArrayList<>();
    }

    public String getName() { return name; }

    public String getBio() {
        return bio;
    }

    public Photo getRawProfilePic() { return profilePic; }

    public Photo getNewProfilePic() { return new Photo(profilePic.getLink()); }

    public List<Photo> getPhotos() { return photos; }

    public Timeline getTimeline() { return timeline; }

    public void addPhoto(Photo photo) { photos.add(photo); }

    public void addFriend(Person p) { friends.add(p); }

    public List<Person> getFriends() { return friends; }
}

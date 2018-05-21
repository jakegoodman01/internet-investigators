import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String bio;
    private Photo profilePic;
    private List<Photo> photos;

    public Person(String name, String bio, Photo profilePic) {
        this.name = name;
        this.bio = bio;
        this.profilePic = profilePic;
        this.photos = new ArrayList<>();
    }

    public String getName() { return name; }

    public String getBio() {
        return bio;
    }

    public Photo getProfilePic() {
        return profilePic;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public String getFirstName() { return name.substring(0, name.indexOf(' ')); }

    public void addPhoto(Photo photo) { photos.add(photo); }
}

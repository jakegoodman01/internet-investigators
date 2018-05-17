public class Person {
    private String name;
    private String bio;
    private Photo profilePic;
    private Photo[] photos;

    public Person(String name, String bio, Photo profilePic) {
        this.name = name;
        this.bio = bio;
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public Photo getProfilePic() {
        return profilePic;
    }

    public Photo[] getPhotos() {
        return photos;
    }
}

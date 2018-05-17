public class PicturePost extends Post{
    Photo photo;

    public PicturePost (int year, int month, int date, Photo photo) {
        super(year, month, date);
        this.photo = photo;
        this.getChildren().add(photo);
    }

    @Override
    public void addComment(Person person, String comment) {
        photo.addComment(person, comment);
    }

    @Override
    public void addComments(Person[] people, String[] comments) {
        photo.addComments(people, comments);
    }
}

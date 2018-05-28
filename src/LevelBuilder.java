import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class LevelBuilder {

    private List<Person> persons = new ArrayList<>();

    /**
     * Reads from a text file, which describes each character from a level
     * This class creates the appropriate objects to model those people described in our program
     * This files that class reads from are written in a specific format so that this class may interpret them
     * The package organization system is very specific to the functionality of this program
     * Each profile level is organized in this manner:
     * levels -> all level information
     *   levelX -> X represents the level number, ie level1 or level1
     *     characterName -> the name of the character, in all lowercase letters
     *       photos -> this contains all of the photos the character will have in the photos section of their page,
     *                 the name of the photos in this package are irrelevant
     *       timeline -> this contains all of the photos in PicturePosts that appear in the character's timeline.
     *                   The link of the photos in this package are put in the level description file and specifically
     *                   associated with a caption.
     *       profile.jpg -> this is the character's profile picture
     * @param file link to a level description file
     */
    public LevelBuilder(String file) {
        // Opens the file as a reader
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line; // current line of the file
            String name = ""; // name of the character that is currently being created
            String bio = ""; // bio of the character that is currently being created
            String currLevel = file.substring(23, 29); // the level that is being created, e.g. level2 or level3
            boolean readBio = false; // indicates weather the bio is currently being read
            boolean picturePost = false; // indicates weather a PicturePost is currently being created
            String photoLink = null; // link to a photo
            Post post = null; // the most recent created Post
            while ((line = br.readLine()) != null) {
                switch (LevelBuilder.numOfTabs(line)) {
                    case 0:
                        if (line.equals("")) continue;
                        name = line.substring(0, line.indexOf(','));
                        break;
                    case 1:
                        readBio = line.equals("    Bio:");
                        bio = "";
                        break;
                    case 2:
                        if (readBio) {
                            bio += line.substring(8);
                        } else {
                            picturePost = line.equals("        PicturePost:");
                        }
                        break;
                    case 3:
                        // firstNameLower is the character name in all lower case letters
                        String firstNameLower = name.toLowerCase();
                        // if a PicturePost is being created
                        if (picturePost) {
                            // if the link has not been read, assign the given link to photoLink
                            if (photoLink == null) {
                                photoLink = String.format(
                                        "levels/%s/%s/timeline/%s", currLevel,
                                        firstNameLower, line.substring(12)
                                );
                            // if the link has been read, create a new PicturePost with the current line as the caption
                            } else {
                                post = new PicturePost(
                                        new Date(),
                                        new Photo(photoLink, 300, line.substring(12))
                                );
                                photoLink = null;
                            }
                        // if we are not reading a PicturePost, we must be reading a TextPost
                        } else {
                            // create a new TextPost with the current line as the caption
                            post = new TextPost(
                                    new Date(),
                                    new TextArea(line.substring(12))
                            );
                        }
                        break;
                    default:
                        if (readBio) {
                            firstNameLower = name.toLowerCase();
                            // creates a person with their name, bio and profile picture
                            persons.add(new Person(
                                    name,
                                    bio,
                                    new Photo(String.format("levels/%s/%s/profile.jpg",
                                            currLevel, firstNameLower
                                    ))
                            ));
                            // loops through the images in the character's photos package and adds each one to the
                            // character's photos
                            File f = new File(String.format("src/levels/%s/%s/photos",
                                    currLevel, firstNameLower));
                            File[] files = f.listFiles();
                            if (files == null) {
                                throw new FileNotFoundException(
                                        String.format("%s directory not found!", firstNameLower)
                                );
                            } else {
                                for (File photo: files) {
                                    persons.get(persons.size() - 1).addPhoto(new Photo(
                                            photo.toString().substring(4),
                                            200));
                                }
                            }
                        } else {
                            // adds the most recent post to the character's timeline
                            persons.get(persons.size() - 1).getTimeline().addPost(post);
                        }
                }
            }
        } catch (IOException ioe) {
            System.out.printf("Error reading from file: %s%n", file);
        }

        // Adds all friends to all people, so everybody is friends with everybody
        for (Person person : persons) {
            for (Person person1: persons) {
                if (!person.equals(person1)) person.addFriend(person1);
            }
        }
    }

    /**
     * Finds the number of tabs in a line
     * @param line line of the file
     * @return the number of tabs at the start of the line, or the number of spaces modulus four
     */
    public static int numOfTabs(String line) {
        int spaces = 0;
        for (char c : line.toCharArray()) {
            if (c == ' ') {
                spaces++;
            } else {
                if (spaces % 4 == 0)
                    return spaces / 4;
                else
                    throw new IllegalArgumentException(String.format(
                            "Invalid file formatting on this line: %s", line
                    ));
            }
        }
        // if the method reaches this point, line is a blank line
        return -1;
    }

    /**
     * Getter for persons of this file
     * @return persons
     */
    public List<Person> getPersons() {
        return persons;
    }
}

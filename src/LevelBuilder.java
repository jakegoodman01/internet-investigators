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

    // Reads from a level description file and makes all of the necessary objects and such
    public LevelBuilder(String file) {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String name = "";
            String bio = "";
            String currLevel = file.substring(23, 29);
            boolean readBio = false;
            boolean picturePost = false;
            String photoLink = null;
            Post post = null;
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
                        String firstNameLower = name.substring(0, 1).toLowerCase() + name.substring(1);
                        if (picturePost) {
                            if (photoLink == null) {
                                photoLink = String.format(
                                        "levels/%s/%s/timeline/%s", currLevel,
                                        firstNameLower, line.substring(12)
                                );
                            } else {
                                post = new PicturePost(
                                        new Date(),
                                        new Photo(photoLink, 300, line.substring(12))
                                );
                                photoLink = null;
                            }
                        } else {
                            post = new TextPost(
                                    new Date(),
                                    new TextArea(line.substring(12))
                            );
                        }
                        break;
                    default:
                        if (readBio) {
                            firstNameLower = name.substring(0, 1).toLowerCase() + name.substring(1);
                            persons.add(new Person(
                                    name,
                                    bio,
                                    new Photo(String.format("levels/%s/%s/profile.jpg",
                                            currLevel, firstNameLower
                                    ))
                            ));
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
                            persons.get(persons.size() - 1).getTimeline().addPost(post);
                        }
                }
            }
        } catch (IOException ioe) {
            System.out.printf("Error reading from file: %s%n", file);
        }

        for (Person person : persons) {
            for (Person person1: persons) {
                if (!person.equals(person1)) person.addFriend(person1);
            }
        }
    }

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

    public List<Person> getPersons() {
        return persons;
    }
}

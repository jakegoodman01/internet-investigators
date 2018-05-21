import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class LevelBuilder {

    private List<Person> persons = new ArrayList<>();

    // Reads from a level description file and makes all of the necessary objects and such
    public LevelBuilder(String file) {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String name = "";
            String bio = "";
            boolean readBio = false;
            while ((line = br.readLine()) != null) {
                switch (LevelBuilder.numOfTabs(line)) {
                    case 0:
                        name = line.substring(0, line.indexOf(','));
                        break;
                    case 1:
                        readBio = line.equals("    Bio:");
                        break;
                    case 2:
                        if (readBio) {
                            bio += line.substring(8);
                        }
                        break;
                    default:
                        if (readBio) {
                            String firstNameLower = name.substring(0, 1).toLowerCase() + name.substring(1);
                            persons.add(new Person(
                                    name,
                                    bio,
                                    new Photo(String.format("%s/profile.jpg",
                                            firstNameLower
                                    ))
                            ));
                            File f = new File(String.format("src/%s/photos", firstNameLower));
                            File[] files = f.listFiles();
                            if (files == null) {
                                throw new FileNotFoundException(
                                        String.format("%s directory not found!", firstNameLower)
                                );
                            } else {
                                for (File photo: files) {
                                    persons.get(persons.size() - 1).addPhoto(new Photo(photo.toString().substring(4)));
                                }
                            }
                        }
                }
            }
        } catch (IOException ioe) {
            System.out.printf("Error reading from file: %s%n", file);
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

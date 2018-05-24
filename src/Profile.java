import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Profile extends GridPane {

    public Profile(Person person) {
        super();
        this.setPadding(new Insets(1, 1, 20, 1));
        this.setVgap(5);
        this.setHgap(5);

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0 && i == 0) {   // report button
                    black(45 * 3 - 20, 43, j, i, 3, 1, new Insets(5, 0, 5, 25));
                    j += 2;
                } else if (j == 4 && i == 0) {
                    // "To Pinboard"
                    black(45 * 2 + 15, 43, j, i, 2, 1);
                    j++;
                } else if (j == 6 && i == 0) {
                    // Help
                    black(40, 40, j, i, 1, 1, new Insets(5, 3, 5, 0));
                } else if (j == 7 && i == 0) {
                    // Sound
                    black(40, 40, j, i, 1, 1, new Insets(5, 3, 5, 0));
                } else if (j == 8 && i == 0) {
                    // Home Menu
                    black(40, 40, j, i, 1, 1, new Insets(5, 5, 5, 0));
                } else if (j == 0 && i == 1) {
                    // Profile Picture
                    Photo profilePic = person.getRawProfilePic();
                    profilePic.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        Scene scene = new Scene(person.getNewProfilePic());
                        Stage stage = new Stage();
                        stage.setTitle(String.format("%s's Profile Picture", person.getName()));
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                        event.consume();
                    });
                    this.add(profilePic, j, i, 3, 2);
                    GridPane.setMargin(profilePic, new Insets(0, 0, 0, 25));
                    j += 2;
                } else if (j == 3 && i == 1) {
                    // Name
                    Text name = new Text(person.getName());
                    name.setWrappingWidth(45 * 2);
                    name.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
                    this.add(name, j, i, 2, 1);
                    GridPane.setMargin(name, new Insets(15, 40, 0, 0));
                    j++;
                } else if (j == 0 && i == 2) {
                    // not a layout
                    j += 2;
                } else if (j == 3 && i == 2) {
                    // bio
                    Text text = new Text(person.getBio().substring(0, 50) + "...");
                    text.setFont(new Font(15));
                    VBox bio = new VBox(text);
                    bio.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        Text t = new Text(person.getBio());
                        t.setWrappingWidth(360);
                        t.setFont(new Font(20));
                        VBox root = new VBox(t);
                        root.setPadding(new Insets(20, 20, 20, 20));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setMaxWidth(400);
                        stage.setTitle("Bio");
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                    });
                    this.add(bio, j, i, 7, 1);
                    GridPane.setMargin(bio, new Insets(0, 40, 0, 0));
                    j += 7;
                } else if (j == 0 && i == 4) {
                    // pictures and friends section with dropdown menus
                    Button photos = new Button("Photos");
                    photos.setMinHeight(43 * 5 / 2);
                    photos.setMinWidth(45 * 3 - 20);

                    photos.setOnAction(e -> {
                        GridPane root = new GridPane();
                        root.setHgap(20);
                        root.setVgap(20);

                        int photoIndex = 0;
                        for (int k = 0; k < 3; k++) {
                            for (int m = 0; m < 3; m++) {
                                if (photoIndex < person.getPhotos().size()) {
                                    root.add(person.getPhotos().get(photoIndex), k, m);
                                    photoIndex++;
                                } else {
                                    break;
                                }
                            }
                        }
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setTitle("Photos");
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                    });

                    this.add(photos, j, i, 3, 5);
                    GridPane.setMargin(photos, new Insets(10, 10, 10, 10));

                    Button friends = new Button("Friends");
                    friends.setMinHeight(43 * 5 / 2);
                    friends.setMinWidth(45 * 3 - 20);

                    friends.setOnAction(e -> {
                        ListView<Person> list = new ListView<>();
                        ObservableList<Person> items = FXCollections.observableArrayList(
                                person.getFriends()
                        );
                        list.setItems(items);
                        Scene scene = new Scene(list);
                        Stage stage = new Stage();
                        stage.setTitle("Friends");
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();

                    });

                    this.add(friends, j, i + 6, 3, 5);
                    GridPane.setMargin(friends, new Insets(10, 10, 10, 10));
                    j += 10;
                    i += 10;
                } else if (j == 3 && i == 3) {
                    // timeline
                    this.add(person.getTimeline(), j, i, 7, 8);
                    GridPane.setMargin(person.getTimeline(), new Insets(20, 40, 70, 0));
                    j += 7;
                } else {
                    // not a layout
                    white(45, 43, j, i, 1, 1);
                }
            }
        }
    }

    private void white(int width, int height, int colI, int rowI, int colSpan, int rowSpan) {
        this.add(new Rectangle(width, height, Color.WHITESMOKE), colI, rowI, colSpan, rowSpan);
    }

    private void black(int width, int height, int colI, int rowI, int colSpan, int rowSpan) {
        this.add(new Rectangle(width, height, Paint.valueOf("#000000")), colI, rowI, colSpan, rowSpan);
    }

    private void black(int width, int height, int colI, int rowI, int colSpan, int rowSpan, Insets insets) {
        Rectangle r = new Rectangle(width, height, Paint.valueOf("#000000"));
        this.add(r, colI, rowI, colSpan, rowSpan);
        GridPane.setMargin(r, insets);
    }
}

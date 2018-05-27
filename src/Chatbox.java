import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chatbox extends VBox {

    private HBox submitSection;
    private ScrollPane chatbox;
    private VBox chatContent;
    private Map<String, String> responder;

    public Chatbox(String file) {
        super();
        initSubmitSection(file);
        initChatbox();
        this.getChildren().addAll(chatbox, submitSection);
    }

    private void initSubmitSection(String file) {
        responder = new HashMap<>();
        submitSection = new HBox(10);
        submitSection.setMinHeight(75);
        // Person / list of questions to ask
        Map<String, List<String >> questions = new HashMap<>();
        String line;
        String name = "";
        String currLevel = file.substring(23, 29);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                switch (Chatbox.numOfTabs(line)) {
                    case 0:
                        if (line.equals("")) continue;
                        name = line.substring(0, line.indexOf(','));
                        questions.put(name, new ArrayList<>());
                        break;
                    case 1:
                        String question = line.substring(0, line.indexOf('|'));
                        String answer = line.substring(line.indexOf('|'));
                        questions.get(name).add(question);
                        responder.put(question, answer);
                    default:
                }
            }
        } catch (IOException ioe) {
            System.out.println("ERROR ERRRR MA GURD");
        }

        ComboBox questionChoice = new ComboBox();
        questionChoice.setPromptText("Message Options");

        ComboBox personChoice = new ComboBox();
        for (Map.Entry<String, List<String>> entry : questions.entrySet()) {
            personChoice.getItems().add(entry.getKey());
        }
        personChoice.setPromptText("Select a person to question");
        personChoice.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    Object selectedPerson = personChoice.getValue();
                    if (selectedPerson != null) {
                        questionChoice.setItems(FXCollections.observableArrayList(
                                questions.get(selectedPerson.toString()))
                        );
                    }
                }
        );

        Button submit = new Button("Enter message");
        submit.setOnAction(event -> {
            if (questionChoice.getValue() != null) {
                addMessage("You", questionChoice.getValue().toString());
            }
        });

        submitSection.getChildren().addAll(personChoice, questionChoice, submit);
        HBox.setMargin(personChoice, new Insets(40, 20, 40, 20));
        HBox.setMargin(questionChoice, new Insets(40, 20, 40, 20));
        HBox.setMargin(submit, new Insets(40, 20, 40, 20));

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

    private void initChatbox() {
        chatbox = new ScrollPane();
        chatContent = new VBox(10);
        chatbox.setMinHeight(500);
        chatbox.setMinWidth(400);
        chatbox.setContent(chatContent);
    }

    public void addMessage(String name, String message) {
        HBox line = new HBox(30);

        Text personName = new Text(name + ":");
        personName.setFont(new Font(20));
        personName.setFill(Color.BLUE);

        Text chatMessage = new Text(message);
        chatMessage.setFont(new Font(20));
        chatMessage.setFill(Color.BLACK);
        chatMessage.setWrappingWidth(450);

        line.getChildren().addAll(personName, chatMessage);
        chatContent.getChildren().add(line);
    }

    public void addMessage(String name, Photo photo) {
        HBox line = new HBox(30);

        Text personName = new Text(name + ":");
        personName.setFont(new Font(20));
        personName.setFill(Color.BLUE);

        Photo chatPhoto = new Photo(photo.getLink(), 100);

        line.getChildren().addAll(personName, chatPhoto);
        chatContent.getChildren().add(line);
    }

}

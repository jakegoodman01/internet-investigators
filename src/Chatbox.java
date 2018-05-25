import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Chatbox extends VBox {

    private HBox submitSection;
    private ScrollPane chatbox;
    private VBox chatContent;

    public Chatbox() {
        super();
        initSubmitSection();
        initChatbox();
        this.getChildren().addAll(chatbox, submitSection);
    }

    private void initSubmitSection() {
        submitSection = new HBox(20);
        submitSection.setMinHeight(75);
        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.setItems(FXCollections.observableArrayList(
                "How's it going?",
                "Anybody going basketball tryouts after school?",
                "Send me a picture of your rooms!"
        ));

        Button submit = new Button("Enter message");
        submit.setOnAction(event -> {
            if (choiceBox.getValue() != null) {
                addMessage("You", choiceBox.getValue().toString());
            }
        });

        submitSection.getChildren().addAll(choiceBox, submit);
        HBox.setMargin(choiceBox, new Insets(40, 20, 40, 20));
        HBox.setMargin(submit, new Insets(40, 20, 40, 20));

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
        chatMessage.setWrappingWidth(350);

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

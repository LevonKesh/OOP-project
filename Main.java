import Battle.Battle;
import Battle.Board;
import Battle.BoardGUI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private final TextArea textView = new TextArea();
    private final TextField input = new TextField();


    private Parent createContent(){
        VBox root;
        if (Battle.isInitiated()){
            Pane boardView = new BoardGUI(new Board()).getPane();
            root = new VBox(15, boardView,input);
        }
        else {
            textView.setPrefHeight(600 - 80);
            root = new VBox(15,textView,input);
        }

        root.setPadding(new Insets(15));
        return root;
    }

    public void printLn (String line){
        textView.appendText(line + "\n");
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }
}

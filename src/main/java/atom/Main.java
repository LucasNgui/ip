package atom;

import java.io.IOException;

import atom.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Atom using FXML.
 */
public class Main extends Application {

    private final Atom atom = new Atom();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAtom(atom);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

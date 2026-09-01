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

    private static final int MIN_STAGE_HEIGHT = 220;
    private static final int MIN_STAGE_WIDTH = 417;

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap;

        try {
            ap = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(ap);
        stage.setMinHeight(MIN_STAGE_HEIGHT);
        stage.setMinWidth(MIN_STAGE_WIDTH);
        stage.setScene(scene);
        fxmlLoader.<MainWindow>getController().setAtom(atom);
        stage.show();
    }
}

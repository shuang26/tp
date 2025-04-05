package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;

/**
 * Controller for the Find Window.
 * This version only shows the result in the PersonListPanel.
 */
public class FindWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(FindWindow.class);
    private static final String FXML = "FindWindow.fxml";

    @FXML
    private VBox studentListPanelPlaceholder;

    private StudentFindWindowListPanel studentListPanel;

    /**
     * Creates a new FindWindow with the given Stage.
     *
     * @param root Stage to use as the root of the FindWindow.
     */
    public FindWindow(Stage root) {
        super(FXML, root);
        Stage stage = getRoot();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setTitle("Find Window (Press ESC to exit and return to Main Window)");
        stage.setMinWidth(1000);
        stage.setMinHeight(400);
        stage.setResizable(false);

        if (stage.getScene() != null) {
            stage.getScene().addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == javafx.scene.input.KeyCode.ESCAPE) {
                    hide();
                    event.consume();
                }
            });
        }
    }

    /**
     * Creates a new FindWindow.
     */
    public FindWindow() {
        this(new Stage());
    }

    /**
     * Fills up the placeholder of this window
     *
     * @param studentList
     */
    public void fillInnerParts(ObservableList<Student> studentList) {
        studentListPanel = new StudentFindWindowListPanel(studentList);
        studentListPanelPlaceholder.getChildren().clear();
        studentListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());
    }

    /**
     * Closes the Find window.
     */
    @FXML
    private void handleClose() {
        getRoot().hide();
    }

    /**
     * Opens the Find window.
     */
    public void show() {
        logger.fine("Showing find window");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the Find window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the Find window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the Find window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}

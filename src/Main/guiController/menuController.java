package Main.guiController;

import Main.CalcoJavaGame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class menuController {
    @FXML
    protected Label lblAction;

    /**
     * This method is used to start the game.
     * @return Nothing.
     */

    @FXML
    protected void doNewGame() {
        new CalcoJavaGame();
    }
}

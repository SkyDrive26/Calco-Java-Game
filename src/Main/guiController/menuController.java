package Main.guiController;

import javafx.fxml.FXML;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The menuController is used to add functionality
 * to the menu GUI.
 *
 * @author Daan van Bennekom
 * @since 07-02-2019
 */

public class menuController {

    private final List<ChangeListener> listeners =
            new CopyOnWriteArrayList<ChangeListener>();
    protected boolean done = false;

    /**
     * This method is used to start the game.
     * @return Nothing.
     */

    @FXML
    protected void doNewGame() {
        this.setDone(true);
    }

    /**
     * This method is used to return the value
     * of the boolean done.
     * @return Boolean done.
     */

    public boolean getDone(){
        return this.done;
    }

    /**
     * This method is used to set the value of
     * the boolean done. This method also notices
     * any ChangeListeners.
     * @param b Takes an Boolean as parameter.
     * @return Nothing.
     * @see ChangeListener
     */

    public synchronized void setDone(boolean b){
        this.done = b;
        for (ChangeListener cl: listeners)
            cl.stateChanged(new ChangeEvent(this));
    }

    /**
     * This method is used to add a ChangeListener
     * to listen on Boolean done.
     * @param cl Takes a ChangeListener as parameter.
     * @return Nothing.
     * @see ChangeListener
     */

    public synchronized void addChangeListener(ChangeListener cl){
        listeners.add(cl);
    }

    /**
     * This method is used to remove a ChangeListener
     * to listen on Boolean done.
     * @param cl
     * @return Nothing.
     * @see ChangeListener
     */

    public synchronized void removeChangeListener(ChangeListener cl){
        listeners.remove(cl);
    }
}
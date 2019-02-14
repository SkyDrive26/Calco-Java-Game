package Actions;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class newGameAction implements ActionListener {

    /* Fields */
    private final List<ChangeListener> listeners =
            new CopyOnWriteArrayList<ChangeListener>();
    private boolean done = false;


    /* Methods */

    /**
     * This method is used to perform actions
     * when the butten "New Game" is pressed.
     * @param e ActionEvent
     * @see ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e){
        this.setDone(true);
    }

    /**
     * This method is used to change the value of the
     * boolean done. This method also notifies the
     * ChangeListener in the Menu class
     * @param b boolean b.
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

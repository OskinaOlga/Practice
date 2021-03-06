package viewer;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Лиза
 * Date: 08.11.13
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractViewer extends JPanel {
    protected int position;
    protected Viewer parent;

    public AbstractViewer(Viewer parent){
        super(null);
        this.parent = parent;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public abstract void update(int position);

}

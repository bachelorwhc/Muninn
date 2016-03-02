package studio.bachelor.draft.marker;

import studio.bachelor.draft.utility.Lockable;
import studio.bachelor.draft.utility.Selectable;
import studio.bachelor.draft.utility.Touchable;
import studio.bachelor.draft.utility.renderer.MarkerRenderer;
import studio.bachelor.draft.utility.Position;

/**
 * <code>Marker</code>，作為<code>Draft</code>上所顯示的標記。
 */
public abstract class Marker implements Lockable, Touchable, Selectable {
    /** 自 0.2.0 起，<code>position</code>將以<code>Draft</code>中心為基準點。 */
    public final Position position = new Position();
    private boolean locked = false;
    State selectionState = State.UNSELECTED;

    public Marker() {

    }

    public Marker(Position position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    public void move(Position position) {
        if(locked)
            return;
        this.position.set(position);
    }

    public boolean canBeTouched(Position position, double threshold) {
        return getDistanceTo(position) < threshold;
    }

    public double getDistanceTo(Position position) {
        return this.position.getDistanceTo(position);
    }

    @Override
    public void lock() {
        locked = true;
    }

    @Override
    public void unlock() {
        locked = false;
    }

    public boolean isLocked() {
        return locked;
    }

    public State getSelectionState() {
        return selectionState;
    }

    public void select() {
        selectionState = State.SELECTED;
    }

    public void deselect() {
        selectionState = State.UNSELECTED;
    }

    public void selecting() {
        selectionState = State.SELECTING;
    }
}

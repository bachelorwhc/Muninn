package studio.bachelor.draft.toolbox;

import studio.bachelor.draft.utility.Command;
import studio.bachelor.draft.utility.Selectable;

/**
 * Created by BACHELOR on 2016/03/02.
 */
public abstract class Tool implements Selectable, Command {
    State selectionState = State.UNSELECTED;

    public abstract void execute();

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
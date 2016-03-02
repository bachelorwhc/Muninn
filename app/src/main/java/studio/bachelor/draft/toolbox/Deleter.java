package studio.bachelor.draft.toolbox;

import studio.bachelor.draft.utility.Removable;

/**
 * Created by BACHELOR on 2016/03/02.
 */
public class Deleter extends Tool {
    public Removable object;
    public void execute() {
        if(object != null)
            object.remove();
    }
}

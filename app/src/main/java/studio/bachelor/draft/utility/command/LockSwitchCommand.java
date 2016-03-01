package studio.bachelor.draft.utility.command;

import android.view.MotionEvent;

import studio.bachelor.draft.utility.Lockable;

/**
 * Created by BACHELOR on 2016/03/01.
 */
public class LockSwitchCommand extends Command {
    private final Lockable object;

    public LockSwitchCommand(Lockable object) {
        this.object = object;
    }

    public void execute() {
        if(object.isLocked())
            object.unlock();
        else
            object.lock();
    }
}

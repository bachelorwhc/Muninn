package studio.bachelor.draft.utility.motion;

import android.os.Handler;

import studio.bachelor.draft.DraftDirector;
import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.toolbox.Toolbox;
import studio.bachelor.draft.utility.Position;

/**
 * Created by BACHELOR on 2016/03/02.
 */
public class MotionHandler {
    private static final DraftDirector director = DraftDirector.instance;
    public static MotionHandler getInstance() {
        return instance;
    }
    private static final MotionHandler instance = new MotionHandler();

    private MotionHandler() {

    }

    public enum Motion {
        DOWN,
        LONG_PRESS,
        LONG_PRESS_READY,
        SINGLE_TAP,
        DOUBLE_TAP,
        MOVE,
        PINCH_IN,
        PINCH_OUT,
    }

    public void postMotion(Motion motion, Toolbox.Tool tool, Marker marker, Position position_first, Position position_second) {


    }
}

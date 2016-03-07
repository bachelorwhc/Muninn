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
        UP
    }

    public void postMotion(Motion motion, Toolbox.Tool tool, Marker marker, Position position_first, Position position_second) {
        switch (motion) {
            case MOVE:
                director.moveHoldMarker(position_first);
                break;
            case LONG_PRESS:
                if(director.getTool() == Toolbox.Tool.DELETER) {
                    director.removeMarker(marker);
                }
                else {
                    director.selectMarker();
                    director.holdMarker(marker);
                }
                break;
            case LONG_PRESS_READY:
                director.selectingMarker(marker);
                break;
            case UP:
                director.releaseMarker();
                director.deselectMarker();
                break;
            case DOUBLE_TAP:
                director.addMarker(position_first);
                break;
            case SINGLE_TAP:
                if(tool != null)
                    director.selectTool(tool);
                break;
            case PINCH_IN:
                director.zoomDraft(position_first, 0.05f);
                break;
            case PINCH_OUT:
                director.zoomDraft(position_first, -0.025f);
                break;
        }
    }
}

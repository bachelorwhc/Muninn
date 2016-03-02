package studio.bachelor.draft.utility.motion;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import studio.bachelor.draft.Draft;
import studio.bachelor.draft.DraftDirector;
import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.toolbox.Toolbox;
import studio.bachelor.draft.utility.Position;

/**
 * Created by BACHELOR on 2016/03/01.
 */
public class MasterHand implements
        View.OnTouchListener,
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
    private static final MotionHandler handler = MotionHandler.getInstance();
    private static final DraftDirector director = DraftDirector.instance;
    public final GestureDetector gestureDetector;

    public MasterHand(Context context) {
        gestureDetector = new GestureDetector(context, this);
    }

    private void postMotion(MotionHandler.Motion motion, MotionEvent event1, MotionEvent event2) {
        Position position_first = null;
        Position position_second = null;
        if(event1 != null)
            position_first = new Position(event1);

        if(event2 != null)
            position_second = new Position(event2);

        Toolbox.Tool tool = null;
        Marker marker = null;
        if(position_first != null) {
            tool = director.getNearestTool(position_first);
            marker = director.getNearestMarker(position_first);
        }
        handler.postMotion(motion, tool, marker, position_first, position_second);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                postMotion(MotionHandler.Motion.MOVE, event, null);
                break;
        }
        gestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocity_x, float velocity_y) {

        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {

    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distance_x, float distance_y) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Position position = new Position(event);

    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {

        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Position position = new Position(event);

        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        postMotion(MotionHandler.Motion.DOUBLE_TAP, event, null);
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        postMotion(MotionHandler.Motion.SINGLE_TAP, event, null);
        return true;
    }
}

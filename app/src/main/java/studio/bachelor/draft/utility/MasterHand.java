package studio.bachelor.draft.utility;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import studio.bachelor.draft.DraftDirector;

/**
 * Created by BACHELOR on 2016/03/01.
 */
public class MasterHand implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
    private static final DraftDirector director = DraftDirector.instance;

    @Override
    public boolean onDown(MotionEvent event) {

        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {

        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Position position = new Position(event.getX(), event.getY());
        director.addMarker(position);
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {

        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {

        return true;
    }
}

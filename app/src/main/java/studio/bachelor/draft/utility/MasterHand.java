package studio.bachelor.draft.utility;

import android.content.Context;
import android.os.Debug;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import studio.bachelor.draft.DraftDirector;
import studio.bachelor.draft.marker.Marker;

/**
 * Created by BACHELOR on 2016/03/01.
 */
public class MasterHand implements
        View.OnTouchListener,
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
    private static final DraftDirector director = DraftDirector.instance;
    public final GestureDetector gestureDetector;
    private Marker markerHold;

    public MasterHand(Context context) {
        gestureDetector = new GestureDetector(context, this);
    }

    private void holdMarker(Marker marker) {
        markerHold = marker;
    }

    private void releaseMarker() {
        markerHold = null;
    }

    private void moveMarker(Position position) {
        if(markerHold != null) {
            markerHold.move(position);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Position position = new Position(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                moveMarker(position);
                break;
            case MotionEvent.ACTION_UP:
                releaseMarker();
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
        Position position = new Position(event);
        holdMarker(director.getNearestMarker(position));
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distance_x, float distance_y) {
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
        Position position = new Position(event);
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

package studio.bachelor.draft;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import studio.bachelor.draft.utility.MasterHand;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class DraftView extends View{
    private static final String DEBUG_TAG = "Gestures";
    private static final DraftDirector director = DraftDirector.instance;
    private GestureDetector detector;
    private MasterHand masterHand = new MasterHand();

    public DraftView(Context context) {
        super(context);
        initializeMasterHand(context);
    }

    public DraftView(Context context, AttributeSet attribute_set) {
        super(context, attribute_set);
        initializeMasterHand(context);
    }

    private void initializeMasterHand(Context context) {
        detector = new GestureDetector(context, masterHand);
        detector.setOnDoubleTapListener(masterHand);
        setLongClickable(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int old_w, int old_h) {
        super.onSizeChanged(w, h, old_w, old_h);
    }

    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        director.render(canvas);
        invalidate();
        return;
    }
}

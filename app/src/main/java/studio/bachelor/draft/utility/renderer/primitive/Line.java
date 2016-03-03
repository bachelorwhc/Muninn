package studio.bachelor.draft.utility.renderer.primitive;

import android.graphics.Canvas;
import android.graphics.Paint;

import studio.bachelor.draft.utility.Position;
import studio.bachelor.draft.utility.Renderable;

/**
 * Created by BACHELOR on 2016/03/01.
 */
public class Line implements Renderable {
    private float width;
    public final Position head;
    public final Position tail;
    public final Paint paint = new Paint();

    {
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public Line() {
        head = new Position();
        tail = new Position();
    }

    public Line(Position head, Position tail) {
        this.head = head;
        this.tail = tail;
    }

    public void setWidth(float width) {
        this.width = width;
        paint.setStrokeWidth(this.width);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawLine((float)head.x, (float)head.y, (float)tail.x, (float)tail.y, paint);
    }
}

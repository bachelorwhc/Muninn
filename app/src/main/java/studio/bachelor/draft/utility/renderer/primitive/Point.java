package studio.bachelor.draft.utility.renderer.primitive;

import android.graphics.Canvas;
import android.graphics.Paint;

import studio.bachelor.draft.utility.Position;
import studio.bachelor.draft.utility.Renderable;

/**
 * Created by BACHELOR on 2016/03/01.
 */
public class Point implements Renderable {
    private float radius;
    public final Position position;
    public final Paint paint = new Paint();

    {
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public Point() {
        position = new Position();
    }

    public Point(Position position) {
        this.position = position;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        paint.setStrokeWidth(this.radius * 2);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawPoint((float)position.x, (float)position.y, paint);
    }
}

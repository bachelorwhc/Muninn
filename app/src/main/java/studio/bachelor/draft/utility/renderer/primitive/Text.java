package studio.bachelor.draft.utility.renderer.primitive;

import android.graphics.Canvas;
import android.graphics.Paint;

import studio.bachelor.draft.utility.MapString;
import studio.bachelor.draft.utility.Position;
import studio.bachelor.draft.utility.Renderable;

/**
 * Created by bachelor on 2016/3/8.
 */
public class Text implements Renderable {
    private float width;
    private float textWidth;
    public final Position position;
    public final Paint paint = new Paint();
    public final String string;
    public final MapString mapString;

    {
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public Text(String string) {
        position = new Position();
        this.string = string;
        mapString = null;
    }

    public Text(String string, Position position) {
        this.position = position;
        this.string = string;
        mapString = null;
    }

    public Text(MapString string) {
        position = new Position();
        this.string = null;
        mapString = string;
    }

    public Text(MapString string, Position position) {
        this.position = position;
        this.string = null;
        mapString = string;
    }

    public void setWidth(float width) {
        this.width = width;
        paint.setStrokeWidth(this.width);
    }

    public void setTextWidth(float width) {
        this.textWidth = width;
        paint.setTextSize(this.textWidth);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(mapString != null)
            canvas.drawText(mapString.getString(), (float)position.x, (float)position.y, paint);
        else
            canvas.drawText(string, (float)position.x, (float)position.y, paint);
    }
}

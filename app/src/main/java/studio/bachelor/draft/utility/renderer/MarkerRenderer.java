package studio.bachelor.draft.utility.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.LinkedList;
import java.util.List;

import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.utility.Renderable;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class MarkerRenderer implements Renderable {
    private Marker reference;
    public final List<Renderable> primitives = new LinkedList<Renderable>();
    private final Paint paint = new Paint();

    {

    }

    public MarkerRenderer() {

    }

    public void setReference(Marker reference) {
        this.reference = reference;
    }

    public void onDraw(Canvas canvas) {
        if(reference != null) {
            switch (reference.getSelectionState()) {
                case SELECTING:
                    paint.setColor(Color.RED);
                    // TODO: Make radius variable preference.
                    canvas.drawCircle((float) reference.position.x, (float) reference.position.y, 6.0f, paint);
                    break;
                case SELECTED:
                    paint.setColor(Color.BLACK);
                    canvas.drawCircle((float) reference.position.x, (float) reference.position.y, 6.0f, paint);
                    break;
                case UNSELECTED:
                    break;
            }
        }
        for(Renderable primitive : primitives) {
            primitive.onDraw(canvas);
        }
    }
}

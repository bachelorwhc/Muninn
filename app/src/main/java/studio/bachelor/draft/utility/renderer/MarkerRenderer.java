package studio.bachelor.draft.utility.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.LinkedList;
import java.util.List;

import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.utility.Renderable;
import studio.bachelor.draft.utility.Position;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class MarkerRenderer implements Renderable {
    private Marker reference;
    public final List<Renderable> primitives = new LinkedList<Renderable>();


    public MarkerRenderer() {

    }

    public void onDraw(Canvas canvas) {
        for(Renderable primitive : primitives ) {
            primitive.onDraw(canvas);
        }
    }
}

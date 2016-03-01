package studio.bachelor.draft.utility.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.LinkedList;
import java.util.List;

import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.utility.renderer.Renderable;
import studio.bachelor.draft.utility.Position;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class MarkerRenderer implements Renderable {
    private Marker reference;
    public final LinkedList<Part> parts = new LinkedList<Part>();

    public static class Part implements Renderable {
        public enum Primitive {
            POINT, LINE
        }

        public final Paint paint = new Paint();

        {
            paint.setColor(Color.RED);
            paint.setStrokeWidth(5.0f);
            paint.setStrokeCap(Paint.Cap.ROUND);
        }

        public Primitive primitive;
        private Position head;
        private List<Position> inter = new LinkedList<Position>();
        private Position tail;

        public void onDraw(Canvas canvas) {
            switch (primitive) {
                case POINT:
                    canvas.drawPoint((float)head.x, (float)head.y, paint);
                    break;
                case LINE:
                    if(tail == null)
                        break;
                    canvas.drawLine((float)head.x, (float)head.y, (float)tail.x, (float)tail.y, paint);
                    break;
            }
        }

        public Part(Primitive primitive) {
            this.primitive = primitive;
        }

        public void setPosition(Position position) {
            if(position != null)
                this.head = position;
        }

        public void setLineStart(Position position) {
            setPosition(position);
        }

        public void setLineEnd(Position position) {
            if(position != null)
                this.tail = position;
        }
    }

    public MarkerRenderer() {

    }

    public static Part createPartMarkerReference(Marker marker) {
        Part return_value = new Part(Part.Primitive.POINT);
        return_value.setPosition(marker.position);
        return return_value;
    }

    public void onDraw(Canvas canvas) {
        for(Part part : parts ) {
            part.onDraw(canvas);
        }
    }
}

package studio.bachelor.draft.utility;

import android.view.MotionEvent;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class Position {
    public double x;
    public double y;

    public Position() {
        x = 0;
        y = 0;
    }

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position(MotionEvent event) {
        this.x = event.getX();
        this.y = event.getY();
    }

    public void set(Position position) {
        x = position.x;
        y = position.y;
    }

    public float getDistanceTo(Position point) {
        double dx = x - point.x;
        double dy = y - point.y;
        return (float)Math.sqrt(dx * dx + dy * dy);
    }
}

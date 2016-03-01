package studio.bachelor.draft.utility;

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

    public void set(Position position) {
        x = position.x;
        y = position.y;
    }

}

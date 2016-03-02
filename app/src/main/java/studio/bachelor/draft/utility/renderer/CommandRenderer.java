package studio.bachelor.draft.utility.renderer;

import android.graphics.Canvas;

import studio.bachelor.draft.utility.Position;
import studio.bachelor.draft.utility.Renderable;
import studio.bachelor.draft.utility.Touchable;
import studio.bachelor.draft.utility.command.Command;
import studio.bachelor.draft.utility.command.CommandCircleRenderer;
import studio.bachelor.draft.utility.primitive.Icon;

/**
 * Created by BACHELOR on 2016/03/01.
 */
public class CommandRenderer implements Touchable, Renderable {
    private Command command;
    public final CommandCircleRenderer circle;
    private double degree;
    public final Icon icon;

    public CommandRenderer(Icon icon, CommandCircleRenderer circle, double degree) {
        this.icon = icon;
        this.circle = circle;
        this.degree = degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    @Override
    public void onDraw(Canvas canvas) {

        icon.onDraw(canvas);
    }

    @Override
    public boolean canBeTouched(Position position, double threshold) {
        return getDistanceTo(position) < threshold;
    }

    @Override
    public double getDistanceTo(Position position) {
        Position current = getPosition();
        return current.getDistanceTo(position);
    }

    public Position getPosition() {
        return new Position(circle.center.x + Math.cos(degree) * circle.radius,  circle.center.y + Math.sin(degree) * circle.radius);
    }
}

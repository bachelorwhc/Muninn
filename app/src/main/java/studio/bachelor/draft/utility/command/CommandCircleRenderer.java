package studio.bachelor.draft.utility.command;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import studio.bachelor.draft.utility.Position;
import studio.bachelor.draft.utility.Renderable;
import studio.bachelor.draft.utility.command.Command;
import studio.bachelor.draft.utility.renderer.CommandRenderer;

/**
 * Created by BACHELOR on 2016/03/01.
 */
public class CommandCircleRenderer implements Renderable {
    public final List<Command> commands;
    public final List<Renderable> renderables;
    public final Position center;
    public double radius;

    public CommandCircleRenderer(Position center, List<Command> commands, List<Renderable> renderables) {
        this.center = center;
        this.commands = commands;
        this.renderables = renderables;
    }

    @Override
    public void onDraw(Canvas canvas) {

    }
}

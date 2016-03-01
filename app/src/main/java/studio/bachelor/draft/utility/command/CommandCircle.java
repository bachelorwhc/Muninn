package studio.bachelor.draft.utility.command;

import android.graphics.Canvas;

import java.util.LinkedList;
import java.util.List;

import studio.bachelor.draft.utility.Renderable;
import studio.bachelor.draft.utility.command.Command;
import studio.bachelor.draft.utility.renderer.CommandRenderer;

/**
 * Created by BACHELOR on 2016/03/01.
 */
public class CommandCircle implements Renderable {
    public final List<Command> commands = new LinkedList<Command>();
    public final List<Renderable> renderables = new LinkedList<Renderable>();

    public CommandCircle() {

    }

    @Override
    public void onDraw(Canvas canvas) {

    }
}

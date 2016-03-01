package studio.bachelor.draft.utility.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import studio.bachelor.draft.utility.command.Command;
import studio.bachelor.draft.utility.Renderable;
import studio.bachelor.draft.utility.primitive.Icon;

/**
 * Created by BACHELOR on 2016/03/01.
 */
public class CommandRenderer implements Renderable {
    private Command command;
    public final Icon icon;

    public CommandRenderer(Icon icon) {
        this.icon = icon;
    }

    @Override
    public void onDraw(Canvas canvas) {

    }
}

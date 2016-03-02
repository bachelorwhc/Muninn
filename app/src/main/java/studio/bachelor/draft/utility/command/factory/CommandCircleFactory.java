package studio.bachelor.draft.utility.command.factory;

import java.util.ArrayList;
import java.util.List;

import studio.bachelor.draft.DraftDirector;
import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.utility.Position;
import studio.bachelor.draft.utility.command.Command;
import studio.bachelor.draft.utility.command.CommandCircleRenderer;
import studio.bachelor.draft.utility.command.LockSwitchCommand;
import studio.bachelor.draft.utility.primitive.Icon;
import studio.bachelor.draft.utility.renderer.CommandRenderer;
import studio.bachelor.muninn.R;

/**
 * Created by BACHELOR on 2016/03/01.
 */
public class CommandCircleFactory {

    public static CommandCircleRenderer createMarkerCommandCircle(Marker marker) {
        List<Command> commands = new ArrayList<Command>();
        // LockSwitchCommand
        Command c1 = new LockSwitchCommand(marker);
        //CommandRenderer cr1 = new CommandRenderer();
        Icon icon;
        if(marker.isLocked())
            icon = new Icon(position, R.drawable.ic_lock_open_black_48dp);
        else
            icon = new Icon(position, R.drawable.ic_lock_black_48dp);

        commands.add(c1);


        CommandCircleRenderer command_circle = new CommandCircleRenderer(marker.position, commands);
        Command command = new LockSwitchCommand(marker);
        command_circle.commands.add(command);

        Position position = new Position(marker.position.x, marker.position.y - 50);

        command_circle.renderables.add(icon);
        return command_circle;
    }
}

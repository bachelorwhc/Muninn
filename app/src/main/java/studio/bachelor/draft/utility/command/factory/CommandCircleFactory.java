package studio.bachelor.draft.utility.command.factory;

import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.utility.Position;
import studio.bachelor.draft.utility.command.Command;
import studio.bachelor.draft.utility.command.CommandCircle;
import studio.bachelor.draft.utility.command.LockSwitchCommand;
import studio.bachelor.draft.utility.primitive.Icon;
import studio.bachelor.muninn.Muninn;
import studio.bachelor.muninn.R;

/**
 * Created by BACHELOR on 2016/03/01.
 */
public class CommandCircleFactory {
    public CommandCircle createMarkerCommandCircle(Marker marker) {
        CommandCircle command_circle = new CommandCircle();
        Command command = new LockSwitchCommand(marker);
        command_circle.commands.add(command);
        Position position = new Position(marker.position.x, marker.position.y - 50);
        Icon icon;
        if(marker.isLocked())
            icon = new Icon(position, R.drawable.ic_lock_open_black_48dp);
        else
            icon = new Icon(position, R.drawable.ic_lock_black_48dp);
        command_circle.renderables.add(icon);
        return command_circle;
    }
}

package studio.bachelor.draft.marker;

import studio.bachelor.draft.utility.MapStringSupport;
import studio.bachelor.draft.utility.Position;

/**
 * Created by bachelor on 2016/3/8.
 */
public class AnchorMarker extends LinkMarker implements MapStringSupport {
    static private final AnchorMarker instance = new AnchorMarker();
    static public AnchorMarker getInstance() {return instance;}
    private double realDistance;

    private AnchorMarker() {
        super();
        link = new ControlMarker(new Position(position.x + 500, position.y + 500));
    }

    @Override
    public String getObjectMappedString() {
        return String.valueOf(realDistance);
    }

    public double getScale() {
        return realDistance / position.getDistanceTo(this.link.position);
    }
}

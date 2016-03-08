package studio.bachelor.draft.marker;

import studio.bachelor.draft.utility.Position;

/**
 * Created by bachelor on 2016/3/8.
 */
public class AnchorMarker extends LinkMarker {
    static private final AnchorMarker instance = new AnchorMarker();
    static public AnchorMarker getInstance() {return instance;}
    private double realDistance;

    private AnchorMarker() {
        super();
        link = new ControlMarker(new Position(position.x + 500, position.y + 500));
    }

    public void setRealDistance(double real_distance) {
        this.realDistance = real_distance;
    }

    public double getScale() {
        return realDistance / position.getDistanceTo(this.link.position);
    }
}

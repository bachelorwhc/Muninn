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
        this.link = new ControlMarker();
    }

    @Override
    public String getObjectMappedString() {
        return String.valueOf(realDistance);
    }

    public double getScale() {
        return realDistance / position.getDistanceTo(this.link.position);
    }

    public void setRealDistance(double real_distance) {
        this.realDistance = real_distance > 0.0 ? real_distance : 0.0;
    }
}

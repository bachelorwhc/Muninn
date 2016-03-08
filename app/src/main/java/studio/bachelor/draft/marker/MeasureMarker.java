package studio.bachelor.draft.marker;

import studio.bachelor.draft.utility.MapStringSupport;
import studio.bachelor.draft.utility.Position;

/**
 * Created by BACHELOR on 2016/03/08.
 */
public class MeasureMarker extends LinkMarker implements MapStringSupport {
    private double distance;

    public MeasureMarker() {
        super();
    }

    public MeasureMarker(Position position) {
        super(position);
    }

    @Override
    public String getObjectMappedString() {
        return String.valueOf(distance);
    }

    @Override
    public void update() {
        super.update();
        AnchorMarker anchor = AnchorMarker.getInstance();
        distance = position.getDistanceTo(link.position) * anchor.getScale();
    }
}

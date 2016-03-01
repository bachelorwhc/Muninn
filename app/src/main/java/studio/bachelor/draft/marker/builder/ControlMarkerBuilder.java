package studio.bachelor.draft.marker.builder;

import studio.bachelor.draft.marker.ControlMarker;
import studio.bachelor.draft.utility.Position;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class ControlMarkerBuilder extends MarkerBuilder {
    public ControlMarkerBuilder clearProductCache() {
        return (ControlMarkerBuilder)super.clearProductCache();
    }

    protected void createProductIfNull() {
        if(product == null)
            product = new ControlMarker();
    }

    public ControlMarkerBuilder setPosition(Position position) {
        return (ControlMarkerBuilder)super.setPosition(position);
    }
}
package studio.bachelor.draft.marker.builder;

import studio.bachelor.draft.marker.LinkMarker;
import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.utility.Builder;
import studio.bachelor.draft.utility.Position;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class LinkMarkerBuilder extends MarkerBuilder{
    protected void createProductIfNull() {
        if(product == null)
            product = new LinkMarker();
    }

    public LinkMarkerBuilder setPosition(Position position){
        return (LinkMarkerBuilder)super.setPosition(position);
    }

    public LinkMarkerBuilder setLink(Marker marker){
        createProductIfNull();
        ((LinkMarker)product).setLink(marker);
        return this;
    }

    public LinkMarkerBuilder clearProductCache() {
        return (LinkMarkerBuilder)super.clearProductCache();
    }
}

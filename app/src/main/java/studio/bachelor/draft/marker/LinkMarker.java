package studio.bachelor.draft.marker;

import studio.bachelor.draft.utility.Position;

/**
 * <code>LinkMarker</code>可連結單一<code>Marker</code>物件。
 */
public class LinkMarker extends Marker {
    /** */
    Marker link;

    public LinkMarker() {
        super();
    }

    public LinkMarker(Position position) {
        super(position);
    }

    public void setLink(Marker marker) {
        this.link = marker;
    }
    public Marker getLink() {return this.link;}
}

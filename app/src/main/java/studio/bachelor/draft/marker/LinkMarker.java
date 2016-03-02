package studio.bachelor.draft.marker;

import studio.bachelor.draft.utility.Position;

/**
 * <code>LinkMarker</code>可連結單一<code>Marker</code>物件。
 */
public class LinkMarker extends Marker {
    /** */

    // TODO: Remove 問題思考
    Marker link;

    public LinkMarker() {
        super();
    }

    public LinkMarker(Position position) {
        super(position);
    }

    @Override
    public void remove() {
        director.removeMarker(link);
        super.remove();
    }

    public void setLink(Marker marker) {
        this.link = marker;
    }
    public Marker getLink() {return this.link;}
}

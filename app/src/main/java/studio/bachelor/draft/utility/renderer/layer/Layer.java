package studio.bachelor.draft.utility.renderer.layer;

import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.marker.MarkerManager;
import studio.bachelor.draft.utility.Position;

/**
 * Created by BACHELOR on 2016/03/03.
 */
public class Layer{
    private float width;
    private float height;
    private final Position center;
    public final MarkerManager markerManager = new MarkerManager();

    public Position getPositionOfLayer(final Position position) {
        double x = position.x - center.x;
        double y = position.y - center.y;
        return new Position(x, y);
    }

    public Layer(float width, float height) {
        this.height = height;
        this.width = width;
        center = new Position(width / 2, height / 2);
    }

    public void setWidthAndHeight(float width, float height) {
        this.height = height;
        this.width = width;
        center.set(new Position(this.width / 2, this.height / 2));
        for(Marker marker : markerManager.markers) {
            marker.position.set(getPositionOfLayer(marker.position));
        }
    }

    public Position getCenter() {
        return center;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}

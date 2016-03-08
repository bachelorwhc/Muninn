package studio.bachelor.draft;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;

import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.marker.MarkerManager;
import studio.bachelor.draft.utility.Position;
import studio.bachelor.draft.utility.renderer.layer.Layer;
import studio.bachelor.draft.utility.renderer.layer.ScaleLayer;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class Draft{
    private static final DraftDirector director = DraftDirector.instance;
    private static final Draft instance = new Draft();
    public final ScaleLayer layer = new ScaleLayer(0, 0);
    public double scale = 1.0;

    public static Draft getInstance() {
        return instance;
    }

    private Draft() {

    };

    public void setWidthAndHeight(float width, float height) {
        this.layer.setWidthAndHeight(width, height);
    }

    public void addMarker(Marker marker) {
        marker.position.set(getDraftPosition(marker.position));
        layer.markerManager.addMarker(marker);
    }

    public void removeMarker(Marker marker) {
        marker.position.set(getDraftPosition(marker.position));
        layer.markerManager.removeMarker(marker);
    }

    public void moveMarker(Marker marker, Position position) {
        if(marker != null) {
            position = getDraftPosition(position);
            marker.move(position);
        }
    }

    private Position getDraftPosition(Position position) {
        return layer.getPositionOfLayer(position);
    }

    public Marker getNearestMarker(Position position) {
        Position draft_position = getDraftPosition(position);
        return layer.markerManager.getNearestMarker(draft_position, 64);
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}

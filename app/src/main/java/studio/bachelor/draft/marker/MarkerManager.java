package studio.bachelor.draft.marker;

import java.util.LinkedList;
import java.util.List;

import studio.bachelor.draft.DraftDirector;
import studio.bachelor.draft.utility.Position;

/**
 * <code>MarkerManager</code>管理所有<code>Marker</code>物件。
 */
public class MarkerManager {
    public final List<Marker> markers = new LinkedList<Marker>();
    private static final DraftDirector director = DraftDirector.instance;
    private static final MarkerManager instance = new MarkerManager();
    public static MarkerManager getInstance() {
        return instance;
    }

    private MarkerManager() {

    }

    public void addMarker(final Marker marker) {
        if(marker != null)
            markers.add(marker);
    }

    public void removeMarker(final Marker marker) {
        if(marker != null)
            markers.remove(marker);
    }

    /**
     * @param threshold 與<code>position</code>距離的最大值，唯有低於此值的<code>Marker</code>會被<code>return</code>。
     * @return 與<code>position</code>距離最近的<code>Marker</code>，若不存在滿足條件的<code>Marker</code>則為<code>null</code>。
     */
    public Marker getNearestMarker(final Position position, double threshold) {
        Marker min_distance_marker = null;
        double min_distance = Double.MAX_VALUE;

        for (final Marker m : markers) {
            double distance = m.position.getDistanceTo(position);
            if(distance < threshold && distance < min_distance) {
                min_distance_marker = m;
                min_distance = distance;
            }
        }
        return min_distance_marker;
    }
}

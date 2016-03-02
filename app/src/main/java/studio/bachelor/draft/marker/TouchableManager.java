package studio.bachelor.draft.marker;

import java.util.LinkedList;
import java.util.List;

import studio.bachelor.draft.DraftDirector;
import studio.bachelor.draft.utility.Position;
import studio.bachelor.draft.utility.Touchable;

/**
 * <code>MarkerManager</code>管理所有<code>Marker</code>物件。
 */
public class TouchableManager {
    public final List<Touchable> touchables = new LinkedList<Touchable>();
    private static final DraftDirector director = DraftDirector.instance;
    private static final TouchableManager instance = new TouchableManager();
    public static TouchableManager getInstance() {
        return instance;
    }

    private TouchableManager() {

    }

    public void addMarker(final Touchable touchable) {
        if(touchables != null)
            touchables.add(touchable);
    }

    public void removeMarker(final Touchable touchable) {
        if(touchables != null)
            touchables.remove(touchable);
    }

    /**
     * @param threshold 與<code>position</code>距離的最大值，唯有低於此值的<code>Marker</code>會被<code>return</code>。
     * @return 與<code>position</code>距離最近的<code>Marker</code>，若不存在滿足條件的<code>Marker</code>則為<code>null</code>。
     */
    public Touchable getNearestObject(final Position position, double threshold) {
        Touchable min_distance_touchable = null;
        double min_distance = Double.MAX_VALUE;

        for (final Touchable t : touchables) {
            if(t.canBeTouched(position, threshold)) {
                min_distance_touchable = t;
                min_distance = t.getDistanceTo(position);
            }
        }
        return min_distance_touchable;
    }

    public Marker getNearestMarker(final Position position, double threshold) {
        Marker min_distance_marker = null;
        double min_distance = Double.MAX_VALUE;

        for (final Touchable t : touchables) {
            if(t instanceof Marker == false)
                continue;
            if(t.canBeTouched(position, threshold)) {
                min_distance_marker = (Marker)t;
                min_distance = t.getDistanceTo(position);
            }
        }
        return min_distance_marker;
    }
}

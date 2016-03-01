package studio.bachelor.draft.marker;

import studio.bachelor.draft.utility.renderer.MarkerRenderer;
import studio.bachelor.draft.utility.Position;

/**
 * <code>Marker</code>，作為<code>Draft</code>上所顯示的標記。
 */
public abstract class Marker {
    /** 自 0.2.0 起，<code>position</code>將以<code>Draft</code>中心為基準點。 */
    public final Position position = new Position();
    public MarkerRenderer renderer;

    public Marker() {

    }

    public Marker(Position position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }
}

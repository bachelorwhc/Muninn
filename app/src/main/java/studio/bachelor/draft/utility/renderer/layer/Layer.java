package studio.bachelor.draft.utility.renderer.layer;

import studio.bachelor.draft.utility.Position;

/**
 * Created by BACHELOR on 2016/03/03.
 */
public class Layer{
    private float width;
    private float height;
    private final Position center;

    public Layer(float width, float height) {
        this.height = height;
        this.width = width;
        center = new Position(width / 2, height / 2);
    }
}

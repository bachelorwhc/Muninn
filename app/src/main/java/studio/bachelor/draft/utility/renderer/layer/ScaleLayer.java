package studio.bachelor.draft.utility.renderer.layer;

import studio.bachelor.draft.utility.Position;

/**
 * Created by BACHELOR on 2016/03/03.
 */
public class ScaleLayer extends Layer {
    private float currentScale = 1.0f;

    public ScaleLayer(float width, float height) {
        super(width, height);
    }

    @Override
    public Position getPositionOfLayer(final Position position) {
        Position original = super.getPositionOfLayer(position);
        double x = original.x / currentScale;
        double y = original.y / currentScale;
        return new Position(x, y);
    }

    public void scale(float factor) {
        currentScale = currentScale + factor > 0.0f ? currentScale + factor : currentScale;
    }

    public float getScale() {
        return currentScale;
    }
}

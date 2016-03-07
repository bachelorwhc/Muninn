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
    public Position getPositionOfLayer(Position position) {
        position = super.getPositionOfLayer(position);
        return new Position(position.x, position.y);
    }

    public void scale(float factor) {
        currentScale = currentScale + factor > 0.0f ? currentScale + factor : currentScale;
    }

    public float getScale() {
        return currentScale;
    }
}

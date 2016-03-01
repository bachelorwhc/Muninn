package studio.bachelor.draft.utility.renderer;

import android.graphics.Canvas;

import studio.bachelor.draft.Draft;
import studio.bachelor.draft.utility.renderer.Renderable;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class DraftRenderer implements Renderable {
    private Draft draft;

    public DraftRenderer(Draft draft) {
        this.draft = draft;
    }

    public void onDraw(Canvas canvas) {

    }
}

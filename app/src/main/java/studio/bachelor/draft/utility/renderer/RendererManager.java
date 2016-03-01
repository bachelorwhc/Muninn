package studio.bachelor.draft.utility.renderer;

import java.util.LinkedList;
import java.util.List;

import studio.bachelor.draft.DraftDirector;
import studio.bachelor.draft.utility.Renderable;

/**
 * Created by BACHELOR on 2016/02/25.
 */
public class RendererManager {
    private static final DraftDirector director = DraftDirector.instance;
    private static final RendererManager instance = new RendererManager();
    public static RendererManager getInstance() {
        return instance;
    }

    public final List<Renderable> renderObjects = new LinkedList<Renderable>();

    private RendererManager() {

    }

    public void addRenderer(final Renderable render_object) {
        if(render_object != null)
            renderObjects.add(render_object);
    }

    public void removeRenderer(final Renderable render_object) {
        if(render_object != null)
            renderObjects.remove(render_object);
    }
}

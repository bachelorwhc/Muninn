package studio.bachelor.draft.utility.renderer;

import java.util.LinkedList;
import java.util.List;

import studio.bachelor.draft.DraftDirector;
import studio.bachelor.draft.marker.AnchorMarker;
import studio.bachelor.draft.marker.LinkMarker;
import studio.bachelor.draft.utility.Renderable;
import studio.bachelor.draft.utility.renderer.builder.MarkerRendererBuilder;

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
        AnchorMarker anchor = AnchorMarker.getInstance();

        MarkerRendererBuilder mrb = new MarkerRendererBuilder();
        Renderable marker_renderer = mrb.
                setLinkLine((AnchorMarker) anchor).
                setReference(anchor).
                setPoint(anchor).
                build();

        Renderable link_renderer = mrb.
                setReference(anchor.getLink()).
                build();

        addRenderer(marker_renderer);
        addRenderer(link_renderer);
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

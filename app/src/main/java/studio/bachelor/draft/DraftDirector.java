package studio.bachelor.draft;

import android.graphics.Canvas;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import studio.bachelor.draft.marker.LinkMarker;
import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.marker.MarkerManager;
import studio.bachelor.draft.marker.builder.ControlMarkerBuilder;
import studio.bachelor.draft.marker.builder.LinkMarkerBuilder;
import studio.bachelor.draft.toolbox.Toolbox;
import studio.bachelor.draft.utility.Position;
import studio.bachelor.draft.utility.Renderable;
import studio.bachelor.draft.utility.renderer.RendererManager;
import studio.bachelor.draft.utility.renderer.builder.MarkerRendererBuilder;
import studio.bachelor.muninn.R;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class DraftDirector {
    public static final DraftDirector instance = new DraftDirector();
    private Draft draft;
    private MarkerManager markerManager;
    private RendererManager rendererManager;
    private Map<Object, Renderable> renderableMap = new HashMap<Object, Renderable>();
    private Toolbox toolbox;
    private Type makerType = LinkMarker.class;

    {
        draft = Draft.getInstance();
        markerManager = markerManager.getInstance();
        toolbox = Toolbox.getInstance();
        rendererManager = RendererManager.getInstance();
    }

    private DraftDirector() {

    }

    public void addMarker(Position position) {
        if(makerType == LinkMarker.class) {
            //  建立LinkMaker與ControlMaker
            ControlMarkerBuilder cb = new ControlMarkerBuilder();
            Marker link = cb.
                    setPosition(new Position(position.x - 100, position.y)).
                    build();
            LinkMarkerBuilder lb = new LinkMarkerBuilder();
            Marker marker = lb.
                    setPosition(position).
                    setLink(link).
                    build();

            markerManager.addMarker(marker);
            markerManager.addMarker(link);

            //  建立MakerRenderer
            MarkerRendererBuilder mrb = new MarkerRendererBuilder();
            Renderable marker_renderer = mrb.
                    setLinkLine((LinkMarker) marker).
                    setReference(((LinkMarker) marker).getLink()).
                    setReference(marker).
                    build();

            rendererManager.addRenderer(marker_renderer);

            //  建立對應關係
            renderableMap.put(marker, marker_renderer);
        }
    }

    public void removeMarker(Marker marker) {
        if(marker == null)
            return;
        if(renderableMap.containsKey(marker)) {
            Renderable renderable = renderableMap.get(marker);
            rendererManager.removeRenderer(renderable);
            renderableMap.remove(marker);
        }
        markerManager.removeMarker(marker);
    }

    public Marker getNearestMarker(Position position) {
        return markerManager.getNearestMarker(position, 64);
    }

    public void render(Canvas canvas) {
        // TODO: 縮放機制
        for (Renderable renderable : rendererManager.renderObjects) {
            renderable.onDraw(canvas);
        }
    }
}

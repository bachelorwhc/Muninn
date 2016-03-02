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
import studio.bachelor.draft.toolbox.Tool;
import studio.bachelor.draft.toolbox.Toolbox;
import studio.bachelor.draft.utility.Position;
import studio.bachelor.draft.utility.Renderable;
import studio.bachelor.draft.utility.renderer.RendererManager;
import studio.bachelor.draft.utility.renderer.ToolboxRenderer;
import studio.bachelor.draft.utility.renderer.builder.MarkerRendererBuilder;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class DraftDirector {
    public static final DraftDirector instance = new DraftDirector();
    private Draft draft;
    private MarkerManager markerManager;
    private RendererManager rendererManager;
    private Map<Object, Renderable> renderableMap = new HashMap<Object, Renderable>();
    private final Toolbox toolbox = Toolbox.getInstance();
    private ToolboxRenderer toolboxRenderer;
    private Type makerType = LinkMarker.class;

    {
        draft = Draft.getInstance();
        markerManager = markerManager.getInstance();
        rendererManager = RendererManager.getInstance();
    }

    private DraftDirector() {

    }

    public void setToolboxRenderer(Position upper_left_corner, float width, float height) {
        toolboxRenderer = new ToolboxRenderer(toolbox, upper_left_corner, width, height);
    }

    public void addMarker(Position position) {
        if(makerType == LinkMarker.class) {
            //  建立LinkMaker與ControlMaker
            ControlMarkerBuilder cb = new ControlMarkerBuilder();
            Marker linked = cb.
                    setPosition(new Position(position.x - 100, position.y)).
                    build();
            LinkMarkerBuilder lb = new LinkMarkerBuilder();
            Marker marker = lb.
                    setPosition(position).
                    setLink(linked).
                    build();

            markerManager.addMarker(marker);
            markerManager.addMarker(linked);

            //  建立MakerRenderer
            MarkerRendererBuilder mrb = new MarkerRendererBuilder();
            Renderable marker_renderer = mrb.
                    setLinkLine((LinkMarker) marker).
                    setReference(marker).
                    setPoint(marker).
                    build();

            Renderable link_renderer = mrb.
                    setReference(linked).
                    build();

            rendererManager.addRenderer(marker_renderer);
            rendererManager.addRenderer(link_renderer);

            //  建立對應關係
            renderableMap.put(marker, marker_renderer);
            renderableMap.put(linked, link_renderer);
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

    public Tool getNearestTool(Position position) {
        return toolboxRenderer.getInstance(position, 64);
    }

    public void render(Canvas canvas) {
        // TODO: 縮放機制
        if(toolboxRenderer != null)
            toolboxRenderer.onDraw(canvas);

        for (Renderable renderable : rendererManager.renderObjects) {
            renderable.onDraw(canvas);
        }
    }
}

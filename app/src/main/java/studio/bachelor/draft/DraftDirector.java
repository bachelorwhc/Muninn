package studio.bachelor.draft;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;

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
import studio.bachelor.draft.utility.renderer.DraftRenderer;
import studio.bachelor.draft.utility.renderer.RendererManager;
import studio.bachelor.draft.utility.renderer.ToolboxRenderer;
import studio.bachelor.draft.utility.renderer.builder.MarkerRendererBuilder;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class DraftDirector {
    public static final DraftDirector instance = new DraftDirector();
    private Draft draft;
    private DraftRenderer draftRenderer;
    private MarkerManager markerManager;
    private RendererManager rendererManager;
    private Map<Object, Renderable> renderableMap = new HashMap<Object, Renderable>();
    private final Toolbox toolbox = Toolbox.getInstance();
    private ToolboxRenderer toolboxRenderer;
    private Type markerType = LinkMarker.class;
    private Marker markerHold;
    private Marker markerSelecting;
    private Marker markerSelected;
    private Toolbox.Tool tool;
    private final Paint paint = new Paint();

    {
        draft = Draft.getInstance();
        draftRenderer = new DraftRenderer(draft);
        markerManager = markerManager.getInstance();
        rendererManager = RendererManager.getInstance();
    }

    private DraftDirector() {

    }

    public void setBirdviewImageByUri(Uri uri) {
        draftRenderer.setBirdview(uri);
    }

    public void setToolboxRenderer(Position upper_left_corner, float width, float height) {
        toolboxRenderer = new ToolboxRenderer(toolbox, upper_left_corner, width, height);
    }

    public void addMarker(Position position) {
        if(markerType == LinkMarker.class) {
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

    public Toolbox.Tool getNearestTool(Position position) {
        return toolboxRenderer.getInstance(position, 64);
    }

    public void render(Canvas canvas) {
        draftRenderer.onDraw(canvas);

        // TODO: 縮放機制
        if(toolboxRenderer != null)
            toolboxRenderer.onDraw(canvas);

        for (Renderable renderable : rendererManager.renderObjects) {
            renderable.onDraw(canvas);
        }

        if(tool != null) {
            Bitmap bitmap = ToolboxRenderer.getToolIcon(tool);
            canvas.drawBitmap(bitmap, canvas.getWidth() - bitmap.getWidth(), canvas.getHeight() - bitmap.getHeight(), paint);
        }
        //canvas.drawText("DEBUG INFO\n TOOL:" + , canvas.getWidth()/2, canvas.getHeight()-TEXT_PADDING, textPaint);
    }

    public void selectTool(Toolbox.Tool tool) {
        this.tool = tool;
    }

    public void deselectTool() {
        this.tool = null;
    }

    public Toolbox.Tool getTool() {
        return tool;
    }

    public void holdMarker(Marker marker) {
        markerHold = marker;
    }

    public void releaseMarker() {
        markerHold = null;
    }

    public void selectMarker() {
        this.markerSelected = this.markerSelecting;
        this.markerSelecting = null;
        if(this.markerSelected != null)
            this.markerSelected.select();
    }

    public void deselectMarker() {
        if(this.markerSelected != null)
            this.markerSelected.deselect();
        if(this.markerSelecting != null)
            this.markerSelecting.deselect();
        this.markerSelecting = null;
        this.markerSelected = null;
    }

    public void selectingMarker(Marker marker) {
        this.markerSelecting = marker;
        if(this.markerSelecting != null)
            this.markerSelecting.selecting();
    }

    public void setMarkerType(Type type) {
        if(type.toString().contains("Marker"))
            this.markerType = type;
    }

    public void moveHoldMarker(Position position) {
        if(this.markerHold != null)
            this.markerHold.move(position);
    }

}

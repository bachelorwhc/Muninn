package studio.bachelor.draft;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import studio.bachelor.draft.marker.AnchorMarker;
import studio.bachelor.draft.marker.LinkMarker;
import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.marker.MarkerManager;
import studio.bachelor.draft.marker.MeasureMarker;
import studio.bachelor.draft.marker.builder.ControlMarkerBuilder;
import studio.bachelor.draft.marker.builder.LinkMarkerBuilder;
import studio.bachelor.draft.marker.builder.MeasureMarkerBuilder;
import studio.bachelor.draft.toolbox.Toolbox;
import studio.bachelor.draft.utility.MapString;
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
    //private MarkerManager markerManager;
    private RendererManager rendererManager;
    private Map<Object, Renderable> renderableMap = new HashMap<Object, Renderable>();
    private final Toolbox toolbox = Toolbox.getInstance();
    private ToolboxRenderer toolboxRenderer;
    private Type markerType = MeasureMarker.class;
    private Marker markerHold;
    private Marker markerSelecting;
    private Marker markerSelected;
    private Toolbox.Tool tool;
    private final Paint paint = new Paint();

    {
        draft = Draft.getInstance();
        draftRenderer = new DraftRenderer(draft);
        //markerManager = markerManager.getInstance();
        rendererManager = RendererManager.getInstance();
    }

    private DraftDirector() {

    }

    public void setBirdviewImageByUri(Uri uri) {
        draftRenderer.setBirdview(uri);
    }

    public void setWidthAndHeight(float width, float height) {
        this.draft.setWidthAndHeight(width, height);
    }

    public void setToolboxRenderer(Position upper_left_corner, float width, float height) {
        toolboxRenderer = new ToolboxRenderer(toolbox, upper_left_corner, width, height);
    }

    public void addMarker(Position position) {
        if(markerType == MeasureMarker.class) {
            //  建立LinkMaker與ControlMaker
            ControlMarkerBuilder cb = new ControlMarkerBuilder();
            Marker linked = cb.
                    setPosition(new Position(position.x - 100, position.y)).
                    build();
            LinkMarkerBuilder lb = new MeasureMarkerBuilder();
            Marker marker = lb.
                    setPosition(position).
                    setLink(linked).
                    build();

            draft.addMarker(marker);
            draft.addMarker(linked);

            Position[] positions = {marker.position, linked.position};
            List<Position> position_list = new ArrayList<Position>(Arrays.asList(positions));

            //  建立MakerRenderer
            MarkerRendererBuilder mrb = new MarkerRendererBuilder();
            Renderable marker_renderer = mrb.
                    setLinkLine((LinkMarker) marker).
                    setReference(marker).
                    setPoint(marker).
                    setText(new MapString((MeasureMarker) marker), position_list).
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
        else if(markerType == AnchorMarker.class) {

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
        draft.removeMarker(marker);
    }

    public Marker getNearestMarker(Position position) {
        return draft.getNearestMarker(position);
    }

    public Toolbox.Tool getNearestTool(Position position) {
        return toolboxRenderer.getInstance(position, 64);
    }

    public void render(Canvas canvas) {
        canvas.save();
        draftRenderer.onDraw(canvas);

        for (Renderable renderable : rendererManager.renderObjects) {
            renderable.onDraw(canvas);
        }

        canvas.restore();

        if(toolboxRenderer != null)
            toolboxRenderer.onDraw(canvas);

        if(tool != null) {
            Bitmap bitmap = ToolboxRenderer.getToolIcon(tool);
            canvas.drawBitmap(bitmap, canvas.getWidth() - bitmap.getWidth(), canvas.getHeight() - bitmap.getHeight(), paint);
        }
    }

    public void selectTool(Toolbox.Tool tool) {
        this.tool = tool;
        switch (tool) {
            case MAKER_TYPE_LINK:
                this.markerType = MeasureMarker.class;
                break;
            case MAKER_TYPE_ANCHOR:
                this.markerType = AnchorMarker.class;
                break;
        }
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
        if(this.markerHold != null) {
            draft.moveMarker(markerHold, position);
        }
    }

    public void zoomDraft(float scale_offset) {
        this.draft.layer.scale(scale_offset);
    }

    public void moveDraft(Position offset) {
        this.draft.layer.moveLayer(offset);
    }
}

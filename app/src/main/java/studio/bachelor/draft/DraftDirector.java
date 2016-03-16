package studio.bachelor.draft;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import studio.bachelor.draft.marker.AnchorMarker;
import studio.bachelor.draft.marker.LabelMarker;
import studio.bachelor.draft.marker.LinkMarker;
import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.marker.MarkerManager;
import studio.bachelor.draft.marker.MeasureMarker;
import studio.bachelor.draft.marker.builder.ControlMarkerBuilder;
import studio.bachelor.draft.marker.builder.LabelMarkerBuilder;
import studio.bachelor.draft.marker.builder.LinkMarkerBuilder;
import studio.bachelor.draft.marker.builder.MeasureMarkerBuilder;
import studio.bachelor.draft.toolbox.Toolbox;
import studio.bachelor.draft.utility.Builder;
import studio.bachelor.draft.utility.MapString;
import studio.bachelor.draft.utility.Position;
import studio.bachelor.draft.utility.Renderable;
import studio.bachelor.draft.utility.renderer.DraftRenderer;
import studio.bachelor.draft.utility.renderer.RendererManager;
import studio.bachelor.draft.utility.renderer.ToolboxRenderer;
import studio.bachelor.draft.utility.renderer.builder.MarkerRendererBuilder;
import studio.bachelor.muninn.Muninn;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class DraftDirector {
    public static final DraftDirector instance = new DraftDirector();
    private Draft draft;
    private DraftRenderer draftRenderer;
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
    //private final Paint pathPaint = new Paint();
    private Context context;

    // TODO: Refine this after completed.
//    private final List<Path> paths = new ArrayList<Path>();
//    private Path currentPath = null;

    {
        draft = Draft.getInstance();
        draftRenderer = new DraftRenderer(draft);
        rendererManager = RendererManager.getInstance();
//        pathPaint.setStrokeCap(Paint.Cap.ROUND);
//        pathPaint.setStrokeWidth(5.0f);
//        pathPaint.setStyle(Paint.Style.STROKE);
    }

    private DraftDirector() {

    }

    public void setViewContext(Context context) {
        this.context = context;
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

    public void createPathIfPathMode(Position position) {
        if (tool == Toolbox.Tool.PATH_MODE) {
            draft.createPathIfPathMode(position);
        }
    }

    public void recordPath(Position position) {
        if (tool == Toolbox.Tool.PATH_MODE) {
            draft.recordPath(position);
        }
    }

    public void endPath(Position position) {
        if (tool == Toolbox.Tool.PATH_MODE) {
            draft.endPath(position);
        }
    }

    public void addMarker(Position position) {
        if (markerType == MeasureMarker.class) {
            addMeasureMarker(position);
        } else if (markerType == AnchorMarker.class) {
            addAnchorMarker(position);
        } else if (markerType == LabelMarker.class) {
            addLabelMarker(position);
        }
    }

    private void addLabelMarker(Position position) {
        LabelMarkerBuilder lb = new LabelMarkerBuilder();
        final Marker marker = lb.
                setPosition(new Position(position.x, position.y)).
                build();

        final EditText edit_text = new EditText(context);

        AlertDialog.Builder dialog_builder = new AlertDialog.Builder(context);
        dialog_builder
                .setTitle("標籤資訊")
                .setView(edit_text)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String label_str = edit_text.getText().toString();
                        if (label_str.isEmpty())
                            return;
                        ((LabelMarker) marker).setLabel(label_str);
                    }
                })
                .show();

        draft.addMarker(marker);

        //  建立MakerRenderer
        MarkerRendererBuilder mrb = new MarkerRendererBuilder();
        Renderable marker_renderer = mrb.
                setReference(marker).
                setPoint(marker).
                setText(new MapString((LabelMarker) marker), marker.position).
                build();

        rendererManager.addRenderer(marker_renderer);

        //  建立對應關係
        renderableMap.put(marker, marker_renderer);
    }

    private void addAnchorMarker(Position position) {
        //  取得AnchorMarker與ControlMaker
        final Marker marker = AnchorMarker.getInstance();
        Marker linked = AnchorMarker.getInstance().getLink();

        if (renderableMap.containsKey(marker) && renderableMap.containsKey(linked)) {
            rendererManager.removeRenderer(renderableMap.get(marker));
            rendererManager.removeRenderer(renderableMap.get(linked));
        }

        final EditText edit_text = new EditText(context);
        edit_text.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        AlertDialog.Builder dialog_builder = new AlertDialog.Builder(context);
        dialog_builder
                .setTitle("真實距離")
                .setView(edit_text)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String distance_str = edit_text.getText().toString();
                        if (distance_str.isEmpty())
                            return;
                        ((AnchorMarker) marker).setRealDistance(Double.parseDouble(distance_str));
                    }
                })
                .show();

        marker.position.set(position);
        linked.position.set(new Position(position.x + 50, position.y + 50));

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
                setText(new MapString((AnchorMarker) marker), position_list).
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

    public void removeMarker(Marker marker) {
        if (marker == null)
            return;
        if (renderableMap.containsKey(marker)) {
            Renderable renderable = renderableMap.get(marker);
            rendererManager.removeRenderer(renderable);
            renderableMap.remove(marker);
        }
        draft.removeMarker(marker);
    }

    private void addMeasureMarker(Position position) {
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

        if (toolboxRenderer != null)
            toolboxRenderer.onDraw(canvas);

        if (tool != null) {
            Bitmap bitmap = ToolboxRenderer.getToolIcon(tool);
            canvas.drawBitmap(bitmap, canvas.getWidth() - bitmap.getWidth(), canvas.getHeight() - bitmap.getHeight(), paint);
        }
    }

    public void selectTool(Toolbox.Tool tool) {
        if (tool == Toolbox.Tool.CLEAR_PATH)
            draft.clearPaths();
        else
            this.tool = tool;
        switch (tool) {
            case MAKER_TYPE_LINK:
                this.markerType = MeasureMarker.class;
                break;
            case MAKER_TYPE_ANCHOR:
                this.markerType = AnchorMarker.class;
                break;
            case MARKER_TYPE_LABEL:
                this.markerType = LabelMarker.class;
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
        if (this.markerSelected != null)
            this.markerSelected.select();
    }

    public void deselectMarker() {
        if (this.markerSelected != null)
            this.markerSelected.deselect();
        if (this.markerSelecting != null)
            this.markerSelecting.deselect();
        this.markerSelecting = null;
        this.markerSelected = null;
    }

    public void selectingMarker(Marker marker) {
        this.markerSelecting = marker;
        if (this.markerSelecting != null)
            this.markerSelecting.selecting();
    }

    public void setMarkerType(Type type) {
        if (type.toString().contains("Marker"))
            this.markerType = type;
    }

    public void moveHoldMarker(Position position) {
        if (this.markerHold != null) {
            draft.moveMarker(markerHold, position);
        }
    }

    public void zoomDraft(float scale_offset) {
        this.draft.layer.scale(scale_offset);
    }

    public void moveDraft(Position offset) {
        this.draft.layer.moveLayer(offset);
    }

    public void exportToDOM() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Node node = draft.writeDOM(document);
            document.appendChild(node);

            TransformerFactory transformer_factory = TransformerFactory.newInstance();
            Transformer transformer = transformer_factory.newTransformer();
            DOMSource source = new DOMSource(document);
            Date current_time = new Date();
            SimpleDateFormat simple_date_format = new SimpleDateFormat("yyyyMMddHHmmss");
            String filename = simple_date_format.format(current_time) + ".xml";
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        } catch (Exception e) {

        }
    }
}

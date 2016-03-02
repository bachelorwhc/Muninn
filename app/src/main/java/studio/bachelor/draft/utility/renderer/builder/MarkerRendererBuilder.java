package studio.bachelor.draft.utility.renderer.builder;

import studio.bachelor.draft.marker.LinkMarker;
import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.utility.Renderable;
import studio.bachelor.draft.utility.primitive.Icon;
import studio.bachelor.draft.utility.primitive.Line;
import studio.bachelor.draft.utility.primitive.Point;
import studio.bachelor.draft.utility.renderer.MarkerRenderer;
import studio.bachelor.muninn.R;

/**
 * Created by BACHELOR on 2016/02/25.
 */
public class MarkerRendererBuilder extends RendererBuilder {
    public MarkerRendererBuilder() {
        super();
    }

    protected void createProductIfNull() {
        if(product == null)
            product = new MarkerRenderer();
    }

    public MarkerRendererBuilder setReference(Marker marker) {
        // TODO: 功能調整 分割出setPoint 以及參數設計
        createProductIfNull();
        MarkerRenderer renderer = (MarkerRenderer)product;
        renderer.setReference(marker);
        Point primitive = new Point(marker.position);
        renderer.primitives.add(primitive);
        primitive.setRadius(3.0f);
        return this;
    }

    public MarkerRendererBuilder setLinkLine(LinkMarker marker) {
        createProductIfNull();
        MarkerRenderer renderer = (MarkerRenderer)product;
        Line primitive = new Line(marker.position, marker.getLink().position);
        renderer.primitives.add(primitive);
        primitive.setWidth(3.0f);
        return this;
    }

    public MarkerRendererBuilder setIcon(Marker marker, int resource) {
        createProductIfNull();
        MarkerRenderer renderer = (MarkerRenderer)product;
        Icon primitive = new Icon(marker.position, resource);
        renderer.primitives.add(primitive);
        return this;
    }

    public MarkerRendererBuilder clearProductCache() {
        super.clearProductCache();
        return (MarkerRendererBuilder)this;
    }
}

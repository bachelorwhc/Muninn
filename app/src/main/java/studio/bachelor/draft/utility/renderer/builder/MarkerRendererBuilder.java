package studio.bachelor.draft.utility.renderer.builder;

import studio.bachelor.draft.marker.LinkMarker;
import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.utility.renderer.MarkerRenderer;

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
        createProductIfNull();
        MarkerRenderer renderer = (MarkerRenderer)product;
        MarkerRenderer.Part part = renderer.createPartMarkerReference(marker);
        renderer.parts.add(part);
        return this;
    }

    public MarkerRendererBuilder setLinkLine(LinkMarker marker) {
        createProductIfNull();
        MarkerRenderer renderer = (MarkerRenderer)product;
        MarkerRenderer.Part part = new MarkerRenderer.Part(MarkerRenderer.Part.Primitive.LINE);
        part.setLineStart(marker.position);
        part.setLineEnd(marker.getLink().position);
        renderer.parts.add(part);
        return this;
    }

    public MarkerRendererBuilder clearProductCache() {
        return (MarkerRendererBuilder)this;
    }
}

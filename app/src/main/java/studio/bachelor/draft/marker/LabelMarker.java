package studio.bachelor.draft.marker;

import studio.bachelor.draft.utility.MapStringSupport;

/**
 * Created by BACHELOR on 2016/03/10.
 */
public class LabelMarker extends Marker implements MapStringSupport {
    private String label;

    public LabelMarker() {
        label = new String();
    }

    public LabelMarker(String string) {
        label = string;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getObjectMappedString() {
        return String.valueOf(label);
    }

    public String getElementName() {
        return "LabelMarker";
    }
}
